package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MoveDirection;

import java.util.Vector;

class AnimalTest {

    // testing MapDirection getDirection method
    @Test
    void gettingDirection() {
        // Given
        Animal animalDefaultDirection = new Animal();
        Animal animalNorthDirection = new Animal(MapDirection.NORTH, new Vector2d(0,0));
        Animal animalEastDirection = new Animal(MapDirection.EAST, new Vector2d(0,0));
        Animal animalSouthDirection = new Animal(MapDirection.SOUTH, new Vector2d(0,0));
        Animal animalWestDirection = new Animal(MapDirection.WEST, new Vector2d(0,0));

        // When
        MapDirection mapDirectionDefaultDirection = animalDefaultDirection.getDirection();
        MapDirection mapDirectionNorthDirection = animalNorthDirection.getDirection();
        MapDirection mapDirectionEastDirection = animalEastDirection.getDirection();
        MapDirection mapDirectionSouthDirection = animalSouthDirection.getDirection();
        MapDirection mapDirectionWestDirection = animalWestDirection.getDirection();

        // Then
        assertEquals(MapDirection.NORTH, mapDirectionDefaultDirection);
        assertEquals(MapDirection.NORTH, mapDirectionNorthDirection);
        assertEquals(MapDirection.EAST, mapDirectionEastDirection);
        assertEquals(MapDirection.SOUTH, mapDirectionSouthDirection);
        assertEquals(MapDirection.WEST, mapDirectionWestDirection);
    }

    // testing Vector2d getPosition method
    @Test
    void gettingPostition() {
        // Given
        Animal animalDefaultPosition = new Animal();
        Animal animalPositivePosition= new Animal(MapDirection.NORTH, new Vector2d(3,1));
        Animal animalNegativePosition = new Animal(MapDirection.NORTH, new Vector2d(-2,-4));
        Animal animalMixedPosition = new Animal(MapDirection.NORTH, new Vector2d(-2,4));
        Animal animalZeroPosition = new Animal(MapDirection.NORTH, new Vector2d(0,0));


        // When
        Vector2d vectorAnimalDefaultPosition = animalDefaultPosition.getPosition();
        Vector2d vectorAnimalPositivePosition = animalPositivePosition.getPosition();
        Vector2d vectorAnimalNegativePosition = animalNegativePosition.getPosition();
        Vector2d vectorAnimalMixedPosition = animalMixedPosition.getPosition();
        Vector2d vectorAnimalZeroPosition = animalZeroPosition.getPosition();


        // Then
        assertEquals(new Vector2d(2,2), vectorAnimalDefaultPosition);
        assertEquals(new Vector2d(3,1), vectorAnimalPositivePosition);
        assertEquals(new Vector2d(-2,-4), vectorAnimalNegativePosition);
        assertEquals(new Vector2d(-2,4), vectorAnimalMixedPosition);
        assertEquals(new Vector2d(0,0), vectorAnimalZeroPosition);
    }

    // testing String toString method
    @Test
    void creatingStringFromAnimal() {
        // Given
        Animal animalDefaultDirectionDefaultPosition = new Animal();
        Animal animalNorthDirectionPositivePosition= new Animal(MapDirection.NORTH, new Vector2d(3,1));
        Animal animalEastDirectionNegativePosition = new Animal(MapDirection.EAST, new Vector2d(-2,-4));
        Animal animalSouthDirectionMixedPosition = new Animal(MapDirection.SOUTH, new Vector2d(-2,4));
        Animal animalWestDirectionZeroPosition = new Animal(MapDirection.WEST, new Vector2d(0,0));

        // When
        String vectorAnimalDefaultDirectionDefaultPosition = animalDefaultDirectionDefaultPosition.toString();
        String vectorAnimalNorthDirectionPositivePosition = animalNorthDirectionPositivePosition.toString();
        String vectorAnimalEastDirectionNegativePosition = animalEastDirectionNegativePosition.toString();
        String vectorAnimalSouthDirectionMixedPosition = animalSouthDirectionMixedPosition.toString();
        String vectorAnimalWestDirectionZeroPosition = animalWestDirectionZeroPosition.toString();

        // Then
        assertEquals("Direction: Północ, Position: (2, 2)", vectorAnimalDefaultDirectionDefaultPosition);
        assertEquals("Direction: Północ, Position: (3, 1)", vectorAnimalNorthDirectionPositivePosition);
        assertEquals("Direction: Wschód, Position: (-2, -4)", vectorAnimalEastDirectionNegativePosition);
        assertEquals("Direction: Południe, Position: (-2, 4)", vectorAnimalSouthDirectionMixedPosition);
        assertEquals("Direction: Zachód, Position: (0, 0)", vectorAnimalWestDirectionZeroPosition);
    }

    // testing boolean isAt method
    @Test
    void checkingIfAnimalIsAtGivenPosition() {
        // Given
        Animal animalDefaultPosition = new Animal();
        Animal animalPositivePosition= new Animal(MapDirection.NORTH, new Vector2d(3,1));
        Animal animalNegativePosition = new Animal(MapDirection.NORTH, new Vector2d(-2,-4));
        Animal animalMixedPosition = new Animal(MapDirection.NORTH, new Vector2d(-2,4));
        Animal animalZeroPosition = new Animal(MapDirection.NORTH, new Vector2d(0,0));

        // Then
        assertTrue(animalDefaultPosition.isAt(new Vector2d(2,2)));
        assertTrue(animalPositivePosition.isAt(new Vector2d(3,1)));
        assertTrue(animalNegativePosition.isAt(new Vector2d(-2,-4)));
        assertTrue(animalMixedPosition.isAt(new Vector2d(-2,4)));
        assertTrue(animalZeroPosition.isAt(new Vector2d(0,0)));

        assertFalse(animalZeroPosition.isAt(new Vector2d(2,2)));
        assertFalse(animalNegativePosition.isAt(new Vector2d(3,1)));
        assertFalse(animalMixedPosition.isAt(new Vector2d(-2,-4)));
        assertFalse(animalPositivePosition.isAt(new Vector2d(-2,4)));
        assertFalse(animalDefaultPosition.isAt(new Vector2d(0,0)));
    }

    // testing switching directions by void move method
    @Test
    void changingAnimalDirection() {
        // Given
        Animal animal = new Animal();

        // When
        MapDirection mapDirectionDefaultDirection = animal.getDirection();
        // Turning RIGHT
        animal.move(MoveDirection.RIGHT);
        MapDirection mapDirectionOneRightTurn = animal.getDirection();
        animal.move(MoveDirection.RIGHT);
        MapDirection mapDirectionTwoRightTurns = animal.getDirection();
        animal.move(MoveDirection.RIGHT);
        MapDirection mapDirectionThreeRightTurns = animal.getDirection();
        animal.move(MoveDirection.RIGHT);
        MapDirection mapDirectionFourRightTurns = animal.getDirection();
        // Turning LEFT
        animal.move(MoveDirection.LEFT);
        MapDirection mapDirectionOneLeftTurn = animal.getDirection();
        animal.move(MoveDirection.LEFT);
        MapDirection mapDirectionTwoLeftTurns = animal.getDirection();
        animal.move(MoveDirection.LEFT);
        MapDirection mapDirectionThreeLeftTurns = animal.getDirection();
        animal.move(MoveDirection.LEFT);
        MapDirection mapDirectionFourLeftTurns = animal.getDirection();

        // Then
        assertEquals(MapDirection.NORTH, mapDirectionDefaultDirection);
        // Turning RIGHT
        assertEquals(MapDirection.EAST, mapDirectionOneRightTurn);
        assertEquals(MapDirection.SOUTH, mapDirectionTwoRightTurns);
        assertEquals(MapDirection.WEST, mapDirectionThreeRightTurns);
        assertEquals(MapDirection.NORTH, mapDirectionFourRightTurns);
        // Turning LEFT
        assertEquals(MapDirection.WEST, mapDirectionOneLeftTurn);
        assertEquals(MapDirection.SOUTH, mapDirectionTwoLeftTurns);
        assertEquals(MapDirection.EAST, mapDirectionThreeLeftTurns);
        assertEquals(MapDirection.NORTH, mapDirectionFourLeftTurns);
    }

    // testing moving FORWARD and BACKWARD by void move method
    @Test
    void changingAnimalPosition() {
        // Given
        Animal animal = new Animal();

        // When
        Vector2d vectorAnimalDefaultPosition = animal.getPosition();
        // Moving FORWARD once from default position to each direction, changing direction to RIGHT after each FORWARD move
        animal.move(MoveDirection.FORWARD);
        Vector2d vectorAnimalRIGHT0FORWARDPosition = animal.getPosition();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        Vector2d vectorAnimalRIGHT1FORWARDPosition = animal.getPosition();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        Vector2d vectorAnimalRIGHT2FORWARDPosition = animal.getPosition();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        Vector2d vectorAnimalRIGHT3FORWARDPosition = animal.getPosition();
        // changing direction to default again
        animal.move(MoveDirection.RIGHT);
        // Moving BACKWARD once from default position to each direction, changing direction to LEFT after each BACKWARD move
        animal.move(MoveDirection.BACKWARD);
        Vector2d vectorAnimalLEFT0BACKWARDPosition = animal.getPosition();
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        Vector2d vectorAnimalLEFT1BACKWARDPosition = animal.getPosition();
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        Vector2d vectorAnimalLEFT2BACKWARDPosition = animal.getPosition();
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        Vector2d vectorAnimalLEFT3BACKWARDPosition = animal.getPosition();

        // Then
        assertEquals(new Vector2d(2,2), vectorAnimalDefaultPosition);
        // Moving FORWARD once from default position to each direction, changing direction to RIGHT after each FORWARD move
        assertEquals(new Vector2d(2,3), vectorAnimalRIGHT0FORWARDPosition);
        assertEquals(new Vector2d(3,3), vectorAnimalRIGHT1FORWARDPosition);
        assertEquals(new Vector2d(3,2), vectorAnimalRIGHT2FORWARDPosition);
        assertEquals(new Vector2d(2,2), vectorAnimalRIGHT3FORWARDPosition);
        // Moving BACKWARD once from default position to each direction, changing direction to LEFT after each BACKWARD move
        assertEquals(new Vector2d(2,1), vectorAnimalLEFT0BACKWARDPosition);
        assertEquals(new Vector2d(3,1), vectorAnimalLEFT1BACKWARDPosition);
        assertEquals(new Vector2d(3,2), vectorAnimalLEFT2BACKWARDPosition);
        assertEquals(new Vector2d(2,2), vectorAnimalLEFT3BACKWARDPosition);
    }

    @Test
    void checkingIfAnimalStaysWithinBounds() {
        // Given
        Animal animalTopLeftCorner = new Animal(MapDirection.NORTH, new Vector2d(0,4));
        Animal animalTopRightCorner = new Animal(MapDirection.NORTH, new Vector2d(4,4));
        Animal animalBottomLeftCorner = new Animal(MapDirection.SOUTH, new Vector2d(0,0));
        Animal animalBottomRightCorner = new Animal(MapDirection.SOUTH, new Vector2d(4,0));

        // When
        Vector2d vectorTopLeftCorner = animalTopLeftCorner.getPosition();
        // Trying to get animal out of bounds from the top left corner by changing directions and moving
        animalTopLeftCorner.move(MoveDirection.FORWARD);
        Vector2d vectorTopLeftCornerMovingNORTHByFORWARD = animalTopLeftCorner.getPosition();
        animalTopLeftCorner.move(MoveDirection.LEFT);
        animalTopLeftCorner.move(MoveDirection.FORWARD);
        Vector2d vectorTopLeftCornerMovingWESTByFORWARD = animalTopLeftCorner.getPosition();
        animalTopLeftCorner.move(MoveDirection.LEFT);
        animalTopLeftCorner.move(MoveDirection.BACKWARD);
        Vector2d vectorTopLeftCornerMovingNORTHByBACKWARD = animalTopLeftCorner.getPosition();
        animalTopLeftCorner.move(MoveDirection.LEFT);
        animalTopLeftCorner.move(MoveDirection.BACKWARD);
        Vector2d vectorTopLeftCornerMovingWESTByBACKWARD = animalTopLeftCorner.getPosition();

        Vector2d vectorTopRightCorner = animalTopRightCorner.getPosition();
        // Trying to get animal out of bounds from the top right corner by changing directions and moving
        animalTopRightCorner.move(MoveDirection.FORWARD);
        Vector2d vectorTopRightCornerMovingNORTHByFORWARD = animalTopRightCorner.getPosition();
        animalTopRightCorner.move(MoveDirection.RIGHT);
        animalTopRightCorner.move(MoveDirection.FORWARD);
        Vector2d vectorTopRightCornerMovingEASTByFORWARD = animalTopRightCorner.getPosition();
        animalTopRightCorner.move(MoveDirection.RIGHT);
        animalTopRightCorner.move(MoveDirection.BACKWARD);
        Vector2d vectorTopRightCornerMovingNORTHByBACKWARD = animalTopRightCorner.getPosition();
        animalTopRightCorner.move(MoveDirection.RIGHT);
        animalTopRightCorner.move(MoveDirection.BACKWARD);
        Vector2d vectorTopRightCornerMovingEASTByBACKWARD = animalTopRightCorner.getPosition();

        Vector2d vectorBottomRightCorner = animalBottomRightCorner.getPosition();
        // Trying to get animal out of bounds from the bottom right corner by changing directions and moving
        animalBottomRightCorner.move(MoveDirection.FORWARD);
        Vector2d vectorBottomRightCornerMovingSOUTHByFORWARD = animalBottomRightCorner.getPosition();
        animalBottomRightCorner.move(MoveDirection.LEFT);
        animalBottomRightCorner.move(MoveDirection.FORWARD);
        Vector2d vectorBottomRightCornerMovingEASTByFORWARD = animalBottomRightCorner.getPosition();
        animalBottomRightCorner.move(MoveDirection.LEFT);
        animalBottomRightCorner.move(MoveDirection.BACKWARD);
        Vector2d vectorBottomRightCornerMovingSOUTHByBACKWARD = animalBottomRightCorner.getPosition();
        animalBottomRightCorner.move(MoveDirection.LEFT);
        animalBottomRightCorner.move(MoveDirection.BACKWARD);
        Vector2d vectorBottomRightCornerMovingEASTByBACKWARD = animalBottomRightCorner.getPosition();

        Vector2d vectorBottomLeftCorner = animalBottomLeftCorner.getPosition();
        // Trying to get animal out of bounds from the bottom left corner by changing directions and moving
        animalBottomLeftCorner.move(MoveDirection.FORWARD);
        Vector2d vectorBottomLeftCornerMovingSOUTHByFORWARD = animalBottomLeftCorner.getPosition();
        animalBottomLeftCorner.move(MoveDirection.RIGHT);
        animalBottomLeftCorner.move(MoveDirection.FORWARD);
        Vector2d vectorBottomLeftCornerMovingWESTByFORWARD = animalBottomLeftCorner.getPosition();
        animalBottomLeftCorner.move(MoveDirection.RIGHT);
        animalBottomLeftCorner.move(MoveDirection.BACKWARD);
        Vector2d vectorBottomLeftCornerMovingSOUTHByBACKWARD = animalBottomLeftCorner.getPosition();
        animalBottomLeftCorner.move(MoveDirection.RIGHT);
        animalBottomLeftCorner.move(MoveDirection.BACKWARD);
        Vector2d vectorBottomLeftCornerMovingWESTByBACKWARD = animalBottomLeftCorner.getPosition();

        // Then
        // Trying to get animal out of bounds from the top left corner by changing directions and moving
        assertEquals(new Vector2d(0,4), vectorTopLeftCorner);
        assertEquals(new Vector2d(0,4), vectorTopLeftCornerMovingNORTHByFORWARD);
        assertEquals(new Vector2d(0,4), vectorTopLeftCornerMovingWESTByFORWARD);
        assertEquals(new Vector2d(0,4), vectorTopLeftCornerMovingNORTHByBACKWARD);
        assertEquals(new Vector2d(0,4), vectorTopLeftCornerMovingWESTByBACKWARD);
        // Trying to get animal out of bounds from the top right corner by changing directions and moving
        assertEquals(new Vector2d(4,4), vectorTopRightCorner);
        assertEquals(new Vector2d(4,4), vectorTopRightCornerMovingNORTHByFORWARD);
        assertEquals(new Vector2d(4,4), vectorTopRightCornerMovingEASTByFORWARD);
        assertEquals(new Vector2d(4,4), vectorTopRightCornerMovingNORTHByBACKWARD);
        assertEquals(new Vector2d(4,4), vectorTopRightCornerMovingEASTByBACKWARD);
        // Trying to get animal out of bounds from the bottom right corner by changing directions and moving
        assertEquals(new Vector2d(4,0), vectorBottomRightCorner);
        assertEquals(new Vector2d(4,0), vectorBottomRightCornerMovingSOUTHByFORWARD);
        assertEquals(new Vector2d(4,0), vectorBottomRightCornerMovingEASTByFORWARD);
        assertEquals(new Vector2d(4,0), vectorBottomRightCornerMovingSOUTHByBACKWARD);
        assertEquals(new Vector2d(4,0), vectorBottomRightCornerMovingEASTByBACKWARD);
        // Trying to get animal out of bounds from the bottom left corner by changing directions and moving
        assertEquals(new Vector2d(0,0), vectorBottomLeftCorner);
        assertEquals(new Vector2d(0,0), vectorBottomLeftCornerMovingSOUTHByFORWARD);
        assertEquals(new Vector2d(0,0), vectorBottomLeftCornerMovingWESTByFORWARD);
        assertEquals(new Vector2d(0,0), vectorBottomLeftCornerMovingSOUTHByBACKWARD);
        assertEquals(new Vector2d(0,0), vectorBottomLeftCornerMovingWESTByBACKWARD);
    }
}