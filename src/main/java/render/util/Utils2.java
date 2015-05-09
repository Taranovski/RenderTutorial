/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.Random;

/**
 *
 * @author Alyx
 */
public class Utils2 {

    private final static Random RANDOM = new Random();

    public static Image getImageFromArray(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0, 0, width, height, pixels);
        return image;
    }

    public static byte[] getBytes(BufferedImage bufferedImage) {
        byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        return pixels;
    }

    public static Color getRandomColor() {
        return new Color(RANDOM.nextInt(0xffffff));
    }
}
