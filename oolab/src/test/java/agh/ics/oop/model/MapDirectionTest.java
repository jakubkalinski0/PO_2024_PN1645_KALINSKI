package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    /*
    Testing methods 'toString', 'next' and 'previous' with Given-When-Then pattern has no reason, because of the
    limited actions each of these methods can take.
    On the other hand 'toUnitVector' is a prefect example where the Given-When-Then pattern can be used.
    */

    // testing String toString method
    @Test
    void changingDirectionToString() {
        assertEquals("Północ", MapDirection.NORTH.toString());
        assertEquals("Wschód", MapDirection.EAST.toString());
        assertEquals("Południe", MapDirection.SOUTH.toString());
        assertEquals("Zachód", MapDirection.WEST.toString());

    }

    // testing MapDirection next method
    @Test
    void changingDirectionToTheNextOne() {
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
    }

    // testing MapDirection previous method
    @Test
    void changingDirectionToThePreviousOne() {
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
    }

    // testing Vector2d toUnitVector method
    @Test
    void changingDirectionToUnitVector() {
        // Given
        Vector2d ExpectedNorth = new Vector2d(0, 1);
        Vector2d ExpectedEast = new Vector2d(1, 0);
        Vector2d ExpectedSouth = new Vector2d(0, -1);
        Vector2d ExpectedWest = new Vector2d(-1, 0);

        // When
        Vector2d north = MapDirection.NORTH.toUnitVector();
        Vector2d east = MapDirection.EAST.toUnitVector();
        Vector2d south = MapDirection.SOUTH.toUnitVector();
        Vector2d west = MapDirection.WEST.toUnitVector();

        // Then
        assertEquals(ExpectedNorth, north);
        assertEquals(ExpectedEast, east);
        assertEquals(ExpectedSouth, south);
        assertEquals(ExpectedWest, west);
    }
  
}