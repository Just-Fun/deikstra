package com.codenjoy.dojo.services;

/**
 * Каждый объект на поле имеет свои координаты. Этот класс обычно используется дял указания координат или как родитель.
 * Может использоваться в коллекциях.
 */
public class PointImpl implements Point, Comparable<Point> {
    protected int x;
    protected int y;

    public PointImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public boolean itsMe(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public boolean isOutOf(int dw, int dh, int size) {
        return x < dw || y < dh || y > size - 1 - dh || x > size - 1 - dw;
    }

    @Override
    public int hashCode() {
        return x*1000 + y;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof PointImpl)) {
            return false;
        }

        PointImpl p = (PointImpl)o;

        return (p.x == x && p.y == y);
    }

    public static Point pt(int x, int y) {
        return new PointImpl(x, y);
    }

    @Override
    public int compareTo(Point o) {
        if (o == null) {
            return -1;
        }
        return Integer.valueOf(this.hashCode()).compareTo(Integer.valueOf(o.hashCode()));
    }
}
