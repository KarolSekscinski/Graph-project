package unitTests.graphTestingTemplates;

import com.example.graphproject.graphUtils.Edge;
import com.example.graphproject.graphUtils.Graph;
import com.example.graphproject.graphUtils.Node;

import java.util.ArrayList;

public class GraphTemplates {

    public Graph templateGraph;
    public Graph templateGraphOne() {
        templateGraph = new Graph(3,3);
        for (int i = 0; i < 9; i++) {
            templateGraph.getNodes().add(new Node(i, new ArrayList<>()));
        }

        // node 0
        templateGraph.getNodes().get(0).getEdgesOfNode().add(new Edge(1, 0.76));
        templateGraph.getNodes().get(0).getEdgesOfNode().add(new Edge(3, 0.66));
        // node 1
        templateGraph.getNodes().get(1).getEdgesOfNode().add(new Edge(0, 0.09));
        templateGraph.getNodes().get(1).getEdgesOfNode().add(new Edge(2, 0.03));
        templateGraph.getNodes().get(1).getEdgesOfNode().add(new Edge(4, 0.01));
        // node 2
        templateGraph.getNodes().get(2).getEdgesOfNode().add(new Edge(1, 0.58));
        templateGraph.getNodes().get(2).getEdgesOfNode().add(new Edge(5, 0.71));
        // node 3
        templateGraph.getNodes().get(3).getEdgesOfNode().add(new Edge(4, 0.42));
        templateGraph.getNodes().get(3).getEdgesOfNode().add(new Edge(6, 0.5));
        templateGraph.getNodes().get(3).getEdgesOfNode().add(new Edge(0, 0.07));
        // node 4
        templateGraph.getNodes().get(4).getEdgesOfNode().add(new Edge(3, 0.88));
        templateGraph.getNodes().get(4).getEdgesOfNode().add(new Edge(5, 0.24));
        templateGraph.getNodes().get(4).getEdgesOfNode().add(new Edge(7, 0.04));
        templateGraph.getNodes().get(4).getEdgesOfNode().add(new Edge(1, 0.37));
        // node 5
        templateGraph.getNodes().get(5).getEdgesOfNode().add(new Edge(4, 0.69));
        templateGraph.getNodes().get(5).getEdgesOfNode().add(new Edge(8, 0.38));
        templateGraph.getNodes().get(5).getEdgesOfNode().add(new Edge(2, 0.4));
        // node 6
        templateGraph.getNodes().get(6).getEdgesOfNode().add(new Edge(7, 0.55));
        templateGraph.getNodes().get(6).getEdgesOfNode().add(new Edge(3, 0.53));
        // node 7
        templateGraph.getNodes().get(7).getEdgesOfNode().add(new Edge(6, 0.77));
        templateGraph.getNodes().get(7).getEdgesOfNode().add(new Edge(8, 0.97));
        templateGraph.getNodes().get(7).getEdgesOfNode().add(new Edge(4, 0.68));
        // node 8
        templateGraph.getNodes().get(8).getEdgesOfNode().add(new Edge(7, 0.31));
        templateGraph.getNodes().get(8).getEdgesOfNode().add(new Edge(5, 0.99));

        return templateGraph;
    }

    //Maybe should be in testUtils
    public void printToConsole(Graph templateGraph) {
        System.out.printf("%d %d\n",templateGraph.getRows(), templateGraph.getCollumns());
        for (int i = 0; i < templateGraph.getSize(); i++) {
            System.out.print("     ");
            for (int j = 0; j < templateGraph.getNodeFromGraph(i).getSize(); j++) {
                int nodeId = templateGraph.getNodeFromGraph(i).getEdgeFromNode(j).getNodeId();
                double weight = templateGraph.getNodeFromGraph(i).getEdgeFromNode(j).getWeightOfEdge();
                System.out.printf("%d :%.2f  ", nodeId, weight);
            }
            System.out.print("\n");
        }
    }

    public Graph coherentGraph() {
        Graph graph = new Graph(3, 3);
        for (int i = 0; i < 9; i++){
            graph.getNodes().add(new Node(i, new ArrayList<>()));
        }
        // node 0
        graph.getNodes().get(0).getEdgesOfNode().add(new Edge(1, 1));
        graph.getNodes().get(0).getEdgesOfNode().add(new Edge(3, 9));
        // node 1
        graph.getNodes().get(1).getEdgesOfNode().add(new Edge(0, 3));
        graph.getNodes().get(1).getEdgesOfNode().add(new Edge(2, 2));
        graph.getNodes().get(1).getEdgesOfNode().add(new Edge(4, 2));
        // node 2
        graph.getNodes().get(2).getEdgesOfNode().add(new Edge(1, 1));
        graph.getNodes().get(2).getEdgesOfNode().add(new Edge(5, 2));
        // node 3
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(0, 3));
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(4, 8));
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(6, 10));
        // node 4
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(1, 8));
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(5, 5));
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(7, 1));
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(3, 5));
        // node 5
        graph.getNodes().get(5).getEdgesOfNode().add(new Edge(4, 3));
        graph.getNodes().get(5).getEdgesOfNode().add(new Edge(2, 4));
        graph.getNodes().get(5).getEdgesOfNode().add(new Edge(8, 7));
        // node 6
        graph.getNodes().get(6).getEdgesOfNode().add(new Edge(3, 5));
        graph.getNodes().get(6).getEdgesOfNode().add(new Edge(7, 3));
        // node 7
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(6, 8));
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(4, 1));
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(8, 2));
        // node 8
        graph.getNodes().get(8).getEdgesOfNode().add(new Edge(7, 3));
        graph.getNodes().get(8).getEdgesOfNode().add(new Edge(5, 6));
        return graph;
    }

    public Graph incoherentGraph() {
        Graph graph = new Graph(3, 3);
        for (int i = 0; i < 9; i++){
            graph.getNodes().add(new Node(i, new ArrayList<>()));
        }

        // node 0
        graph.getNodes().get(0).getEdgesOfNode().add(new Edge(1, 2));
        graph.getNodes().get(0).getEdgesOfNode().add(new Edge(3, 2));
        // node 1
        graph.getNodes().get(1).getEdgesOfNode().add(new Edge(0, 3));
        // node 2
        graph.getNodes().get(2).getEdgesOfNode().add(new Edge(5, 1));
        // node 3
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(0, 2));
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(4,4));
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(6,3));
        // node 4
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(3, 3));
        // node 5
        graph.getNodes().get(5).getEdgesOfNode().add(new Edge(2, 4));
        // node 6
        graph.getNodes().get(6).getEdgesOfNode().add(new Edge(7, 5));
        graph.getNodes().get(6).getEdgesOfNode().add(new Edge(3, 5));
        // node 7
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(6, 5));
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(8, 1));
        // node 8
        graph.getNodes().get(8).getEdgesOfNode().add(new Edge(7, 1));

        return graph;
    }

    public Graph templateGraphForDijkstraOne() {
        Graph graph = new Graph(4, 3);
        for (int i = 0; i < 12; i++){
            graph.getNodes().add(new Node(i, new ArrayList<>()));
        }
        //node 0
        graph.getNodes().get(0).getEdgesOfNode().add(new Edge(1, 2));
        graph.getNodes().get(0).getEdgesOfNode().add(new Edge(4, 2));
        //node 1
        graph.getNodes().get(1).getEdgesOfNode().add(new Edge(2, 3));
        //node 2
        graph.getNodes().get(2).getEdgesOfNode().add(new Edge(6, 1));
        graph.getNodes().get(2).getEdgesOfNode().add(new Edge(3, 1));
        //node 3
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(7, 2));
        //node 4
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(8, 5));
        //node 6
        graph.getNodes().get(6).getEdgesOfNode().add(new Edge(7, 5));
        graph.getNodes().get(6).getEdgesOfNode().add(new Edge(10, 5));
        //node 7
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(11, 5));
        //node 8
        graph.getNodes().get(8).getEdgesOfNode().add(new Edge(9, 1));
        //node 9
        graph.getNodes().get(9).getEdgesOfNode().add(new Edge(10, 1));
        //node 10
        graph.getNodes().get(10).getEdgesOfNode().add(new Edge(11, 5));

        return graph;
    }

    public Graph templateGraphForDijkstraTwo() {
        Graph graph = new Graph(3, 3);
        for (int i = 0; i < 9; i++){
            graph.getNodes().add(new Node(i, new ArrayList<>()));
        }
        // node 0
        graph.getNodes().get(0).getEdgesOfNode().add(new Edge(1, 1));
        graph.getNodes().get(0).getEdgesOfNode().add(new Edge(3, 9));
        // node 1
        graph.getNodes().get(1).getEdgesOfNode().add(new Edge(0, 3));
        graph.getNodes().get(1).getEdgesOfNode().add(new Edge(2, 2));
        graph.getNodes().get(1).getEdgesOfNode().add(new Edge(4, 2));
        // node 2
        graph.getNodes().get(2).getEdgesOfNode().add(new Edge(1, 1));
        graph.getNodes().get(2).getEdgesOfNode().add(new Edge(5, 2));
        // node 3
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(0, 3));
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(4, 8));
        graph.getNodes().get(3).getEdgesOfNode().add(new Edge(6, 10));
        // node 4
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(1, 8));
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(5, 5));
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(7, 1));
        graph.getNodes().get(4).getEdgesOfNode().add(new Edge(3, 5));
        // node 5
        graph.getNodes().get(5).getEdgesOfNode().add(new Edge(4, 3));
        graph.getNodes().get(5).getEdgesOfNode().add(new Edge(2, 4));
        graph.getNodes().get(5).getEdgesOfNode().add(new Edge(8, 7));
        // node 6
        graph.getNodes().get(6).getEdgesOfNode().add(new Edge(3, 5));
        graph.getNodes().get(6).getEdgesOfNode().add(new Edge(7, 3));
        // node 7
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(6, 8));
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(4, 1));
        graph.getNodes().get(7).getEdgesOfNode().add(new Edge(8, 2));
        // node 8
        graph.getNodes().get(8).getEdgesOfNode().add(new Edge(7, 3));
        graph.getNodes().get(8).getEdgesOfNode().add(new Edge(5, 6));

        return graph;
    }
}
