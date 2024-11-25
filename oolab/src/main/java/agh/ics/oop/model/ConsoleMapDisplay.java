package agh.ics.oop.model;

import java.io.Console;

public class ConsoleMapDisplay implements MapChangeListener {
    private int changeCount = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Upadate counter: " + (++changeCount) + " and update message: " + message);
        System.out.println(worldMap);
    }
}
