package agh.ics.oop.model;
import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    public void DoesMapWorkBasic(){
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal();
        try {
        map.place(animal);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void isPlaceAvailableToPutThereAnimal(){
        RectangularMap map = new RectangularMap(100, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 2));
        try {
        assertTrue(map.place(animal1));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertThrows(IncorrectPositionException.class, () -> map.place(animal2));
    }

    @Test
    public void isPlaceOccupied(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 3));
        try {
            map.place(animal1);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            map.place(animal2);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(2, 4)));
        assertFalse(map.isOccupied(new Vector2d(3, 2)));
    }

    @Test
    public void isObjectAt(){
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.NORTH, new Vector2d(2, 3));
        try {
        map.place(animal1);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
        map.place(animal2);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
        assertEquals(animal2, map.objectAt(new Vector2d(2, 3)));
    }

    @Test
    public void CanMoveToOccupied(){
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal();
        try {
        map.place(animal1);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void MovingAnimals(){
        RectangularMap map = new RectangularMap(55, 4);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(3, 2));
        try {
        map.place(animal1);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
        map.place(animal2);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal1.getPosition());
        assertEquals(new Vector2d(3, 1), animal2.getPosition());
    }

    @Test
    public void CanMoveToOccupiedByOtherAnimal(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 3));
        try {
            map.place(animal1);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            map.place(animal2);
        }
        catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
    }

    // brak testow do visualizera - zakladam ze dziala
}