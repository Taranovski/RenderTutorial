/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.image.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import render.image.Image;
import render.obj.domain.element.Polygon;
import static render.util.PointUtils.*;

/**
 *
 * @author Alyx
 */
public class ImageImpl implements Image {

    private final BufferedImage bi;
    private final int scale;
    public final int sizeX;
    public final int sizeY;

    public int[][][] visiblePixels;

    public ImageImpl(BufferedImage bi, int scale) {
        this.bi = bi;
        this.scale = scale;
        sizeX = scale;
        sizeY = scale;
        visiblePixels = new int[sizeY][sizeX][2];
    }

    @Override
    public void setPixel(int i, int j, Color color) {
        bi.setRGB(i, j, color.getRGB());
    }

//    @Override
//    public void setPixel(Voxel point) {
//        setPixel(point.x, point.y, point.color);
//    }
//    @Override
//    public void drawLine(Voxel p1, Voxel p2, Color color) {
//        List<Voxel> points = getLine(p1, p2);
//        for (Voxel p : points) {
//            setPixel(p, color);
//        }
//    }
    @Override
    public RenderedImage getSource() {
        return bi;
    }

    @Override
    public int getWidth() {
        return scale;
    }

    @Override
    public int getHeight() {
        return scale;
    }

    @Override
    public void drawWireFramePolygon(Polygon p, Color color) {
        Voxel p1 = getVoxel(p.polygonElement1.vertex, scale);
        Voxel p2 = getVoxel(p.polygonElement2.vertex, scale);
        Voxel p3 = getVoxel(p.polygonElement3.vertex, scale);

//        drawLine(p1, p2, color);
//        drawLine(p2, p3, color);
//        drawLine(p3, p1, color);
    }

//    @Override
//    public void drawPolygon(Polygon p, Color color) {
//        Voxel p1 = getVoxel(p.polygonElement1.vertex, scale);
//        Voxel p2 = getVoxel(p.polygonElement2.vertex, scale);
//        Voxel p3 = getVoxel(p.polygonElement3.vertex, scale);
//
//        List<Voxel> polygon = getTrianglePoints(p1, p2, p3);
//
//        for (Voxel point : polygon) {
//            setPixel(point, color);
//        }
//    }
//
//    @Override
//    public void setPixel(Voxel point, Color color) {
//        setPixel(point.x, point.y, color);
//    }
//
//    @Override
//    public void draw(List<Voxel> voxels) {
//        for (Voxel v : voxels){
//            setPixel(v);
//        }
//    }
    @Override
    public void draw() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                bi.setRGB(x, y, visiblePixels[y][x][1]);
            }
        }
    }

    @Override
    public int getScale() {
        return scale;
    }

    @Override
    public int[][][] getVisible() {
        return visiblePixels;
    }
}
