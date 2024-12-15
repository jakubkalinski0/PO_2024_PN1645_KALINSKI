package agh.ics.oop.presenter;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private Label infoLabel;
    private WorldMap worldMap;

    public void setWorldMap(WorldMap map) {
        this.worldMap = map;
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        drawMap();
    }

    public void drawMap() {
        if (worldMap != null) {
            infoLabel.setText(worldMap.toString());
        }
    }
}
