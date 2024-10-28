package agh.ics.oop.model;
import java.util.Objects;

public class Vector2d {
    // two, unmodifiable private fields of type int
    private final int x;
    private final int y;

    // constructor
    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getter x
    public int getX() {
        return x;
    }

    // getter y
    public int getY() {
        return y;
    }

    // String toString method
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // boolean precedes(Vector2d other) method
    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
        // return this.x <= other.getX() && this.y <= other.getY();
    }

    // boolean follows(Vector2d other) method
    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
        // return this.x >= other.getX() && this.y >= other.getY();
    }

    // Vector2d add(Vector2d other) method
    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
        // return new Vector2d(this.x + other.getX(), this.y + other.getY());
    }

    // Vector2d substract(Vector2d other) method
    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
        // return new Vector2d(this.x - other.getX(), this.y - other.getY());
    }

    // Vector2d upperRight(Vector2d other) method
    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
        // return new Vector2d(Math.max(this.x, other.getX()), Math.max(this.y, other.getY()));
    }

    // Vector2d lowerLeft(Vector2d other) method
    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
        // return new Vector2d(Math.min(this.x, other.getX()), Math.min(this.y, other.getY()));
    }

    // Vector2d opposite method
    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    // boolean equals(Object other) method
    public boolean equals(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Vector2d)) {
            return false;
        }
        Vector2d vector = (Vector2d) other;
        return this.x == vector.x && this.y == vector.y;
        // return this.x == other.getX() && this.y == other.getY();
    }

    // int hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
