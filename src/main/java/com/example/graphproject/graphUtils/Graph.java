package com.example.graphproject.graphUtils;


import java.util.ArrayList;

public class Graph {

    private final int collumns;

    private final int rows;

    private final ArrayList<Node> nodes;

    public Graph(int collumns, int rows) {
        this.collumns = collumns;
        this.rows = rows;
        nodes = new ArrayList<>(collumns * rows);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public int getCollumns() {
        return collumns;
    }

    public int getRows() {
        return rows;
    }

    public void addNodeToGraph(int nodeId, Node node) {
        nodes.add(nodeId, node);
    }

    public Node getNodeFromGraph(int nodeId) {
        return nodes.get(nodeId);
    }

    public int getSize() {
        return nodes.size();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Graph otherGraph)) {
            return false;
        }

        if (collumns != otherGraph.getCollumns() || rows != otherGraph.getRows()) {
            return false;
        }
        int graphSize = this.getSize();

        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < this.getNodeFromGraph(i).getSize(); j++) {
                if (this.getNodeFromGraph(i).getEdgeFromNode(j).getNodeId() != otherGraph.getNodeFromGraph(i).getEdgeFromNode(j).getNodeId()) {
                    return false;
                }
                if (this.getNodeFromGraph(i).getEdgeFromNode(j).getWeightOfEdge() != otherGraph.getNodeFromGraph(i).getEdgeFromNode(j).getWeightOfEdge()) {
                    return false;
                }
            }
        }
        return true;
    }

    public double[] determineEdgeRange() {
        double[] edgeRange = new double[2];
        edgeRange[0] = Integer.MAX_VALUE;
        edgeRange[1] = 0;

        for (Node node : this.getNodes()) {
            for (Edge edge : node.getEdgesOfNode()) {
                if (edge.getWeightOfEdge() > edgeRange[1]) {
                    edgeRange[1] = edge.getWeightOfEdge();
                }
                if (edge.getWeightOfEdge() < edgeRange[0]) {
                    edgeRange[0] = edge.getWeightOfEdge();
                }

            }
        }
        return edgeRange;
    }
}
