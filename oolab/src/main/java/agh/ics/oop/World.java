package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class responsible for initializing and running the simulation.
 */
public class World {
    private static final Logger logger = Logger.getLogger(World.class.getName());

    public static void main(String[] args) {
        try {
            // Parse movement directions from input arguments
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

            // Initialize maps
            AbstractWorldMap grassField = new GrassField(50);
            AbstractWorldMap rectangularMap = new RectangularMap(100, 100);

            // Attach observers to maps
            grassField.addObserver(new ConsoleMapDisplay());
            rectangularMap.addObserver(new ConsoleMapDisplay());

            // Create simulation instances
            Simulation simulation1 = new Simulation(positions, directions, grassField);
            Simulation simulation2 = new Simulation(positions, directions, rectangularMap);

            // Run simulations using a thread pool
            SimulationEngine engine = new SimulationEngine(List.of(simulation1, simulation2));
            engine.runAsyncInThreadPool();
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return;
        }

        logger.info("Simulation finished successfully.");
    }
}
