package agh.ics.oop.model;
import java.util.Objects;

/**
 * Represents a 2D vector with integer coordinates.
 *
 * This class is used to represent a position or a direction in 2D space
 * and provides various utility methods for manipulating vectors.
 */
public class Vector2d {
    private final int x; // The x-coordinate of the vector
    private final int y; // The y-coordinate of the vector

    /**
     * Constructor to initialize the vector with specific x and y coordinates.
     *
     * @param x             The x-coordinate of the vector.
     * @param y             The y-coordinate of the vector.
     */
    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the vector.
     *
     * @return              The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the vector.
     *
     * @return              The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns a string representation of the vector in the format "(x, y)".
     *
     * @return              A string representing the vector.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Checks if this vector precedes another vector (both x and y are less than or equal).
     *
     * @param other         The other vector to compare with.
     * @return              True if this vector precedes the other, otherwise false.
     */
    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    /**
     * Checks if this vector follows another vector (both x and y are greater than or equal).
     *
     * @param other         The other vector to compare with.
     * @return              True if this vector follows the other, otherwise false.
     */
    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    /**
     * Adds another vector to this vector and returns the result as a new vector.
     *
     * @param other         The vector to add.
     * @return              A new vector that is the result of the addition.
     */
    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    /**
     * Subtracts another vector from this vector and returns the result as a new vector.
     *
     * @param other         The vector to subtract.
     * @return              A new vector that is the result of the subtraction.
     */
    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    /**
     * Returns the vector that is the upper-right corner of this vector and another vector.
     *
     * @param other         The other vector to compare with.
     * @return              A new vector representing the upper-right corner of the two vectors.
     */
    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    /**
     * Returns the vector that is the lower-left corner of this vector and another vector.
     *
     * @param other         The other vector to compare with.
     * @return              A new vector representing the lower-left corner of the two vectors.
     */
    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    /**
     * Returns the opposite of this vector, i.e., the vector with negated coordinates.
     *
     * @return A new vector that is the opposite of this vector.
     */
    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    /**
     * Checks if this vector is equal to another object.
     *
     * @param other         The object to compare with.
     * @return              True if the object is a `Vector2d` and has the same coordinates as this vector, otherwise false.
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vector2d)) {
            return false;
        }
        Vector2d vector = (Vector2d) other;
        return this.x == vector.x && this.y == vector.y;
    }

    /**
     * Returns a hash code for this vector based on its coordinates.
     *
     * @return              A hash code for the vector.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}