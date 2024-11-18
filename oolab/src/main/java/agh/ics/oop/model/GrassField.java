package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {
    private final int grassCount;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    private final Map<Vector2d, Grass> grassMap;
    private final Map<Vector2d, Animal> animalMap;
    private final MapVisualizer mapVisualizer;

    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        this.lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.grassMap = new HashMap<>();
        placeGrassRandomly(grassCount);
        this.animalMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);
    }

    private void placeGrassRandomly(int grassCount) {
        Random random = new Random();
        int maxRange = (int) Math.sqrt(grassCount*10);

        while (grassMap.size() < grassCount) {
            int x = random.nextInt(maxRange+1);
            int y = random.nextInt(maxRange+1);
            Vector2d position = new Vector2d(x, y);

            if (!grassMap.containsKey(position)) {
                grassMap.put(position, new Grass(position));
            }
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animalMap.get(position) != null) {return animalMap.get(position);}
        if (grassMap.get(position) != null) {return grassMap.get(position);}
        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animalMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection orientation) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(orientation, this);
        animalMap.remove(oldPosition);
        animalMap.put(animal.getPosition(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public String toString() {
        if (animalMap.isEmpty() && grassMap.isEmpty()) {
            System.out.println("Puste");
            return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(0,0));
        }
        Vector2d mapLeftBottom = new Vector2d(upperRight.getX(), upperRight.getY());
        Vector2d mapRightTop = new Vector2d(lowerLeft.getX(), lowerLeft.getY());
        for (WorldElement animal : animalMap.values()) {
            mapLeftBottom = mapLeftBottom.lowerLeft(animal.getPosition());
            mapRightTop = mapRightTop.upperRight(animal.getPosition());
        }
        for (WorldElement grass : grassMap.values()) {
            mapLeftBottom = mapLeftBottom.lowerLeft(grass.getPosition());
            mapRightTop = mapRightTop.upperRight(grass.getPosition());
        }
        System.out.println(mapLeftBottom);
        System.out.println(mapRightTop);
        return mapVisualizer.draw(mapLeftBottom, mapRightTop);
    }
}
