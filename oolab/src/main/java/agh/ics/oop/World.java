package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    //f b r l f f r r f f f f f f f f
    public static void main(String[] args){
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

            AbstractWorldMap map1 = new GrassField(10);
            AbstractWorldMap map2 = new RectangularMap(5, 5);
            map1.addObserver(new ConsoleMapDisplay());
            map2.addObserver(new ConsoleMapDisplay());
            Simulation simulation1 = new Simulation(positions, directions, map1);
            Simulation simulation2 = new Simulation(positions, directions, map2);
            SimulationEngine engine = new SimulationEngine(List.of(simulation1, simulation2));
//            engine.runSync();
//            engine.runAsync();
            engine.runAsyncInThreadPool();

//            List<MoveDirection> directions = parse(args);
//            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
//            List<Simulation> simulations = new ArrayList<>();
//
//            for (int i = 0; i < 1000; i++) {
//                AbstractWorldMap map;
//                if (i%2 == 0) {
//                    map = new GrassField(10);
//                }
//                else {
//                    map = new RectangularMap(5,5);
//                }
//                map.addObserver(new ConsoleMapDisplay());
//                Simulation simulation = new Simulation(positions, directions, map);
//                simulations.add(simulation);
//            }
//
//            SimulationEngine engine = new SimulationEngine(simulations);
//            engine.runAsync();
        }
        catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("Koniec działania");
    }
}