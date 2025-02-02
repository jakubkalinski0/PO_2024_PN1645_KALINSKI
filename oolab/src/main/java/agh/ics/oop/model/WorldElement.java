package agh.ics.oop.model;

/**
 * Interface representing an element in the world.
 *
 * Any object that implements this interface must provide a way to retrieve its position
 * in the world, represented by a `Vector2d`.
 */
public interface WorldElement {

    /**
     * Returns the position of the world element.
     *
     * This method provides the coordinates of the element within the world, typically
     * as an instance of the `Vector2d` class.
     *
     * @return The position of the world element.
     */
    Vector2d getPosition();
}