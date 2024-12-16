package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private GridPane mapGrid;
    @FXML
    private TextField movesList;
    @FXML
    private Label moveDescription;

    private WorldMap map;

    private int boundaryTop;
    private int boundaryBottom;
    private int boundaryRight;
    private int boundaryLeft;
    private int mapWidth;
    private int mapHeight;

    private int squareWidth=50;
    private int squareHeight=50;

    @FXML
    public void onSimulationStartClicked(ActionEvent actionEvent) {
        String[] args = movesList.getText().split(" ");
        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new GrassField(10);
        map.addObserver(this);
        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        moveDescription.setText("Simulation has been started with the following moves: " + Arrays.toString(args));
        engine.runAsyncInThreadPool();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            clearGrid();
            drawMap();
            moveDescription.setText(message);
        });
    }

    public void drawMap() {
        updateMapBounds();
        setupLabel();
        setupColumns();
        setupRows();
        addElements();
        mapGrid.setGridLinesVisible(true);
    }

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void updateMapBounds() {
        boundaryTop = map.getCurrentBounds().upperRight().getY();
        boundaryBottom = map.getCurrentBounds().lowerLeft().getY();
        boundaryRight = map.getCurrentBounds().upperRight().getX();
        boundaryLeft = map.getCurrentBounds().lowerLeft().getX();
        mapWidth = boundaryRight - boundaryLeft + 1;
        mapHeight = boundaryTop - boundaryBottom + 1;
    }

    public void setupLabel() {
        mapGrid.getColumnConstraints().add(new ColumnConstraints(squareWidth));
        mapGrid.getRowConstraints().add(new RowConstraints(squareHeight));
        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
    }

    public void setupColumns() {
        for (int i = 0; i < mapWidth; i++) {
            Label label = new Label(Integer.toString(i+boundaryLeft));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(squareWidth));
            mapGrid.add(label, i+1, 0);
        }
    }

    public void setupRows() {
        for (int i = 0; i < mapHeight; i++) {
            Label label = new Label(Integer.toString(boundaryTop-i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(squareHeight));
            mapGrid.add(label, 0, i+1);
        }
    }

    public void addElements() {
        for (int i = boundaryLeft; i <= boundaryRight; i++) {
            for (int j = boundaryTop; j >= boundaryBottom; j--) {
                Vector2d position = new Vector2d(i, j);
                String content = map.isOccupied(position) ? map.objectAt(position).toString(): " ";

                Label cellLabel = new Label(content);
                GridPane.setHalignment(cellLabel, HPos.CENTER);

                mapGrid.add(cellLabel, i - boundaryLeft + 1, boundaryTop - j + 1);
            }
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
}
