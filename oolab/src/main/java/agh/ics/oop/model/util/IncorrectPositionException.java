package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

/**
 * Custom exception class that represents an error when an invalid position
 * is encountered. This exception carries the invalid position that caused the
 * exception to be thrown.
 */
public class IncorrectPositionException extends Exception {

    // The invalid position that triggered the exception
    private final Vector2d position;

    /**
     * Constructor for creating a new `IncorrectPositionException`.
     * This constructor initializes the exception with a message indicating
     * the invalid position and stores the position for later retrieval.
     *
     * @param position The invalid position that caused the exception.
     */
    public IncorrectPositionException(Vector2d position) {
        // Calls the superclass constructor with a custom error message
        super("Position " + position + " is not correct");
        this.position = position;
    }

    /**
     * Retrieves the invalid position that triggered this exception.
     *
     * @return The invalid position.
     */
    public Vector2d getPosition() {
        return position;
    }
}