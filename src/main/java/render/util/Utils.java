/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.util;

/**
 *
 * @author Alyx
 */
public class Utils {

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
