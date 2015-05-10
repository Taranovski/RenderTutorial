/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import render.image.impl.Voxel;
import render.obj.domain.element.Polygon;
import render.obj.domain.element.Vertex;

/**
 *
 * @author Alyx
 */
public class PointUtils {

    public static List<Voxel> getLine2d(Voxel p1, Voxel p2) {
        List<Voxel> points = new ArrayList<>();

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
                points.add(new Voxel(y, x, 0));
            } else {
                points.add(new Voxel(x, y, 0));
            }
            error2 += derror2;
            if (error2 > dx) {
                y += (y1 > y0) ? 1 : -1;
                error2 -= dx * 2;
            }
        }
        return points;
    }

    public static List<Voxel> getLine(Voxel p1, Voxel p2) {
        return getLine2d(p1, p2);
//        return getLine(p1, p2, Color.BLACK, new Vertex(0, 0, 1));
    }
//
//    public static List<Voxel> getLine(Voxel p1, Voxel p2, Color color, Vertex normal) {
//        List<Voxel> points = new ArrayList<>();
//
//        int i, dx, dy, dz, l, m, n, x_inc, y_inc, z_inc,
//                err_1, err_2, dx2, dy2, dz2;
//        int[] pixel = new int[3];
//
//        pixel[0] = p1.x;
//        pixel[1] = p1.y;
//        pixel[2] = p1.z;
//        dx = p2.x - p1.x;
//        dy = p2.y - p1.y;
//        dz = p2.z - p1.z;
//        x_inc = (dx < 0) ? -1 : 1;
//        l = Math.abs(dx);
//        y_inc = (dy < 0) ? -1 : 1;
//        m = Math.abs(dy);
//        z_inc = (dz < 0) ? -1 : 1;
//        n = Math.abs(dz);
//        dx2 = l << 1;
//        dy2 = m << 1;
//        dz2 = n << 1;
//
//        if ((l >= m) && (l >= n)) {
//            err_1 = dy2 - l;
//            err_2 = dz2 - l;
//            for (i = 0; i < l; i++) {
//                points.add(new Voxel(pixel[0], pixel[1], pixel[2], color, normal));
//                if (err_1 > 0) {
//                    pixel[1] += y_inc;
//                    err_1 -= dx2;
//                }
//                if (err_2 > 0) {
//                    pixel[2] += z_inc;
//                    err_2 -= dx2;
//                }
//                err_1 += dy2;
//                err_2 += dz2;
//                pixel[0] += x_inc;
//            }
//        } else if ((m >= l) && (m >= n)) {
//            err_1 = dx2 - m;
//            err_2 = dz2 - m;
//            for (i = 0; i < m; i++) {
//                points.add(new Voxel(pixel[0], pixel[1], pixel[2], color, normal));
//                if (err_1 > 0) {
//                    pixel[0] += x_inc;
//                    err_1 -= dy2;
//                }
//                if (err_2 > 0) {
//                    pixel[2] += z_inc;
//                    err_2 -= dy2;
//                }
//                err_1 += dx2;
//                err_2 += dz2;
//                pixel[1] += y_inc;
//            }
//        } else {
//            err_1 = dy2 - n;
//            err_2 = dx2 - n;
//            for (i = 0; i < n; i++) {
//                points.add(new Voxel(pixel[0], pixel[1], pixel[2], color, normal));
//                if (err_1 > 0) {
//                    pixel[1] += y_inc;
//                    err_1 -= dz2;
//                }
//                if (err_2 > 0) {
//                    pixel[0] += x_inc;
//                    err_2 -= dz2;
//                }
//                err_1 += dy2;
//                err_2 += dx2;
//                pixel[2] += z_inc;
//            }
//        }
//        points.add(new Voxel(pixel[0], pixel[1], pixel[2], color, normal));
//        return points;
//    }

    public static List<Voxel> getTrianglePoints(Voxel p1, Voxel p2, Voxel p3) {
        return getTrianglePoints(p1, p2, p3, Color.BLACK, new Vertex(0, 0, 1));
    }

    public static List<Voxel> getTrianglePoints(Voxel p1, Voxel p2, Voxel p3, Color color, Vertex normal) {
//        List<Voxel> points = getLine(p1, p2, color, normal);
//        points.addAll(getLine(p2, p3, color, normal));
//        points.addAll(getLine(p3, p1, color, normal));
        List<Voxel> points = getLine(p1, p2);
        points.addAll(getLine(p2, p3));
        points.addAll(getLine(p3, p1));

        NavigableMap<Integer, NavigableMap<Integer, Voxel>> m = new TreeMap<>();

        for (Voxel p : points) {
            if (!m.containsKey(p.y)) {
                m.put(p.y, new TreeMap<Integer, Voxel>());
            }
            m.get(p.y).put(p.x, p);
        }

        List<Voxel> triangle = new ArrayList<>();

        for (Map.Entry<Integer, NavigableMap<Integer, Voxel>> entry1 : m.entrySet()) {
            int minX = entry1.getValue().firstKey();
            int maxX = entry1.getValue().lastKey();

            int y = entry1.getKey();
            for (int i = minX; i <= maxX; i++) {
                triangle.addAll(getLine2d(new Voxel(i, y, 0), new Voxel(i, y, 0)));
            }
        }
        return triangle;
    }

    public static List<Voxel> getTrianglePoints(Polygon polygon, int scale, Color color) {
        return getTrianglePoints(
                getVoxel(polygon.polygonElement1.vertex, scale),
                getVoxel(polygon.polygonElement2.vertex, scale),
                getVoxel(polygon.polygonElement3.vertex, scale),
                color,
                polygon.normal);
    }

    public static Voxel getVoxel(Vertex v, int scale) {
        return new Voxel(
                coordinateTransform(v.x, scale, false),
                coordinateTransform(v.y, scale, true),
                coordinateTransform(v.z, scale, false));
    }

    private static int coordinateTransform(double value, int range, boolean reverse) {
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
