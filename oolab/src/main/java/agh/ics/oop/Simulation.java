package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.IncorrectPositionException;

/**
 * The Simulation class represents a simulation of animal movements on a map.
 */
public class Simulation implements Runnable {
    private static final Logger logger = Logger.getLogger(Simulation.class.getName());
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap worldMap;

    /**
     * Initializes the simulation with initial animal positions, movement directions, and the worldMap.
     *
     * @param       positions  a list of initial positions for animals
     * @param       directions a list of movement directions
     * @param       worldMap        the world worldMap where the simulation takes place
     */
    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap worldMap) {
        this.directions = directions;
        this.animals = new ArrayList<>();
        this.worldMap = worldMap;

        for (Vector2d position : positions) {
            try {
                Animal newAnimal = new Animal(MapDirection.NORTH, position);
                if (worldMap.place(newAnimal)) {
                    this.animals.add(newAnimal);
                }
            } catch (IncorrectPositionException e) {
                logger.warning("Warning: " + e.getMessage());
            }
        }
    }

    /**
     * Runs the simulation by iterating through movement directions and applying them to animals.
     */
    @Override
    public void run() {
        int numberOfAnimals = animals.size();
        System.out.println(worldMap);

        for (int i = 0; i < directions.size(); i++) {
            Animal currentAnimal = animals.get(i % numberOfAnimals);
            worldMap.move(currentAnimal, directions.get(i));

            try {
                Thread.sleep(50); // Pause between movements for visualization
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Simulation interrupted", e);
                Thread.currentThread().interrupt();            }
        }
    }

    /**
     * Returns an unmodifiable list of animals participating in the simulation.
     *
     * @return      a list of animals
     */
    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }
}