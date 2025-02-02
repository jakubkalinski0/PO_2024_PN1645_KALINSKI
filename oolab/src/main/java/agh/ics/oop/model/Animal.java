package agh.ics.oop.model;

/**
 * Represents an animal in the world.
 *
 * This class implements the `WorldElement` interface and represents an animal with
 * a specific orientation and position on the map. It provides methods to get the animal's
 * current position and orientation, as well as methods for moving and changing its orientation.
 */
public class Animal implements WorldElement {
    private MapDirection orientation;  // The current orientation of the animal
    private Vector2d position;        // The current position of the animal

    /**
     * Default constructor for the animal. Initializes the animal's position at (2,2)
     * and orientation to NORTH.
     */
    public Animal() {
        this.orientation = MapDirection.NORTH;  // Default orientation is NORTH
        this.position = new Vector2d(2, 2);    // Default position is (2,2)
    }

    /**
     * Constructor for the animal with a specified orientation and position.
     *
     * @param orientation       The initial orientation of the animal.
     * @param position          The initial position of the animal.
     */
    public Animal(MapDirection orientation, Vector2d position) {
        this.orientation = orientation;  // Set the initial orientation
        this.position = position;        // Set the initial position
    }

    /**
     * Returns the current orientation of the animal.
     *
     * @return                  The orientation of the animal.
     */
    public MapDirection getOrientation() {
        return this.orientation;
    }

    /**
     * Returns the current position of the animal.
     *
     * @return                  The position of the animal.
     */
    public Vector2d getPosition() {
        return this.position;
    }

    /**
     * Returns a string representation of the animal's orientation.
     *
     * This method overrides the `toString` method to display the animal's orientation
     * as a string (e.g., "N" for NORTH).
     *
     * @return                  A string representing the animal's orientation.
     */
    @Override
    public String toString() {
        return this.orientation.toString();  // Return the orientation as a string
    }

    /**
     * Checks if the animal is at the specified position.
     *
     * @param position          The position to check.
     * @return                  True if the animal is at the given position, otherwise false.
     */
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);  // Check if the current position matches the given position
    }

    /**
     * Moves the animal based on the specified direction.
     *
     * The movement is determined by the provided `MoveDirection`. The animal's position
     * and orientation may change based on this input. If the animal attempts to move
     * to a position it cannot occupy, it stays at its current position.
     *
     * @param direction         The direction in which the animal should move.
     * @param map               The map on which the animal is moving. Used to check if the move is valid.
     */
    public void move(MoveDirection direction, WorldMap map) {
        switch (direction) {
            case LEFT -> this.orientation = this.orientation.previous();  // Turn left
            case RIGHT -> this.orientation = this.orientation.next();     // Turn right
            case FORWARD -> {  // Move forward
                Vector2d newPosition = this.position.add(this.orientation.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    this.position = newPosition;  // Update position if move is valid
                }
            }
            case BACKWARD -> {  // Move backward
                Vector2d newPosition = this.position.subtract(this.orientation.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    this.position = newPosition;  // Update position if move is valid
                }
            }
        }
    }
}