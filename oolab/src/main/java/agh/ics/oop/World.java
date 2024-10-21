package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import static agh.ics.oop.OptionsParser.Parse;

public class World {
    public static void main(String[] args){
        System.out.println("System wystartował");
        MoveDirection[] directions = Parse(args);
        run(directions);
        System.out.println("System zakończył działanie");
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