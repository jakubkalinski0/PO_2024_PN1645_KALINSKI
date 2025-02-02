package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.*;

/**
 * Abstract implementation of the WorldMap interface.
 *
 * This class provides the base functionality for a world map, including managing animals,
 * checking if positions are occupied, and visualizing the map. It also supports adding observers
 * to listen for changes in the map.
 */
public abstract class AbstractWorldMap implements WorldMap {
    protected Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE); // Lower-left corner of the map
    protected Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE); // Upper-right corner of the map
    protected final Map<Vector2d, Animal> animalMap; // Map storing animals at specific positions
    protected final MapVisualizer mapVisualizer; // Visualizer for displaying the map
    protected final List<MapChangeListener> observers; // List of observers listening to changes
    protected final UUID id; // Unique identifier for the map instance

    /**
     * Constructor initializes the map with default boundaries and sets up collections for animals,
     * observers, and visualizer.
     */
    public AbstractWorldMap() {
        this.lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);  // Set lower-left boundary
        this.upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE); // Set upper-right boundary
        this.animalMap = new HashMap<>();  // Initialize map for animals
        this.mapVisualizer = new MapVisualizer(this); // Initialize map visualizer
        this.observers = new ArrayList<>();  // Initialize list of observers
        this.id = UUID.randomUUID(); // Generate a unique ID for the map
    }

    /**
     * Checks if a given position on the map is occupied by any element.
     *
     * @param position The position to check.
     * @return True if the position is occupied, false otherwise.
     */
    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;  // Return true if an object is at the given position
    }

    /**
     * Returns the object at a specified position on the map.
     *
     * @param position The position to check.
     * @return The object at the given position, or null if the position is empty.
     */
    @Override
    public WorldElement objectAt(Vector2d position) {
        return animalMap.get(position);  // Return the animal at the position (if any)
    }

    /**
     * Determines if an animal can move to a specified position.
     *
     * @param position The position to check.
     * @return True if the position is not occupied by another animal.
     */
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);  // A position is valid if it's not occupied by an animal
    }

    /**
     * Places an animal at the specified position on the map.
     *
     * @param animal The animal to place.
     * @return True if the animal was successfully placed.
     * @throws IncorrectPositionException If the animal cannot be placed at the given position.
     */
    @Override
    public boolean place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())) {
            animalMap.put(animal.getPosition(), animal);  // Add animal to map at its position
            notifyObservers("Animal placed at: " + animal.getPosition());  // Notify observers about the placement
            return true;
        }
        throw new IncorrectPositionException(animal.getPosition());  // Throw exception if position is invalid
    }

    /**
     * Moves an animal on the map based on the specified direction.
     *
     * @param animal The animal to move.
     * @param orientation The direction in which to move the animal.
     */
    @Override
    public void move(Animal animal, MoveDirection orientation) {
        Vector2d oldPosition = animal.getPosition();  // Save the animal's current position
        animal.move(orientation, this);  // Move the animal based on the specified direction
        animalMap.remove(oldPosition);  // Remove the animal from its old position
        animalMap.put(animal.getPosition(), animal);  // Add the animal to its new position
        notifyObservers("Animal moved from " + oldPosition + " to " + animal.getPosition());  // Notify observers about the move
    }

    /**
     * Returns all the elements (animals) on the map as a collection.
     *
     * @return A collection of all world elements (animals) on the map.
     */
    @Override
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animalMap.values());  // Return all animals on the map
    }

    /**
     * Abstract method to get the current boundaries of the map.
     *
     * @return The boundaries of the map.
     */
    @Override
    public abstract Boundary getCurrentBounds();

    /**
     * Returns a string representation of the map.
     *
     * @return A string representing the map, including all animals and its boundaries.
     */
    @Override
    public String toString() {
        return mapVisualizer.draw(getCurrentBounds().lowerLeft(), getCurrentBounds().upperRight());  // Generate the map visualization
    }

    /**
     * Adds an observer to listen for changes in the map.
     *
     * @param observer The observer to add.
     */
    public void addObserver(MapChangeListener observer) {
        observers.add(observer);  // Add observer to the list
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);  // Remove observer from the list
    }

    /**
     * Notifies all observers about a change in the map.
     *
     * @param message The message to send to observers.
     */
    protected void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);  // Notify each observer about the change
        }
    }

    /**
     * Returns the unique identifier of the map.
     *
     * @return The ID of the map.
     */
    @Override
    public UUID getId() {
        return id;  // Return the map's unique ID
    }
}