package com.example.graphproject.functions;

import com.example.graphproject.graphUtils.Edge;
import com.example.graphproject.graphUtils.Graph;
import com.example.graphproject.graphUtils.Node;

import java.util.PriorityQueue;

abstract public class DijkstraSolver {
    protected Graph graph;
    protected NodeDistance[] distances;
    protected boolean[] isVisited;
    protected Node sourceNode;

    protected void solve() {
        int sizeOfGraph = graph.getCollumns() * graph.getRows();
        distances = new NodeDistance[sizeOfGraph];
        isVisited = new boolean[sizeOfGraph];
        for (int i = 0; i < sizeOfGraph; i++) {
            if (i != sourceNode.getNodeId()) {
                distances[i] = new NodeDistance(i, Double.MAX_VALUE, -1);
                isVisited[i] = false;
            } else {
                distances[i] = new NodeDistance(i, 0, -1);
                isVisited[i] = true;
            }
        }
        PriorityQueue<NodeDistance> priorityQueue = new PriorityQueue<>(NodeDistance::compareTo);
        NodeDistance tempDistance;

        //adding all edges to priorityQueue
        for (Edge edge : sourceNode.getEdgesOfNode()) {
            tempDistance = new NodeDistance(edge.getNodeId(), edge.getWeightOfEdge(), sourceNode.getNodeId());
            priorityQueue.add(tempDistance);
            distances[tempDistance.getNodeId()] = tempDistance;
        }

        NodeDistance currentEdgeDistance;

        while (!priorityQueue.isEmpty()) {
            currentEdgeDistance = priorityQueue.poll();
            isVisited[currentEdgeDistance.getNodeId()] = true;

            for (Edge edge : graph.getNodes().get(currentEdgeDistance.getNodeId()).getEdgesOfNode()) {
                if (!isVisited[edge.getNodeId()]) {
                    tempDistance = new NodeDistance(edge.getNodeId(),
                            edge.getWeightOfEdge() + currentEdgeDistance.getDistance(),
                            currentEdgeDistance.getNodeId());
                    if (tempDistance.getDistance() < distances[tempDistance.getNodeId()].getDistance()) {
                        priorityQueue.add(tempDistance);
                        distances[tempDistance.getNodeId()] = tempDistance;
                    }

                }
            }
        }

    }
    abstract public void print(Node destination);

    public NodeDistance[] getDistances() {
        return distances;
    }
}
