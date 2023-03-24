package com.example.graphproject.graphUtils;

import java.util.ArrayList;
import java.util.Random;

public class GraphGenerator {

    public Graph generateGraph(int numberOfCollumns, int numberOfRows, double startOfRange, double endOfRange){
        Graph graph = new Graph(numberOfCollumns, numberOfRows);

        Random random = new Random();
        int graphSize = numberOfRows*numberOfCollumns;
        for (int i = 0; i < graphSize; i++){
            ArrayList<Edge> tempEdges = new ArrayList<>();
            if ((i % numberOfCollumns) != 0){
                tempEdges.add(new Edge(i - 1, random.nextDouble(startOfRange, endOfRange)));
            }
            if (((i + 1) % numberOfCollumns) != 0){
                tempEdges.add(new Edge(i + 1, random.nextDouble(startOfRange, endOfRange)));
            }
            if ((i + numberOfCollumns) < (numberOfCollumns * numberOfRows)){
                tempEdges.add(new Edge(i + numberOfCollumns, random.nextDouble(startOfRange, endOfRange)));
            }
            if ((i - numberOfCollumns) >= 0){
                tempEdges.add(new Edge(i - numberOfCollumns, random.nextDouble(startOfRange, endOfRange)));
            }
            graph.getNodes().add(new Node(i, tempEdges));
        }
        return graph;
    }
}
