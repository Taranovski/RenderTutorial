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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import render.image.Image;
import render.obj.domain.element.Polygon;
import render.obj.domain.element.Vertex;
import render.util.Utils;

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
        List<Point> points = getLine(p1, p2);
        for (Point p : points) {
            setPixel(p, color);
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
    public int getHeight() {
        return bi.getHeight();
    }

    @Override
    public void drawWireFramePolygon(Polygon p, Color color) {
        Point p1 = getPoint(p.polygonElement1.vertex);
        Point p2 = getPoint(p.polygonElement2.vertex);
        Point p3 = getPoint(p.polygonElement3.vertex);

        drawLine(p1, p2, color);
        drawLine(p2, p3, color);
        drawLine(p3, p1, color);
    }

    private Point getPoint(Vertex v) {
        return new Point(
                Utils.coordinateTransform(v.x, getWidth(), false),
                Utils.coordinateTransform(v.y, getHeight(), true));
    }

    private List<Point> getLine(Point p1, Point p2) {
        List<Point> points = new ArrayList<>();

        int x0 = p1.x;
        int y0 = p1.y;

        int x1 = p2.x;
        int y1 = p2.y;

        int temp = 0;

        boolean steep = false;
        if (Math.abs(x0 - x1) < Math.abs(y0 - y1)) {
            temp = x0;
            x0 = y0;
            y0 = temp;

            temp = x1;
            x1 = y1;
            y1 = temp;

            steep = true;
        }
        if (x0 > x1) {
            temp = x0;
            x0 = x1;
            x1 = temp;

            temp = y0;
            y0 = y1;
            y1 = temp;
        }

        int dx = x1 - x0;
        int dy = y1 - y0;

        int derror2 = Math.abs(dy) * 2;

        int error2 = 0;

        int y = y0;

        for (int x = x0; x <= x1; x++) {
            if (steep) {
                points.add(new Point(y, x));
            } else {
                points.add(new Point(x, y));
            }
            error2 += derror2;
            if (error2 > dx) {
                y += (y1 > y0) ? 1 : -1;
                error2 -= dx * 2;
            }
        }
        return points;
    }

    @Override
    public void drawPolygon(Polygon p, Color color) {
        Point p1 = getPoint(p.polygonElement1.vertex);
        Point p2 = getPoint(p.polygonElement2.vertex);
        Point p3 = getPoint(p.polygonElement3.vertex);

        List<Point> polygon = getTrianglePoints(p1, p2, p3);

        for (Point point : polygon) {
            setPixel(point, color);
        }

    }

    private List<Point> getTrianglePoints(Point p1, Point p2, Point p3) {
        List<Point> points = getLine(p1, p2);
        points.addAll(getLine(p2, p3));
        points.addAll(getLine(p3, p1));

        NavigableMap<Integer, NavigableMap<Integer, Point>> m = new TreeMap<>();

        for (Point p : points) {
            if (!m.containsKey(p.y)) {
                m.put(p.y, new TreeMap<Integer, Point>());
            }
            m.get(p.y).put(p.x, p);
        }

        List<Point> triangle = new ArrayList<>();

        for (Map.Entry<Integer, NavigableMap<Integer, Point>> entry : m.entrySet()) {
            int minX = entry.getValue().firstKey();
            int maxX = entry.getValue().lastKey();
            int y = entry.getKey();
            for (int i = minX; i <= maxX; i++) {
                triangle.add(new Point(i, y));
            }
        }
        return triangle;
    }

}
