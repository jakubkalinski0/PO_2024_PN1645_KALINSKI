package agh.ics.oop;

import javafx.application.Application;

/**
 * Entry point for the graphical version of the simulation.
 * This class initializes and launches the JavaFX application.
 */
public class WorldGUI {
    public static void main(String[] args) {
        // Launches the JavaFX application by starting the SimulationApp class
        Application.launch(SimulationApp.class, args);
    }
}