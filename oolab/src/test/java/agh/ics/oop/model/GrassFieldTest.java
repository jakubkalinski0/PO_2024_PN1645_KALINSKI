package agh.ics.oop.model;

import static org.junit.jupiter.api.Assertions.*;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

// testy:
// isOccupiedWhenPositionIsOccupiedByGrass
// mapToString
// objectAtWhenPositionIsOccupiedByGrass
// zostały wykomentowane przez wzgląd na to, że przy deterministycznym losowaniu pozycji trawy nie da się ustawić
// wartości random.setseed() w związku z czym wyniki tych testów za każdym razem będą inne

class GrassFieldTest {

    // TESTY METODY PLACE

    @Test
    void placeAnimalOnFreePosition() {
        //given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(2, 2));

        //when
        try {
        map.place(animal);
        }
        catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        //then
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void placeAnimalOnOccupiedPosition() {
        // given
        GrassField map = new GrassField(0);
        Animal animal1 = new Animal(MapDirection.NORTH, new Vector2d(2, 2));
        Animal animal2 = new Animal(MapDirection.NORTH, new Vector2d(2, 2));

        // when
        try {
            map.place(animal1);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception when placing the first animal: " + e.getMessage());
        }

        // then - expect exception when placing the second animal
        assertThrows(IncorrectPositionException.class, () -> map.place(animal2));

        // and ensure the first animal is still at the position
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
    }


    @Test
    void placeAnimalOnGrassPosition() {
        //given
        GrassField map = new GrassField(1);
        Vector2d grassPosition = new Vector2d(1, 0);
        Animal animal = new Animal(MapDirection.NORTH, grassPosition);

        //when
        try {
        map.place(animal);
        }
        catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        //then
        assertEquals(animal, map.objectAt(grassPosition));
    }

    // TESTY METODY CANMOVETO

    @Test
    void canMoveToFreePosition() {
        //given
        GrassField map = new GrassField(0);

        //when
        boolean result = map.canMoveTo(new Vector2d(2, 2));

        //then
        assertTrue(result);
    }

    @Test
    void canMoveToOccupiedPosition() {
        //given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(2, 2));
        try {
        map.place(animal);
        }
        catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        //when
        boolean result = map.canMoveTo(new Vector2d(2, 2));

        //then
        assertFalse(result);
    }

    @Test
    void canMoveToGrassPosition() {
        //given
        GrassField map = new GrassField(1);
        Vector2d grassPosition = new Vector2d(1, 0);

        //when
        boolean result = map.canMoveTo(grassPosition);

        //then
        assertTrue(result);
    }

    // TESTY METODY ISOCCUPIED

    @Test
    void isOccupiedWhenPositionIsOccupiedByAnimal() {
        //given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(2, 2));
        try {
        map.place(animal);
        }
        catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        //when
        boolean result = map.isOccupied(new Vector2d(2, 2));

        //then
        assertTrue(result);
    }

//    @Test
//    void isOccupiedWhenPositionIsOccupiedByGrass() {
//        //given
//        GrassField map = new GrassField(1);
//        Vector2d grassPosition = new Vector2d(2, 3);
//
//        //when
//        boolean result = map.isOccupied(grassPosition);
//
//        //then
//        assertTrue(result);
//    }

    @Test
    void isOccupiedWhenPositionIsFree() {
        //given
        GrassField map = new GrassField(0);

        //when
        boolean result = map.isOccupied(new Vector2d(2, 2));

        //then
        assertFalse(result);
    }

    // TESTY METODY OBJECTAT

    @Test
    void objectAtWhenPositionIsOccupiedByAnimal() {
        //given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(2, 2));
        try {
        map.place(animal);
        }
        catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        //when
        WorldElement result = map.objectAt(new Vector2d(2, 2));

        //then
        assertEquals(animal, result);
    }

//    @Test
//    void objectAtWhenPositionIsOccupiedByGrass() {
//        //given
//        GrassField map = new GrassField(1);
//        System.out.println(map);
//        Vector2d grassPosition = new Vector2d(2,3);
//
//        //when
//        WorldElement result = map.objectAt(grassPosition);
//
//        //then
//        assertInstanceOf(Grass.class, result);
//    }

    @Test
    void objectAtWhenPositionIsFree() {
        //given
        GrassField map = new GrassField(0);

        //when
        WorldElement result = map.objectAt(new Vector2d(2, 2));

        //then
        assertNull(result);
    }

    // TESTY METODY MOVE

    @Test
    void moveAnimalToFreePosition() {
        //given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(2, 2));
        try {
        map.place(animal);
        }
        catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        //when
        map.move(animal, MoveDirection.RIGHT);
        map.move(animal, MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(3, 2), animal.getPosition());
        assertEquals(animal, map.objectAt(new Vector2d(3, 2)));
        assertNull(map.objectAt(new Vector2d(2, 2)));
    }

    // TEST WYŚWIETLANIA MAPY

//    @Test
//    void mapToString() {
//        //given
//        GrassField map = new GrassField(2);
//        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(2, 2));
//        map.place(animal);
//
//        //when
//        String mapString = map.toString();
//
//        //then
//        assertNotNull(mapString);
//        assertTrue(mapString.contains("N"));
//        assertTrue(mapString.contains("*"));
//    }
}