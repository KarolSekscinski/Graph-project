package com.example.graphproject.graphutils;

public class Edge {
    private final int nodeId;

    private final double weightOfEdge;

    public Edge(int nodeId, double weightOfEdge) {
        this.nodeId = nodeId;
        this.weightOfEdge = weightOfEdge;
    }

    public double getWeightOfEdge() {
        return weightOfEdge;
    }

    public int getNodeId() {
        return nodeId;
    }
}
