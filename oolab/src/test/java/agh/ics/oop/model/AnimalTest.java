package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    // testing MapDirection getOrientation method
    @Test
    void gettingOrientation() {
        // Given
        Animal animalDefaultOrientation = new Animal();
        Animal animalNorthOrientation = new Animal(MapDirection.NORTH, new Vector2d(0, 0));
        Animal animalEastOrientation = new Animal(MapDirection.EAST, new Vector2d(0, 0));
        Animal animalSouthOrientation = new Animal(MapDirection.SOUTH, new Vector2d(0, 0));
        Animal animalWestOrientation = new Animal(MapDirection.WEST, new Vector2d(0, 0));

        // When
        MapDirection orientationDefault = animalDefaultOrientation.getOrientation();
        MapDirection orientationNorth = animalNorthOrientation.getOrientation();
        MapDirection orientationEast = animalEastOrientation.getOrientation();
        MapDirection orientationSouth = animalSouthOrientation.getOrientation();
        MapDirection orientationWest = animalWestOrientation.getOrientation();

        // Then
        assertEquals(MapDirection.NORTH, orientationDefault);
        assertEquals(MapDirection.NORTH, orientationNorth);
        assertEquals(MapDirection.EAST, orientationEast);
        assertEquals(MapDirection.SOUTH, orientationSouth);
        assertEquals(MapDirection.WEST, orientationWest);
    }

    // testing Vector2d getPosition method
    @Test
    void gettingPosition() {
        // Given
        Animal animalDefaultPosition = new Animal();
        Animal animalPositivePosition = new Animal(MapDirection.NORTH, new Vector2d(3, 1));
        Animal animalNegativePosition = new Animal(MapDirection.NORTH, new Vector2d(-2, -4));
        Animal animalMixedPosition = new Animal(MapDirection.NORTH, new Vector2d(-2, 4));
        Animal animalZeroPosition = new Animal(MapDirection.NORTH, new Vector2d(0, 0));

        // When
        Vector2d positionDefault = animalDefaultPosition.getPosition();
        Vector2d positionPositive = animalPositivePosition.getPosition();
        Vector2d positionNegative = animalNegativePosition.getPosition();
        Vector2d positionMixed = animalMixedPosition.getPosition();
        Vector2d positionZero = animalZeroPosition.getPosition();

        // Then
        assertEquals(new Vector2d(2, 2), positionDefault);
        assertEquals(new Vector2d(3, 1), positionPositive);
        assertEquals(new Vector2d(-2, -4), positionNegative);
        assertEquals(new Vector2d(-2, 4), positionMixed);
        assertEquals(new Vector2d(0, 0), positionZero);
    }

    // testing String toString method
    @Test
    void creatingStringFromAnimal() {
        // Given
        Animal animalDefault = new Animal();
        Animal animalNorth = new Animal(MapDirection.NORTH, new Vector2d(3, 1));
        Animal animalEast = new Animal(MapDirection.EAST, new Vector2d(-2, -4));
        Animal animalSouth = new Animal(MapDirection.SOUTH, new Vector2d(-2, 4));
        Animal animalWest = new Animal(MapDirection.WEST, new Vector2d(0, 0));

        // When
        String defaultString = animalDefault.toString();
        String northString = animalNorth.toString();
        String eastString = animalEast.toString();
        String southString = animalSouth.toString();
        String westString = animalWest.toString();

        // Then
        assertEquals("N", defaultString);
        assertEquals("N", northString);
        assertEquals("E", eastString);
        assertEquals("S", southString);
        assertEquals("W", westString);
    }

    // testing boolean isAt method
    @Test
    void checkingIfAnimalIsAtGivenPosition() {
        // Given
        Animal animalDefaultPosition = new Animal();
        Animal animalPositivePosition = new Animal(MapDirection.NORTH, new Vector2d(3, 1));
        Animal animalNegativePosition = new Animal(MapDirection.NORTH, new Vector2d(-2, -4));
        Animal animalMixedPosition = new Animal(MapDirection.NORTH, new Vector2d(-2, 4));
        Animal animalZeroPosition = new Animal(MapDirection.NORTH, new Vector2d(0, 0));

        // Then
        assertTrue(animalDefaultPosition.isAt(new Vector2d(2, 2)));
        assertTrue(animalPositivePosition.isAt(new Vector2d(3, 1)));
        assertTrue(animalNegativePosition.isAt(new Vector2d(-2, -4)));
        assertTrue(animalMixedPosition.isAt(new Vector2d(-2, 4)));
        assertTrue(animalZeroPosition.isAt(new Vector2d(0, 0)));

        assertFalse(animalZeroPosition.isAt(new Vector2d(2, 2)));
        assertFalse(animalNegativePosition.isAt(new Vector2d(3, 1)));
        assertFalse(animalMixedPosition.isAt(new Vector2d(-2, -4)));
        assertFalse(animalPositivePosition.isAt(new Vector2d(-2, 4)));
        assertFalse(animalDefaultPosition.isAt(new Vector2d(0, 0)));
    }

    // testing switching directions by void move method
    @Test
    void changingAnimalOrientation() {
        // Given
        Animal animal = new Animal();
        RectangularMap map = new RectangularMap(5, 5);

        // When
        MapDirection initialOrientation = animal.getOrientation();
        // Turning RIGHT
        animal.move(MoveDirection.RIGHT, map);
        MapDirection oneRightTurn = animal.getOrientation();
        animal.move(MoveDirection.RIGHT, map);
        MapDirection twoRightTurns = animal.getOrientation();
        animal.move(MoveDirection.RIGHT, map);
        MapDirection threeRightTurns = animal.getOrientation();
        animal.move(MoveDirection.RIGHT, map);
        MapDirection fourRightTurns = animal.getOrientation();
        // Turning LEFT
        animal.move(MoveDirection.LEFT, map);
        MapDirection oneLeftTurn = animal.getOrientation();
        animal.move(MoveDirection.LEFT, map);
        MapDirection twoLeftTurns = animal.getOrientation();
        animal.move(MoveDirection.LEFT, map);
        MapDirection threeLeftTurns = animal.getOrientation();
        animal.move(MoveDirection.LEFT, map);
        MapDirection fourLeftTurns = animal.getOrientation();

        // Then
        assertEquals(MapDirection.NORTH, initialOrientation);
        // Turning RIGHT
        assertEquals(MapDirection.EAST, oneRightTurn);
        assertEquals(MapDirection.SOUTH, twoRightTurns);
        assertEquals(MapDirection.WEST, threeRightTurns);
        assertEquals(MapDirection.NORTH, fourRightTurns);
        // Turning LEFT
        assertEquals(MapDirection.WEST, oneLeftTurn);
        assertEquals(MapDirection.SOUTH, twoLeftTurns);
        assertEquals(MapDirection.EAST, threeLeftTurns);
        assertEquals(MapDirection.NORTH, fourLeftTurns);
    }

    // testing movement (FORWARD and BACKWARD) by move method within bounds
    @Test
    void changingAnimalPosition() {
        // Given
        Animal animal = new Animal();
        RectangularMap map = new RectangularMap(5, 5);

        // When
        Vector2d initialPosition = animal.getPosition();
        animal.move(MoveDirection.FORWARD, map);
        Vector2d positionAfterForward = animal.getPosition();
        animal.move(MoveDirection.BACKWARD, map);
        Vector2d positionAfterBackward = animal.getPosition();

        // Then
        assertEquals(new Vector2d(2, 2), initialPosition);
        assertEquals(new Vector2d(2, 3), positionAfterForward);
        assertEquals(new Vector2d(2, 2), positionAfterBackward);
    }

    // testing bounds using the move method
    @Test
    void checkingIfAnimalStaysWithinBounds() {
        // Given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animalTopLeftCorner = new Animal(MapDirection.NORTH, new Vector2d(0, 4));
        Animal animalBottomRightCorner = new Animal(MapDirection.SOUTH, new Vector2d(4, 0));

        // Then
        animalTopLeftCorner.move(MoveDirection.FORWARD, map);
        assertEquals(new Vector2d(0, 4), animalTopLeftCorner.getPosition());

        animalBottomRightCorner.move(MoveDirection.FORWARD, map);
        assertEquals(new Vector2d(4, 0), animalBottomRightCorner.getPosition());
    }
}
