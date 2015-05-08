/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.util;

import java.awt.Point;

/**
 *
 * @author Alyx
 */
public class Utils {

    public static void swapPointCoords(Point p1) {
        int temp = p1.y;
        p1.y = p1.x;
        p1.x = temp;
    }

    public static void swapPoints(Point p1, Point p2) {
        int temp = p1.x;
        p1.x = p2.x;
        p2.x = temp;

        temp = p1.y;
        p1.y = p2.y;
        p2.y = temp;
    }

    public static int coordinateTransform(double value, int range, boolean reverse) {
        int coord = Math.round((float) ((value + 1) / 2 * (range - 1)));
        coord = reverse ? range - coord : coord;
        if (coord < 0) {
            coord = 0;
        }
        if (coord > range - 1) {
            coord = range - 1;
        }

        return coord;
    }

}
