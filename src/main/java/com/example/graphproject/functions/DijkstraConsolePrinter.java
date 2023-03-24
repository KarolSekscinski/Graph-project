package com.example.graphproject.functions;

import com.example.graphproject.graphUtils.Graph;
import com.example.graphproject.graphUtils.Node;

public class DijkstraConsolePrinter extends DijkstraSolver{
    public DijkstraConsolePrinter(Graph graph, Node startNode) {
        this.graph = graph;
        this.sourceNode = startNode;
        solve();
    }

    @Override
    public void print(Node destinationNode) {
        Node currentNode = destinationNode;

        while (currentNode != sourceNode) {
            System.out.print(currentNode.getNodeId() + " <- ");
            currentNode = graph.getNodes().get(distances[currentNode.getNodeId()].getOtherNodeId());
        }
        System.out.print(sourceNode.getNodeId());
    }
}
