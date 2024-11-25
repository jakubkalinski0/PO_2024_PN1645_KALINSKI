package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.*;

abstract class AbstractWorldMap implements WorldMap {
    protected Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected final Map<Vector2d, Animal> animalMap;
    protected final MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        this.animalMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        // position.follows(lowerLeft) && position.precedes(upperRight) - ta część tylko w rectangular - do poprawy
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())) {
            animalMap.put(animal.getPosition(), animal);
            return true;
        }
        throw new IncorrectPositionException(animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection orientation) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(orientation, this);
        animalMap.remove(oldPosition);
        animalMap.put(animal.getPosition(), animal);
    }

    @Override
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animalMap.values());
    }

    @Override
    public Boundary getBoundary() {
        return new Boundary(lowerLeft, upperRight);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(getBoundary().lowerLeft(), getBoundary().upperRight());
    }
}