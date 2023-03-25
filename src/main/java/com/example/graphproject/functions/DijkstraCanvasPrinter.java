package com.example.graphproject.functions;


import com.example.graphproject.graphUtils.CanvasGraphCoordinatesDeteminer;
import com.example.graphproject.graphUtils.GraphCanvasPrinter;
import com.example.graphproject.graphUtils.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DijkstraCanvasPrinter extends DijkstraSolver {
    private final GraphCanvasPrinter graphCanvasPrinter;

    public DijkstraCanvasPrinter(Node source, GraphCanvasPrinter graphCanvasPrinter) {
        this.graphCanvasPrinter = graphCanvasPrinter;
        this.sourceNode = source;
        this.graph = graphCanvasPrinter.getGraph();
        solve();
    }

    @Override
    public void print(Node destinationNode) {
        if (distances[destinationNode.getNodeId()].getOtherNodeId() == -1) {
            return;
        }
        Node currentNode = destinationNode;
        Node nextNode;

        Canvas graphCanvas = graphCanvasPrinter.getGraphCanvas();
        CanvasGraphCoordinatesDeteminer graphCoordinatesDeteminer = graphCanvasPrinter.getGraphCoordinatesDeteminer();

        double pathSize = graphCoordinatesDeteminer.getNodeSize() / 2;
        GraphicsContext graphicsContext = graphCanvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITE);

        graphicsContext.fillOval(graphCoordinatesDeteminer.getCenterOfNodeX(currentNode) - (pathSize / 2),
                graphCoordinatesDeteminer.getCenterOfNodeY(currentNode) - (pathSize / 2), pathSize, pathSize);
        while (currentNode != sourceNode) {
            nextNode = graph.getNodes().get(distances[currentNode.getNodeId()].getOtherNodeId());

            if (nextNode.getNodeId() == currentNode.getNodeId() + 1) {
                graphicsContext.fillRect(graphCoordinatesDeteminer.getCenterOfNodeX(currentNode),
                        graphCoordinatesDeteminer.getCenterOfNodeY(currentNode) - (pathSize / 2),
                        graphCoordinatesDeteminer.getNodeSize(), pathSize);
            }
            if (nextNode.getNodeId() == currentNode.getNodeId() - 1) {
                graphicsContext.fillRect(graphCoordinatesDeteminer.getCenterOfNodeX(nextNode),
                        graphCoordinatesDeteminer.getCenterOfNodeY(nextNode) - (pathSize / 2),
                        graphCoordinatesDeteminer.getNodeSize(), pathSize);
            }
            if (nextNode.getNodeId() == currentNode.getNodeId() + graph.getCollumns()) {
                graphicsContext.fillRect(graphCoordinatesDeteminer.getCenterOfNodeX(currentNode) - (pathSize / 2),
                        graphCoordinatesDeteminer.getCenterOfNodeY(currentNode),
                        pathSize, graphCoordinatesDeteminer.getNodeSize());
            }
            if (nextNode.getNodeId() == currentNode.getNodeId() - graph.getCollumns()) {
                graphicsContext.fillRect(graphCoordinatesDeteminer.getCenterOfNodeX(nextNode) - (pathSize / 2),
                        graphCoordinatesDeteminer.getCenterOfNodeY(nextNode),
                        pathSize, graphCoordinatesDeteminer.getNodeSize());
            }

            graphicsContext.fillOval(graphCoordinatesDeteminer.getCenterOfNodeX(nextNode) - (pathSize / 2),
                    graphCoordinatesDeteminer.getCenterOfNodeY(nextNode) - (pathSize / 2), pathSize, pathSize);
            currentNode = graph.getNodes().get(distances[currentNode.getNodeId()].getOtherNodeId());
        }
    }

    public void makeGradient() {
        double maxValue = findMaxDistance();

        for (NodeDistance nodeDistance : distances) {
            graphCanvasPrinter.paintNode(graph.getNodes().get(nodeDistance.getNodeId()),
                    nodeDistance.getDistance(), 0, maxValue);
        }
    }

    public double findMaxDistance() {
        double maxValue = 0;
        for (NodeDistance nodeDistance : distances) {
            if (nodeDistance.getDistance() > maxValue) {
                maxValue = nodeDistance.getDistance();
            }
        }
        return maxValue;
    }


}
