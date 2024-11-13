package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap map) {
        this.directions = directions;
        this.animals = new ArrayList<>();
        this.map = map;

        for (Vector2d position : positions) {
            Animal newAnimal = new Animal(MapDirection.NORTH, position);
            if (map.place(newAnimal)) {
                this.animals.add(newAnimal);
            }
        }
    }

    public void run() {
        int numberOfAnimals = animals.size();
        System.out.println(map);
        for (int i = 0; i < directions.size(); i++) {
            Animal currentAnimal = animals.get(i%numberOfAnimals);
            map.move(currentAnimal, directions.get(i));
            System.out.println(map);
        }
    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }
}