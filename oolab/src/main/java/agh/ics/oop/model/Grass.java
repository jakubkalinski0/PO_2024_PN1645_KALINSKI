package agh.ics.oop.model;

/**
 * Represents a grass patch in the world.
 *
 * This class implements the `WorldElement` interface and represents a single patch of grass
 * located at a specific position on the map. It provides methods to retrieve the position
 * of the grass and its string representation.
 */
public class Grass implements WorldElement {
    private final Vector2d position;  // The position of the grass patch on the map

    /**
     * Constructor for the grass patch.
     *
     * Initializes the grass with a specific position on the map.
     *
     * @param position      The position of the grass patch.
     */
    public Grass(Vector2d position) {
        this.position = position;
    }

    /**
     * Returns the position of the grass patch.
     *
     * This method implements the `getPosition` method from the `WorldElement` interface.
     *
     * @return              The position of the grass patch.
     */
    @Override
    public Vector2d getPosition() {
        return position;  // Return the position of the grass patch
    }

    /**
     * Returns a string representation of the grass patch.
     *
     * This method provides a simple string representation for the grass, which is "*" in this case.
     *
     * @return              A string representation of the grass.
     */
    @Override
    public String toString() {
        return "*";  // Return "*" to represent the grass in the map
    }
}