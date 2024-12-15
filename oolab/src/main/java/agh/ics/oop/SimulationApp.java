package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class SimulationApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();

//        List<MoveDirection> directions = OptionsParser.parse("f f f f f f f f f f".split(" "));
        List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(1, 0), new Vector2d(1, 1));
        String[] args = getParameters().getRaw().toArray(new String[0]);
        List<MoveDirection> directions = OptionsParser.parse(args);
        AbstractWorldMap map1 = new GrassField(10);
        map1.addObserver(new ConsoleMapDisplay());
        map1.addObserver(presenter);
        Simulation simulation1 = new Simulation(positions, directions, map1);
        SimulationEngine engine = new SimulationEngine(List.of(simulation1));

        engine.runAsync();
        presenter.setWorldMap(map1);
        presenter.drawMap();
        configureStage(primaryStage, viewRoot);
        primaryStage.show();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
