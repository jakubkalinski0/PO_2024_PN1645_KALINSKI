package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args){
        WorldMap map = new GrassField(10);
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        System.out.println(map);
    }
}