package agh.ics.oop;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MapDirection;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args){
        // creating animal and returning its position
        Animal animal = new Animal();
        System.out.println(animal.getPosition());

        // first version of main method used second time with adaptation to List<>
//        System.out.println("System wystartował");
//        List<MoveDirection> directions = parse(args);
//        run(directions);
//        System.out.println("System zakończył działanie");

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
    }

    public static void run(List<MoveDirection> directions){
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