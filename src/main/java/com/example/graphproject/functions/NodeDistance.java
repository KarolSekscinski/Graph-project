package com.example.graphproject.functions;

public class NodeDistance implements Comparable<NodeDistance> {
    private final int nodeId;
    private final double distance;
    private final int otherNodeId;

    public NodeDistance(int nodeId, double distance, int otherNodeId) {
        this.nodeId = nodeId;
        this.distance = distance;
        this.otherNodeId = otherNodeId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public double getDistance() {
        return distance;
    }

    public int getOtherNodeId() {
        return otherNodeId;
    }

    @Override
    public int compareTo(NodeDistance otherNode) {
        return Double.compare(this.distance, otherNode.getDistance());
    }
}
