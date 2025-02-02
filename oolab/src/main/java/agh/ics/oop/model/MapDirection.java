package agh.ics.oop.model;

/**
 * Enum representing the four cardinal directions: NORTH, EAST, SOUTH, and WEST.
 * This enum provides methods to manipulate and represent directions in a compact way.
 */
public enum MapDirection {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Returns a short string representation of the direction.
     *
     * @return A string representing the direction ("N", "E", "S", or "W").
     */
    public String toString() {
        return switch (this) {
            case NORTH -> "N";  // North is represented by "N"
            case EAST -> "E";   // East is represented by "E"
            case SOUTH -> "S";  // South is represented by "S"
            case WEST -> "W";   // West is represented by "W"
        };
    }

    /**
     * Returns the next direction in a clockwise rotation.
     *
     * @return The direction that is 90 degrees clockwise from the current direction.
     */
    public MapDirection next() {
        return switch (this) {
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case NORTH -> EAST;
        };
    }

    /**
     * Returns the previous direction in a counterclockwise rotation.
     *
     * @return The direction that is 90 degrees counterclockwise from the current direction.
     */
    public MapDirection previous() {
        return switch (this) {
            case EAST -> NORTH;
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
        };
    }

    /**
     * Converts the current direction into a corresponding unit vector.
     *
     * @return A `Vector2d` representing the direction as a unit vector.
     *         For example, North is (0,1), East is (1,0), etc.
     */
    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);   // North corresponds to (0,1)
            case EAST -> new Vector2d(1, 0);    // East corresponds to (1,0)
            case SOUTH -> new Vector2d(0, -1);  // South corresponds to (0,-1)
            case WEST -> new Vector2d(-1, 0);   // West corresponds to (-1,0)
        };
    }
}
