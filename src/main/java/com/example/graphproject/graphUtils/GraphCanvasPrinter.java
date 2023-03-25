package com.example.graphproject.graphUtils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphCanvasPrinter {
    private final Graph graph;

    private final Canvas graphCanvas;

    private final CanvasGraphCoordinatesDeteminer graphCoordinatesDeteminer;

    private final ColorPicker colorPicker;

    public GraphCanvasPrinter(Graph graph, Canvas graphCanvas) {
        this.graph = graph;
        this.graphCanvas = graphCanvas;

        graphCoordinatesDeteminer = new CanvasGraphCoordinatesDeteminer(graph, graphCanvas);
        colorPicker = new ColorPicker();
    }

    public void print() {
        GraphicsContext graphicsContext = graphCanvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITE);

        int nodeIndex = 0;

        double nodeSize = graphCoordinatesDeteminer.getNodeSize();
        double ovalSize = graphCoordinatesDeteminer.getOvalSize();
        double edgeSize = graphCoordinatesDeteminer.getEdgeSize();

        double[] rangeOfEdges = graph.determineEdgeRange();

        double nodeX;
        double nodeY = 10;


        double nodeCenterX;
        double nodeCenterY = 10 + (ovalSize / 2);

        for (int row = 0; row < graph.getRows(); row++) {
            nodeX = 10;
            nodeCenterX = 10 + (ovalSize / 2);
            for (int collumn = 0; collumn < graph.getCollumns(); collumn++) {
                for (Edge edge : graph.getNodes().get(nodeIndex).getEdgesOfNode()) {
                    if (edge.getNodeId() == nodeIndex - 1 && graph.getCollumns() != 1) {
                        graphicsContext.setFill(colorPicker.determineColorForCanvas(edge.getWeightOfEdge(),
                                rangeOfEdges[0], rangeOfEdges[1]));
                        graphicsContext.fillRect(nodeCenterX - nodeSize, nodeCenterY - (edgeSize / 2), nodeSize, edgeSize);

                        graphicsContext.setFill(Color.WHITE);
                        graphicsContext.fillOval(nodeX - nodeSize, nodeY, ovalSize, ovalSize);
                    }
                    if (edge.getNodeId() == nodeIndex + 1 && graph.getCollumns() != 1) {
                        graphicsContext.setFill(colorPicker.determineColorForCanvas(edge.getWeightOfEdge(),
                                rangeOfEdges[0], rangeOfEdges[1]));
                        graphicsContext.fillRect(nodeCenterX, nodeCenterY - (edgeSize / 2), nodeSize, edgeSize);
                    }
                    if (edge.getNodeId() == nodeIndex + graph.getCollumns() && graph.getRows() != 1) {
                        graphicsContext.setFill(colorPicker.determineColorForCanvas(edge.getWeightOfEdge(),
                                rangeOfEdges[0], rangeOfEdges[1]));
                        graphicsContext.fillRect(nodeCenterX - (edgeSize / 2), nodeCenterY, edgeSize, nodeSize);
                    }
                    if (edge.getNodeId() == nodeIndex - graph.getCollumns() && graph.getRows() != 1) {
                        graphicsContext.setFill(colorPicker.determineColorForCanvas(edge.getWeightOfEdge(),
                                rangeOfEdges[0], rangeOfEdges[1]));
                        graphicsContext.fillRect(nodeCenterX - (edgeSize / 2), nodeCenterY - nodeSize, edgeSize, nodeSize);

                        graphicsContext.setFill(Color.WHITE);
                        graphicsContext.fillOval(nodeX, nodeY - nodeSize, ovalSize, ovalSize);
                    }
                }
                graphicsContext.setFill(Color.WHITE);
                graphicsContext.fillOval(nodeX, nodeY, ovalSize, ovalSize);
                nodeIndex++;
                nodeX += nodeSize;
                nodeCenterX += nodeSize;
            }
            nodeY += nodeSize;
            nodeCenterY += nodeSize;

        }

    }

    public Node selectNode(double x, double y) {
        return graphCoordinatesDeteminer.findNode(x, y);
    }


    public void paintNode(Node node, Color color) {
        GraphicsContext graphicsContext = graphCanvas.getGraphicsContext2D();
        graphicsContext.setFill(color);
        graphicsContext.fillOval(graphCoordinatesDeteminer.getNodeX(node), graphCoordinatesDeteminer.getNodeY(node),
                graphCoordinatesDeteminer.getOvalSize(), graphCoordinatesDeteminer.getOvalSize());
    }

    public void paintNode(Node node, double value, double startOfRange, double endOfRange) {
        GraphicsContext graphicsContext = graphCanvas.getGraphicsContext2D();
        graphicsContext.setFill(colorPicker.determineColorForCanvas(value, startOfRange, endOfRange));
        graphicsContext.fillOval(graphCoordinatesDeteminer.getNodeX(node), graphCoordinatesDeteminer.getNodeY(node),
                graphCoordinatesDeteminer.getOvalSize(), graphCoordinatesDeteminer.getOvalSize());
    }

    public Canvas getGraphCanvas() {
        return graphCanvas;
    }

    public Graph getGraph() {
        return graph;
    }

    public CanvasGraphCoordinatesDeteminer getGraphCoordinatesDeteminer() {
        return graphCoordinatesDeteminer;
    }
}
