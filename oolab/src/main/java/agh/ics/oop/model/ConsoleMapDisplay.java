package agh.ics.oop.model;

import java.io.Console;

public class ConsoleMapDisplay implements MapChangeListener {
    private int changeCount = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (System.out) {
            System.out.println("Upadate counter: " + (++changeCount) + ", and update message: " + message + ", on Map with id: " + worldMap.getId());
            System.out.println(worldMap);
        }
    }
}
