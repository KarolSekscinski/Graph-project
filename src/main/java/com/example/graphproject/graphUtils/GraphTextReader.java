package com.example.graphproject.graphUtils;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class GraphTextReader extends GraphReader {

    private final String filePath;
    protected Graph graph;

    public GraphTextReader(String filePath) {
        this.graph = null;
        this.filePath = "src/main/resources/com/example/graphproject/GraphsDirectory/" + filePath;
    }

    @Override
    public Graph read() throws IOException, IllegalArgumentException, InputMismatchException, NullPointerException {

        try {
            int rows = -2, collumns = -2, i, j;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String[] lines = bufferedReader.readLine().split("\\s+");
            for (i = 0; i < lines.length; i++) {
                if (!lines[i].equals("")) {
                    rows = Integer.parseInt(lines[i]);
                    break;
                }
            }
            for (j = i + 1; j < lines.length; j++) {
                if (!lines[j].equals("")) {
                    collumns = Integer.parseInt(lines[j]);
                    break;
                }
            }
            for (int k = j + 1; k < lines.length; k++) {
                if (!lines[k].equals("")) {
                    generateAlert("There are additional characters after dimensions.\n Please remove them.");
                    break;
                }

            }

            if (rows != -2 && collumns != -2) {
                graph = new Graph(collumns, rows);
            } else {
                generateAlert("Wrong format of graph dimensions.\n Please enter valid file");
            }

            String line;
            Scanner scanner;
            Node newNode;
            for (i = 0; (line = bufferedReader.readLine()) != null; i++) {
                scanner = new Scanner(line.replaceAll(",", "."));
                scanner.useDelimiter(" :|\\s* ");
                scanner.useLocale(Locale.US);

                newNode = new Node(i);
                for (j = 0; scanner.hasNext(); j++) {
                    int dest = scanner.nextInt();
                    if (dest > rows * collumns) {
                        generateAlert("The node doesn't belong to graph\n Please enter a valid file.");
                    }
                    double weight = scanner.nextDouble();
                    if (weight < 0) {
                        generateAlert("Weights of edge between nodes cannot be negative\n Please enter a valid file");
                    }
                    newNode.addEdgeToNode(j, new Edge(dest, weight));

                }
                graph.addNodeToGraph(i, newNode);
            }
        } catch (IllegalArgumentException | InputMismatchException numberFormatException) {
            generateAlert("Wrong format of graph\n Please enter a valid file.");

        } catch (NullPointerException nullPointerException) {
            generateAlert("The graph is empty.");
        } catch (FileNotFoundException fileNotFoundException) {
            generateAlert("A file with this name was not found.");
        }
        return graph;
    }

    private void generateAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
