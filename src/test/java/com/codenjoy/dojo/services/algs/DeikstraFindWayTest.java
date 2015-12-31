package com.codenjoy.dojo.services.algs;

import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.services.LengthToXY;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.utils.TestUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleksandr.baglai on 31.12.2015.
 */
public class DeikstraFindWayTest {

    @Test
    public void test1() throws Exception {
        asrtWay("++++" +
                "++++" +
                "++++" +
                "++++",

                " A  " +
                " *  " +
                " *B " +
                "    ");
    }

    @Test
    public void test2() throws Exception {
        asrtWay("++++" +
                "+  +" +
                "+  +" +
                "++++",

                "*A  " +
                "*   " +
                "*   " +
                "**B ");
    }

    @Test
    public void test3() throws Exception {
        asrtWay("++++" +
                "   +" +
                "+  +" +
                "++++",

                " A**" +
                "   *" +
                "   *" +
                "  B*");
    }

    @Test
    public void test4() throws Exception {
        asrtWay("+++ +" +
                "+ + +" +
                "+ + +" +
                "+ + +" +
                "+ +++",

                "*** A" +
                "* * *" +
                "* * *" +
                "* * *" +
                "B ***");
    }

    @Test
    public void test5() throws Exception {
        asrtWay("+++ +" +
                "+ + +" +
                "+++++" +
                "+ + +" +
                "+ +++",

                "    A" +
                "    *" +
                "*****" +
                "*    " +
                "B    ");
    }

    @Test
    public void test6() throws Exception {
        asrtWay("+++++" +
                "+++++" +
                "+++++" +
                "+++++" +
                "+++++",

                "    A" +
                "    *" +
                "    *" +
                "    *" +
                "B****");
    }

    private void asrtWay(String possibleBoard, String board) {
        DeikstraFindWay way = new DeikstraFindWay();

        DeikstraFindWay.Possible possible = getPossible(possibleBoard);
        List<Direction> shortestWay = way.getShortestWay(size(board), from(board), to(board), possible);

        assertPath(board, shortestWay);
    }

    private Point from(String board) {
        return getXY(board).getXY(board.indexOf('A'));
    }

    private List<Point> to(String board) {
        return Arrays.asList(getXY(board).getXY(board.indexOf('B')));
    }

    private void assertPath(String board, List<Direction> way) {
        String actual = board.replaceAll("[AB*]", " ");
        Point point = from(board);
        actual = set(actual, point, 'A');

        for (Direction direction : way) {
            point = direction.change(point);
            actual = set(actual, point, '*');
        }

        actual = set(actual, point, 'B');

        assertEquals(TestUtils.injectN(board), TestUtils.injectN(actual));
    }

    private String set(String board, Point from, char ch) {
        LengthToXY xy = getXY(board);
        int length = xy.getLength(from.getX(), from.getY());
        String result = board.substring(0, length) + ch + board.substring(length + 1);
        return result;
    }

    private int size(String board) {
        return (int) Math.sqrt(board.length());
    }

    private DeikstraFindWay.Possible getPossible(final String board) {
        final LengthToXY xy = getXY(board);
        return new DeikstraFindWay.Possible() {
            @Override
            public boolean possible(Point from, Direction direction) {
                if (board.charAt(xy.getLength(from.getX(), from.getY())) == '+') {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public boolean possible(Point atWay) {
                if (atWay.isOutOf(0, 0, size(board))) {
                    return false;
                }
                return board.charAt(xy.getLength(atWay.getX(), atWay.getY())) == '+';
            }
        };
    }

    private LengthToXY getXY(String board) {
        return new LengthToXY(size(board));
    }

}