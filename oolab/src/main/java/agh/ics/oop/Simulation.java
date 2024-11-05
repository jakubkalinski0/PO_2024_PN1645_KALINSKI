package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MoveDirection;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
//    private final List<Vector2d> positions; not needed because it isn't used later as it only serves to create animals

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions) {
//        this.positions = positions; not needed because it isn't used later as it only serves to create animals
        this.directions = directions;
        this.animals = new ArrayList<>();

        for (Vector2d position : positions) {
            Animal newAnimal = new Animal(MapDirection.NORTH, position);
            this.animals.add(newAnimal);
        }
    }

    public void run() {
        int numberOfAnimals = animals.size();
        for (int i = 0; i < directions.size(); i++) {
            Animal currentAnimal = animals.get(i%numberOfAnimals);
            currentAnimal.move(directions.get(i));
            System.out.println("Zwierzę " + i%numberOfAnimals + ": " + currentAnimal);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}