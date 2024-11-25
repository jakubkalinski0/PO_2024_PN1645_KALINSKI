package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;
//import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
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
//        Random random = new Random();
        int maxRange = (int) Math.sqrt(grassCount*10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxRange, maxRange, grassCount);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement animal = super.objectAt(position);
        if (animal == null) {
            return grassMap.get(position);
        }
        return animal;
    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = super.getElements();
        elements.addAll(grassMap.values());
        return elements;
    }


    @Override
    public Boundary getCurrentBounds() {
        Vector2d mapLeftBottom = new Vector2d(upperRight.getX(), upperRight.getY());
        Vector2d mapRightTop = new Vector2d(lowerLeft.getX(), lowerLeft.getY());
        Collection<WorldElement> elements = getElements();
        for (WorldElement element : elements) {
            mapLeftBottom = mapLeftBottom.lowerLeft(element.getPosition());
            mapRightTop = mapRightTop.upperRight(element.getPosition());
        }

        return new Boundary(mapLeftBottom, mapRightTop);
    }
}
