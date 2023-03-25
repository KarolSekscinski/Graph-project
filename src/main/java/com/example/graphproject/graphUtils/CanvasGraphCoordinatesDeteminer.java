package com.example.graphproject.graphUtils;

import javafx.scene.canvas.Canvas;

public class CanvasGraphCoordinatesDeteminer {
    private final double nodeSize;

    private final double ovalSize;

    private final double edgeSize;

    private final Graph graph;

    public CanvasGraphCoordinatesDeteminer(Graph graph, Canvas graphCanvas) {
        this.graph = graph;
        this.nodeSize = determinateNodeSize(graph, graphCanvas);
        this.edgeSize = nodeSize / 5;
        this.ovalSize = nodeSize / 1.2;
    }

    private double determinateNodeSize(Graph graph, Canvas graphCanvas) {
        double heightToRowsRatio = (graphCanvas.getHeight() - 20) / (graph.getRows() - 1);
        double widthToCollumnsRatio = (graphCanvas.getWidth() - 20) / (graph.getCollumns() + 1);

        if (heightToRowsRatio < widthToCollumnsRatio) {
            return heightToRowsRatio;
        } else {
            return widthToCollumnsRatio;
        }
    }

    public Node findNode(double xValue, double yValue) {
        double nodeX = 10;
        double nodeY = 10;

        int collumn = -1;
        for (int i = 0; i < graph.getCollumns(); i++) {
            if (xValue - nodeX <= ovalSize && xValue - nodeX >= 0) {
                collumn = i;
                break;
            }
            nodeX += nodeSize;

        }
        if (collumn == -1) {
            return null;
        }
        int row = -1;
        for (int i = 0; i < graph.getRows(); i++) {
            if (yValue - nodeY <= ovalSize && yValue - nodeY >= 0) {
                row = i;
                break;
            }
            nodeY += nodeSize;

        }
        if (row == -1) {
            return null;
        }
        return graph.getNodes().get(row * graph.getCollumns() + collumn);
    }

    public double getCenterOfNodeX(Node node) {
        int row = node.getNodeId() / graph.getCollumns();
        int col = node.getNodeId() - (row * graph.getCollumns());

        return col * nodeSize + 10 + (ovalSize / 2);
    }

    public double getCenterOfNodeY(Node node) {
        int row = node.getNodeId() / graph.getCollumns();
        int col = node.getNodeId() - (row * graph.getCollumns());

        return row * nodeSize + 10 + (ovalSize / 2);
    }

    public double getOvalSize() {
        return ovalSize;
    }

    public double getEdgeSize() {
        return edgeSize;
    }

    public double getNodeSize() {
        return nodeSize;
    }

    public double getNodeX(Node node) {
        return getCenterOfNodeX(node) - (ovalSize / 2);
    }

    public double getNodeY(Node node) {
        return getCenterOfNodeY(node) - (ovalSize / 2);
    }
}
