package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private Label infoLabel;
    @FXML
    private TextField movesList;
    @FXML
    private Label moveDescription;

    private WorldMap map;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            drawMap();
            moveDescription.setText(message);
        });
    }

    public void drawMap() {
        if (map != null) {
            infoLabel.setText(map.toString());
        }
    }

    @FXML
    public void onSimulationStartClicked(ActionEvent actionEvent) {
        String[] args = movesList.getText().split(" ");
        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new GrassField(10);
        map.addObserver(this);
        setWorldMap(map);
        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        moveDescription.setText("Simulation has been started with the following moves: " + Arrays.toString(args));
        engine.runAsyncInThreadPool();
    }
}
