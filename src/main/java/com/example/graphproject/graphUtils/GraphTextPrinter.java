package com.example.graphproject.graphUtils;

import java.io.FileWriter;
import java.io.IOException;

public class GraphTextPrinter extends GraphPrinter {
    protected String filePath;

    protected Graph graph;

    public GraphTextPrinter(String filePath, Graph graph) {
        this.filePath = "src/main/resources/com/example/graphproject/GraphsDirectory/" + filePath;
        this.graph = graph;
    }

    public GraphTextPrinter(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void print() throws IOException {
        FileWriter output = new FileWriter(filePath);
        output.write(String.format("%d %d\n", graph.getRows(), graph.getCollumns()));

        for (int i = 0; i < graph.getSize(); i++) {
            output.write("     ");
            for (int j = 0; j < graph.getNodeFromGraph(i).getSize(); j++) {

                int nodeId = graph.getNodeFromGraph(i).getEdgeFromNode(j).getNodeId();
                double weight = graph.getNodeFromGraph(i).getEdgeFromNode(j).getWeightOfEdge();

                output.write(String.format("%d :%.16f  ", nodeId, weight));
            }
            output.write("\n");
        }
        output.close();
    }

}
