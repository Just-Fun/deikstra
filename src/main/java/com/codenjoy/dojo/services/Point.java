package com.codenjoy.dojo.services;

/**
 * Инкапсулирует координату элемента на поле. Все элементы поля должны наследоваться от PointImpl,
 * единственной реализации этого интерфейса.
 */
public interface Point extends Comparable<Point> {
    int getX();

    int getY();

    boolean itsMe(int x, int y);

    boolean isOutOf(int dw, int dh, int size);
}
