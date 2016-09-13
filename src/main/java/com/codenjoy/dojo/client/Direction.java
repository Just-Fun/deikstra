package com.codenjoy.dojo.client;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

public enum Direction {
    UP(2, 0, -1), DOWN(3, 0, 1), LEFT(0, -1, 0), RIGHT(1, 1, 0);
    private final int value;
    private final int dx;
    private final int dy;

    Direction(int value, int dx, int dy) {
        this.value = value;
        this.dx = dx;
        this.dy = dy;
    }

    public String toString() {
        return this.name();
    }

    public static Direction valueOf(int i) {
        for (Direction d : Direction.values()) {
            if (d.value == i) {
                return d;
            }
        }
        throw new IllegalArgumentException("No such Direction for " + i);
    }

    public int changeX(int x) {
        return x + dx;
    }

    public int changeY(int y) {
        return y + dy;
    }

    public Direction inverted() {
        switch (this) {
            case UP : return DOWN;
            case DOWN : return UP;
            case LEFT : return RIGHT;
            case RIGHT : return LEFT;
        }
        throw new IllegalArgumentException();
    }

    public Point change(Point point) {
        return new PointImpl(changeX(point.getX()), changeY(point.getY()));
    }

    public int value() {
        return value;
    }

    public Direction clockwise() {
        switch (this) {
            case LEFT: return UP;
            case UP: return RIGHT;
            case RIGHT: return DOWN;
            case DOWN: return LEFT;
        }
        throw new IllegalArgumentException("Cant clockwise for: " + this);
    }

}
