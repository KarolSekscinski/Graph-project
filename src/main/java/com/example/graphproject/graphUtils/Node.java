package com.example.graphproject.graphUtils;

import java.util.ArrayList;

public class Node {
    private final int nodeId;

    private final ArrayList<Edge> edgesOfNode;

    public Node(int nodeId, ArrayList<Edge> edgesOfNode) {
        this.nodeId = nodeId;
        this.edgesOfNode = edgesOfNode;
    }
    public Node(int newNodeid) {
        edgesOfNode = new ArrayList<>();
        nodeId = newNodeid;
    }

    public int getNodeId() {
        return nodeId;
    }

    public ArrayList<Edge> getEdgesOfNode() {
        return edgesOfNode;
    }
    public void addEdgeToNode(int nodeId, Edge edge) {
        if (nodeId <= 3) {
            edgesOfNode.add(nodeId, edge);
        } else {
            throw new IllegalStateException("The node has too many edges!");
        }
    }

    public Edge getEdgeFromNode(int nodeId) {
        return edgesOfNode.get(nodeId);
    }
    public int getSize() {
        return edgesOfNode.size();
    }
}
