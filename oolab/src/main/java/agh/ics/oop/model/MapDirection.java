package agh.ics.oop.model;

public enum MapDirection {
    NORTH, EAST, SOUTH, WEST;

    // toString String method
    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case EAST -> "Wschód";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
        };
    }

    // next method
    public MapDirection next() {
        return switch (this) {
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case NORTH -> EAST;
        };
    }

    // previous method
    public MapDirection previous() {
        return switch (this) {
            case EAST -> NORTH;
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
        };
    }

    // toUnitVector method
    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
        };
    }
}
