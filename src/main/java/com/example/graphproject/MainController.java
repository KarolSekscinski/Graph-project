package com.example.graphproject;

import com.example.graphproject.functions.BfsSolver;
import com.example.graphproject.graphutils.*;
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
                new Stop(1, Color.rgb(0, 0, 255))
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
//            GraphCanvasPrinter graphCanvasPrinter = new GraphCanvasPrinter(graph, graphCanvas);
//            graphCanvasPrinter.print();
        }
    }

    @FXML
    public void onClickSaveButton() {
        if (graph == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a graph first", ButtonType.OK);
            alert.showAndWait();
        }
        if (graph != null) {
            try {
                String filePath = filePathTextField.getText();
                GraphTextPrinter graphTextPrinter = new GraphTextPrinter(filePath, graph);
                graphTextPrinter.print();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something while printing to file went wrong.", ButtonType.OK);
                alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a file name.", ButtonType.OK);
            alert.showAndWait();
        }
        clearCanvas();
//        GraphCanvasPrinter graphCanvasPrinter = new GraphCanvasPrinter(graph, graphCanvas);
//        graphCanvasPrinter.print();
    }

    @FXML
    public void onClickSolveBFS() {
        BfsSolver bfsSolver = new BfsSolver(graph);
        if (graph == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please load the graph first.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        try {
            boolean isGraphCoherent = bfsSolver.solve();
            if (isGraphCoherent) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "This graph is coherent.", ButtonType.OK);
                alert.showAndWait();
                return;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "This graph isn't coherent.", ButtonType.OK);
                alert.showAndWait();
                return;
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
    public void onClickDijkstraButton() {

    }
    @FXML
    public void onClickCanvasMouse(MouseEvent mouseEvent) {

    }
}