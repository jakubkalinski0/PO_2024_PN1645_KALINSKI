package agh.ics.oop;

import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The SimulationApp class is responsible for initializing and launching the JavaFX application.
 */
public class SimulationApp extends Application {

    /**
     * Starts the JavaFX application, loads the FXML view, and initializes the presenter.
     *
     * @param primaryStage the primary stage for the JavaFX application
     * @throws Exception if an error occurs while loading the FXML file
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        configureStage(primaryStage, viewRoot);
        primaryStage.show();
    }

    /**
     * Configures the primary stage with the provided view and sets basic properties.
     *
     * @param primaryStage the primary stage of the application
     * @param viewRoot the root layout of the application view
     */
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
