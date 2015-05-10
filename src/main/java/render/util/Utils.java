/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.util;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Alyx
 */
public class Utils {

    private final static Random RANDOM = new Random();

    public static Color getLightIntencityColor(double intensity) {
        return new Color((int) (intensity * 255), (int) (intensity * 255), (int) (intensity * 255));
    }

    public static Color getRandomColor() {
        return new Color(RANDOM.nextInt(0xffffff));
    }
}
