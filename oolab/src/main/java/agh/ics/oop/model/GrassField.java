package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;

/**
 * Represents a grass field in the world, which contains a collection of grass patches
 * and animals, and allows for managing and visualizing the state of the field.
 *
 * This class extends `AbstractWorldMap` and provides specific behavior for the grass field,
 * including the placement of grass and animals, boundary calculation, and map visualization.
 */
public class GrassField extends AbstractWorldMap {
    private final int grassCount;                       // The number of grass patches on the field
    protected Vector2d lowerLeft;                       // The bottom-left corner of the map
    protected Vector2d upperRight;                      // The top-right corner of the map
    private final Map<Vector2d, Grass> grassMap;        // A map holding grass patches by their positions
    private final Map<Vector2d, Animal> animalMap;      // A map holding animals by their positions
    private final MapVisualizer mapVisualizer;          // An object for visualizing the map

    /**
     * Constructor for the grass field.
     *
     * Initializes the field with a given number of grass patches, sets the default
     * boundaries to the extreme possible values, and places the grass randomly on the field.
     *
     * @param grassCount        The number of grass patches to place randomly on the field.
     */
    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        this.lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE); // Default bottom-left corner
        this.upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE); // Default top-right corner
        this.grassMap = new HashMap<>();
        placeGrassRandomly(grassCount); // Place the grass patches randomly
        this.animalMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this); // Initialize the map visualizer
    }

    /**
     * Places the specified number of grass patches randomly on the field.
     *
     * This method uses a `RandomPositionGenerator` to generate random positions for grass
     * patches and adds them to the `grassMap`.
     *
     * @param grassCount        The number of grass patches to place.
     */
    private void placeGrassRandomly(int grassCount) {
        int maxRange = (int) Math.sqrt(grassCount * 10); // Calculate a reasonable range for random placement
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxRange, maxRange, grassCount);

        // Loop through the generated positions and add them to the grass map
        for (Vector2d grassPosition : randomPositionGenerator) {
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    /**
     * Returns the object at a specific position on the map.
     *
     * This method checks if there is an animal at the specified position, and if not,
     * it returns the grass at that position (if any).
     *
     * @param position          The position to check.
     * @return                  The object at the specified position (either an animal or grass).
     */
    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement animal = super.objectAt(position); // Check for animals first
        if (animal == null) {
            return grassMap.get(position); // If no animal, check for grass
        }
        return animal; // Return the animal if one is found
    }

    /**
     * Returns a collection of all world elements (animals and grass) on the map.
     *
     * This method adds all grass patches to the collection of elements and returns it.
     *
     * @return                  A collection of all world elements (animals and grass) on the map.
     */
    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = super.getElements(); // Get animals from the superclass
        elements.addAll(grassMap.values()); // Add all grass patches to the collection
        return elements;
    }

    /**
     * Returns the current bounds of the grass field.
     *
     * This method calculates the bounds of the map by considering the positions of all
     * world elements (animals and grass), ensuring that the boundaries expand to encompass
     * all objects on the field.
     *
     * @return                  A `Boundary` object representing the current bounds of the field.
     */
    @Override
    public Boundary getCurrentBounds() {
        // Start with the extreme corners
        Vector2d mapLeftBottom = new Vector2d(upperRight.getX(), upperRight.getY());
        Vector2d mapRightTop = new Vector2d(lowerLeft.getX(), lowerLeft.getY());

        // Get all elements on the field
        Collection<WorldElement> elements = getElements();

        // Adjust the boundaries to encompass all elements
        for (WorldElement element : elements) {
            mapLeftBottom = mapLeftBottom.lowerLeft(element.getPosition());
            mapRightTop = mapRightTop.upperRight(element.getPosition());
        }

        return new Boundary(mapLeftBottom, mapRightTop); // Return the calculated boundaries
    }
}