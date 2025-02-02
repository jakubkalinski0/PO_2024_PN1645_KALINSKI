package agh.ics.oop.model;

import java.io.Console;

/**
 * This class implements the `MapChangeListener` interface and is responsible
 * for displaying updates to the map in the console.
 *
 * The `mapChanged` method is triggered whenever there is a change in the map.
 * It prints the update message and the current state of the map.
 */
public class ConsoleMapDisplay implements MapChangeListener {
    private int changeCount = 0;  // Counter to keep track of the number of map updates

    /**
     * Called when the map changes. This method prints the update message,
     * the number of updates, and the current state of the map.
     *
     * @param worldMap      The map that has changed.
     * @param message       A message describing the update.
     */
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (System.out) {  // Ensure that the output is synchronized to avoid concurrency issues
            // Print the update counter, message, and the map's id
            System.out.println("Update counter: " + (++changeCount) + ", and update message: " + message + ", on Map with id: " + worldMap.getId());
            // Print the current state of the map
            System.out.println(worldMap);
        }
    }
}