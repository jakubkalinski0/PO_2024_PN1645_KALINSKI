package agh.ics.oop.model;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(MapDirection direction, Vector2d position) {
        this.direction = direction;
        this.position = position;
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "Direction: " + this.direction + ", Position: " + this.position;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.direction.toUnitVector());
                if (isWithinBounds(newPosition)) {
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.direction.toUnitVector());
                if (isWithinBounds(newPosition)) {
                    this.position = newPosition;
                }
            }
        }
    }

    private boolean isWithinBounds(Vector2d position) {
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() <= 4 && position.getY() <= 4;
    }
}
