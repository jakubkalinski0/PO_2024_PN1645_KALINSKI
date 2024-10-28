package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MapDirection;
import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args){
        // first version of main method
        // System.out.println("System wystartował");
        // MoveDirection[] directions = Parse(args);
        // run(directions);
        // System.out.println("System zakończył działanie");

        // Vector2d test
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        // MapDirection test
        System.out.println("Kierunek NORTH: " + MapDirection.NORTH.toString());
        System.out.println("Kierunek EAST: " + MapDirection.EAST.toString());
        System.out.println("Następny po NORTH: " + MapDirection.NORTH.next());
        System.out.println("Następny po EAST: " + MapDirection.EAST.next());
        System.out.println("Poprzedni po NORTH: " + MapDirection.NORTH.previous());
        System.out.println("Poprzedni po EAST: " + MapDirection.EAST.previous());
        System.out.println("Jednostkowy wektor NORTH: " + MapDirection.NORTH.toUnitVector());
        System.out.println("Jednostkowy wektor EAST: " + MapDirection.EAST.toUnitVector());
    }
    public static void run(MoveDirection[] directions){
        for (MoveDirection arg : directions) {
            String output = switch (arg) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak idzie w prawo";
                case LEFT -> "Zwierzak idzie w lewo";
            };
            System.out.println(output);
        }
    }
}