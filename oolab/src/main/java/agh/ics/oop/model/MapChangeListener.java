package agh.ics.oop.model;

/**
 * Interface for listening to changes in the map.
 *
 * Implementing this interface allows objects to be notified whenever a change
 * occurs in the world map.
 */
public interface MapChangeListener {

    /**
     * Method to handle map changes.
     *
     * This method will be called whenever a change occurs in the map, and it provides
     * details about the map and the change message.
     *
     * @param worldMap The world map that has changed.
     * @param message A message describing the change that occurred.
     */
    void mapChanged(WorldMap worldMap, String message);
}