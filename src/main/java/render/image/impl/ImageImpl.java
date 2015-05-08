/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.image.impl;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import render.image.Image;

import static render.util.Utils.*;

/**
 *
 * @author Alyx
 */
public class ImageImpl implements Image {

    private final BufferedImage bi;

    public ImageImpl(BufferedImage bi) {
        this.bi = bi;
    }

    @Override
    public void setPixel(int i, int j, Color color) {
        bi.setRGB(i, j, color.getRGB());
    }

    @Override
    public void setPixel(Point point, Color color) {
        setPixel(point.x, point.y, color);
    }

    @Override
    public void drawLine(Point p1, Point p2, Color color) {
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
                setPixel(y, x, color);
            } else {
                setPixel(x, y, color);
            }
            error2 += derror2;
            if (error2 > dx) {
                y += (p2.y > p1.y) ? 1 : -1;
                error2 -= dx * 2;
            }
        }
    }

    @Override
    public RenderedImage getSource() {
        return bi;
    }

    @Override
    public int getWidth() {
        return bi.getWidth();
    }

    @Override
    public int getHight() {
        return bi.getHeight();
    }

}
