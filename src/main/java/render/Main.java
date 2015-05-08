/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Alyx
 */
public class Main {

    private static Random random = new Random();

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File f = new File("c:\\1.bmp");
        f.createNewFile();
        if (f.exists()) {
            f.delete();
            f.createNewFile();
        }

        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        Color color = new Color(255, 0, 0);
//
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                setPixel(bi, i, j, color);
//            }
//        }
//
//        int basex = 0;
//        int basey = 0;
//        int rangex = 800;
//        int rangey = 600;
//        
//        
//        for (int i = 0; i < 100000; i++) {
//            Point p1 = new Point(generateCoordinate(basex, rangex), generateCoordinate(basey, rangey));
//            Point p2 = new Point(generateCoordinate(basex, rangex), generateCoordinate(basey, rangey));
//            Color color1 = new Color(random.nextInt());
//
//            drawLine(bi, p1, p2, color1);
//        }

        ImageIO.write(bi, "bmp", f);
    }

    private static void setPixel(BufferedImage bi, int i, int j, Color color) {
        bi.setRGB(i, j, color.getRGB());
    }

    private static void setPixel(BufferedImage bi, Point point, Color color) {
        setPixel(bi, point.x, point.y, color);
    }

    ;

    private static void drawLine(BufferedImage bi, Point p1, Point p2, Color color) {
        boolean steep = false;
        if (Math.abs(p1.x - p2.x) < Math.abs(p1.y - p2.y)) {
            swapPointCoords(p1);
            swapPointCoords(p2);
            steep = true;
        }
        if (p1.x > p2.x) {
            swapPoints(p1, p2);
        }

        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;

        int derror2 = Math.abs(dy) * 2;

        int error2 = 0;

        int y = p1.y;

        for (int x = p1.x; x <= p2.x; x++) {
            if (steep) {
                setPixel(bi, y, x, color);
            } else {
                setPixel(bi, x, y, color);
            }
            error2 += derror2;
            if (error2 > dx) {
                y += (p2.y > p1.y) ? 1 : -1;
                error2 -= dx * 2;
            }
        }

    }

    private static void swapPointCoords(Point p1) {
        int temp = p1.y;
        p1.y = p1.x;
        p1.x = temp;
    }

    private static void swapPoints(Point p1, Point p2) {
        int temp = p1.x;
        p1.x = p2.x;
        p2.x = temp;

        temp = p1.y;
        p1.y = p2.y;
        p2.y = temp;
    }

    private static int generateCoordinate(int base, int range) {
        return base + random.nextInt(range);
    }

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
}
