package com.example.graphproject;

import com.example.graphproject.functions.BfsSolver;
import com.example.graphproject.functions.DijkstraCanvasPrinter;
import com.example.graphproject.graphUtils.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final GraphGenerator graphGenerator = new GraphGenerator();
    private Graph graph;
    private ObservableList<Node> selectedNodes;
    @FXML
    private TextField gridSizeTextField;
    @FXML
    private TextField edgeWeightTextField;
    @FXML
    private TextField filePathTextField;
    @FXML
    private Canvas graphCanvas;
    @FXML
    private Label selectedNodesLabel;
    @FXML
    private Rectangle gradientRectangle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedNodesLabel.setText("0");
        selectedNodes = FXCollections.observableArrayList();
        selectedNodes.addListener((ListChangeListener<Node>) change -> selectedNodesLabel.setText(String.valueOf(selectedNodes.size())));
        GraphicsContext graphicsContext = graphCanvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());
        Stop[] stops = new Stop[]{
                new Stop(0, Color.rgb(255, 0, 0)),
                new Stop(0.25, Color.rgb(255, 255, 0)),
                new Stop(0.5, Color.rgb(0, 255, 0)),
                new Stop(0.75, Color.rgb(0, 255, 255)),
                new Stop(1, Color.rgb(0, 0, 255)),
        };
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        gradientRectangle.setFill(linearGradient);
    }

    @FXML
    public void onClickGenerateButton() {
        String gridSizeText = gridSizeTextField.getText();
        String edgeWeightText = edgeWeightTextField.getText();

        int numberOfCollumns, numberOfRows;
        double startRange, endRange;
        try {
            numberOfCollumns = Integer.parseInt(gridSizeText.split("x")[0]);
            numberOfRows = Integer.parseInt(gridSizeText.split("x")[1]);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong format of graph dimensions.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        try {
            startRange = Double.parseDouble(edgeWeightText.split("-")[0]);
            endRange = Double.parseDouble(edgeWeightText.split("-")[1]);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong format of the weight range.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        onClickDeleteButton();
        graph = graphGenerator.generateGraph(numberOfCollumns, numberOfRows, startRange, endRange);
        gridSizeTextField.clear();
        edgeWeightTextField.clear();

        onClickRedrawButton();
    }

    @FXML
    public void onClickRedrawButton() {
        selectedNodes.clear();
        clearCanvas();
        if (graph != null) {
            GraphCanvasPrinter graphCanvasPrinter = new GraphCanvasPrinter(graph, graphCanvas);
            graphCanvasPrinter.print();
        }
    }

    @FXML
    public void onClickSaveButton() {
        if (graph == null) {
            generateAlert("Please enter a graph.");
        }
        if (graph != null) {
            try {
                String filePath = filePathTextField.getText();
                GraphTextPrinter graphTextPrinter = new GraphTextPrinter(filePath, graph);
                graphTextPrinter.print();
            } catch (Exception e) {
                generateAlert("While printing to file something went wrong.");
            }
        }
    }

    @FXML
    public void onClickDeleteButton() {
        selectedNodes.clear();
        graph = null;
        clearCanvas();
    }

    @FXML
    public void onClickImportButton() {
        String filePath = filePathTextField.getText();
        GraphTextReader graphTextReader = new GraphTextReader(filePath);
        try {
            this.graph = graphTextReader.read();
        } catch (Exception e) {
            generateAlert("Please enter a file name.");
        }
        clearCanvas();
        GraphCanvasPrinter graphCanvasPrinter = new GraphCanvasPrinter(graph, graphCanvas);
        graphCanvasPrinter.print();
    }

    @FXML
    public void onClickSolveBFS() {
        BfsSolver bfsSolver = new BfsSolver(graph);
        if (graph == null) {
            generateAlert("Please load the graph.");
        }
        try {
            boolean isGraphCoherent = bfsSolver.solve();
            if (isGraphCoherent) {
                generateAlert("This graph is coherent.");
            } else {
                generateAlert("This graph isn't coherent.");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void clearCanvas() {
        GraphicsContext graphicsContext = graphCanvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());
    }

    @FXML
    public void onClickDijkstraSolverButton() {
        if (graph != null && selectedNodes.size() > 1) {
            DijkstraCanvasPrinter dijkstraCanvasPrinter =
                    new DijkstraCanvasPrinter(selectedNodes.get(0), new GraphCanvasPrinter(graph, graphCanvas));
            for (int i = 1; i < selectedNodes.size(); i++) {
                dijkstraCanvasPrinter.print(selectedNodes.get((i)));
            }
        }
        if (graph == null) {
            generateAlert("Please load the graph.");
        }
        if (selectedNodes.size() == 0) {
            generateAlert("Please choose the starting and destination nodes.");
        }
        if (selectedNodes.size() == 1) {
            generateAlert("Please choose the destination node.");
        }

    }

    @FXML
    public void onClickCanvasMouse(MouseEvent mouseEvent) {
        if (graph != null) {
            GraphCanvasPrinter graphCanvasPrinter = new GraphCanvasPrinter(graph, graphCanvas);
            Node selected = graphCanvasPrinter.selectNode(mouseEvent.getX(), mouseEvent.getY());
            if (selected != null) {
                if (selectedNodes.size() == 0) {
                    selectedNodes.add(selected);
                    DijkstraCanvasPrinter dijkstraCanvasPrinter = new DijkstraCanvasPrinter(selected, graphCanvasPrinter);
                    dijkstraCanvasPrinter.makeGradient();
                } else {
                    boolean isAlreadyInside = false;
                    for (Node node : selectedNodes) {
                        if (node.getNodeId() == selected.getNodeId()) {
                            isAlreadyInside = true;
                            break;
                        }
                    }
                    if (!isAlreadyInside) {
                        selectedNodes.add(selected);
                        graphCanvasPrinter.paintNode(selected, Color.GREEN);
                    }
                }
            }
        }
    }

    private void generateAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}