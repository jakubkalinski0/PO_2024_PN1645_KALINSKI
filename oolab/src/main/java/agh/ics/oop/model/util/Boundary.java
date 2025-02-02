package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

/**
 * Represents a rectangular boundary defined by two corner points:
 * @param lowerLeft         the bottom-left corner of the boundary.
 * @param upperRight        the top-right corner of the boundary.
 *
 * This record is immutable and provides a simple way to define boundaries
 * for maps or other spatial structures.
 */
public record Boundary(Vector2d lowerLeft, Vector2d upperRight) {
}