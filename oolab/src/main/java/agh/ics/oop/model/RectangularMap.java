package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;

/**
 * Represents a rectangular map with defined width and height.
 *
 * This class extends `AbstractWorldMap` and provides functionality specific to
 * a rectangular map, including boundary checks and position validation.
 */
public class RectangularMap extends AbstractWorldMap {
    private final int width;      // The width of the rectangular map
    private final int height;     // The height of the rectangular map
    private final Vector2d lowerLeft;  // The bottom-left corner of the map
    private final Vector2d upperRight; // The top-right corner of the map

    /**
     * Constructor for the rectangular map.
     *
     * Initializes the map with the given width and height, setting the bottom-left
     * corner to (0,0) and the top-right corner to (width-1, height-1).
     *
     * @param width The width of the map.
     * @param height The height of the map.
     */
    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.lowerLeft = new Vector2d(0, 0);  // Lower-left corner of the map
        this.upperRight = new Vector2d(this.width - 1, this.height - 1);  // Upper-right corner of the map
    }

    /**
     * Checks if the given position is valid for movement.
     *
     * This method checks if the position is within the bounds of the map and if the
     * position is valid based on the superclass's logic for movement.
     *
     * @param position The position to check.
     * @return True if the position is within bounds and valid for movement, otherwise false.
     */
    @Override
    public boolean canMoveTo(Vector2d position) {
        // Check if the position is within the bounds of the map
        return position.follows(lowerLeft) && position.precedes(upperRight) && super.canMoveTo(position);
    }

    /**
     * Returns the current boundaries of the map.
     *
     * This method provides the current lower-left and upper-right boundaries of the map.
     *
     * @return A `Boundary` object representing the current map boundaries.
     */
    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeft, upperRight);  // Return the boundaries of the map
    }
}