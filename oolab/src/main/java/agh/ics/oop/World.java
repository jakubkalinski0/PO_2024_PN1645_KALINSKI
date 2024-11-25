package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.Observer;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    //f b r l f f r r f f f f f f f f
    public static void main(String[] args){
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

            AbstractWorldMap map = new GrassField(10);
            map.addObserver(new ConsoleMapDisplay());

            Simulation simulation = new Simulation(positions, directions, map);
            simulation.run();
            System.out.println(map);
        }
        catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("Koniec działania");
    }
}