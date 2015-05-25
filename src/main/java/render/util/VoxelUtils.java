/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.util;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import render.image.Image;
import render.image.impl.Voxel;
import render.obj.domain.element.Polygon;
import render.obj.domain.element.TextureCoordinate;
import render.obj.domain.element.Vertex;

/**
 *
 * @author Alyx
 */
public class VoxelUtils {

    public static List<Voxel> getLine3d(Voxel p1, Voxel p2, Color color, Vertex normal) {
//        System.out.println("begin");
//        final int abs = Math.abs(p1.x - p2.x);
//        System.out.println("dx=" + abs);
//        final int abs1 = Math.abs(p1.y - p2.y);
//        System.out.println("dy=" + abs1);
//        final int abs2 = Math.abs(p1.z - p2.z);
//        System.out.println("dz=" + abs2);
//        System.out.println("approx=" + (abs + abs1 + abs2));

        List<Voxel> points = new LinkedList<>();

        int i, dx, dy, dz, l, m, n, x_inc, y_inc, z_inc,
                err_1, err_2, dx2, dy2, dz2;
        int[] pixel = new int[3];
        double[] tex = new double[2];

        pixel[0] = p1.x;
        pixel[1] = p1.y;
        pixel[2] = p1.z;
        dx = p2.x - p1.x;
        dy = p2.y - p1.y;
        dz = p2.z - p1.z;

        tex[0] = p1.u;
        tex[1] = p1.v;

        double du = p2.u - p1.u;
        double dv = p2.v - p1.v;
//        System.out.println("p1 " + p1.u + " " + p1.v);
//        System.out.println("p2 " + p2.u + " " + p2.v);
//
//        System.out.println("");
//        System.out.println(du + " " + dv);

        x_inc = (dx < 0) ? -1 : 1;
        l = Math.abs(dx);
        y_inc = (dy < 0) ? -1 : 1;
        m = Math.abs(dy);
        z_inc = (dz < 0) ? -1 : 1;
        n = Math.abs(dz);
        dx2 = l << 1;
        dy2 = m << 1;
        dz2 = n << 1;

//        System.out.println("begin");
//        System.out.println(tex[0] + " " + tex[1]);

        int count = 0;
        if ((l >= m) && (l >= n)) {
            err_1 = dy2 - l;
            err_2 = dz2 - l;
            for (i = 0; i < l; i++) {
                final Voxel voxel = new Voxel(pixel[0], pixel[1], pixel[2], tex[0], tex[1], normal);
//                count++;
//                System.out.println("case 1: " + count);
                points.add(voxel);
                if (err_1 > 0) {
                    pixel[1] += y_inc;
                    err_1 -= dx2;
                }
                if (err_2 > 0) {
                    pixel[2] += z_inc;
                    err_2 -= dx2;
                }
                err_1 += dy2;
                err_2 += dz2;
                pixel[0] += x_inc;

                incrementCoords(tex, du, dv, l);

//                System.out.println(tex[0] + " " + tex[1]);
            }
        } else if ((m >= l) && (m >= n)) {
            err_1 = dx2 - m;
            err_2 = dz2 - m;
            for (i = 0; i < m; i++) {
                final Voxel voxel = new Voxel(pixel[0], pixel[1], pixel[2], tex[0], tex[1], normal);
//                count++;
//                System.out.println("case 2: " + count);
                points.add(voxel);
                if (err_1 > 0) {
                    pixel[0] += x_inc;
                    err_1 -= dy2;
                }
                if (err_2 > 0) {
                    pixel[2] += z_inc;
                    err_2 -= dy2;
                }
                err_1 += dx2;
                err_2 += dz2;
                pixel[1] += y_inc;

                incrementCoords(tex, du, dv, m);

//                System.out.println(tex[0] + " " + tex[1]);
            }
        } else {
            err_1 = dy2 - n;
            err_2 = dx2 - n;
            for (i = 0; i < n; i++) {
                final Voxel voxel = new Voxel(pixel[0], pixel[1], pixel[2], tex[0], tex[1], normal);
//                count++;
//                System.out.println("case 3: " + count);
                points.add(voxel);
                if (err_1 > 0) {
                    pixel[1] += y_inc;
                    err_1 -= dz2;
                }
                if (err_2 > 0) {
                    pixel[0] += x_inc;
                    err_2 -= dz2;
                }
                err_1 += dy2;
                err_2 += dx2;
                pixel[2] += z_inc;

                incrementCoords(tex, du, dv, n);

//                System.out.println(tex[0] + " " + tex[1]);
            }
        }
        final Voxel voxel = new Voxel(pixel[0], pixel[1], pixel[2], tex[0], tex[1], normal);
//        count++;
//        System.out.println("case 4: " + count);
        points.add(voxel);

//        System.out.println("total=" + count);
        return points;
    }

    private static void incrementCoords(double[] tex, double du, double dv, int l) {
        tex[0] = tex[0] + du / l;
        tex[1] = tex[1] + dv / l;
    }

    public static List<Voxel> getTrianglePoints3d(Voxel p1, Voxel p2, Voxel p3, Color color, Vertex normal) {
        List<Voxel> points = getLine3d(p1, p2, color, normal);
        points.addAll(getLine3d(p2, p3, color, normal));
        points.addAll(getLine3d(p3, p1, color, normal));

        NavigableMap<Integer, NavigableMap<Integer, NavigableMap<Integer, Voxel>>> m = new TreeMap<>();

        for (Voxel p : points) {
            if (!m.containsKey(p.y)) {
                m.put(p.y, new TreeMap<Integer, NavigableMap<Integer, Voxel>>());
            }
            if (!m.get(p.y).containsKey(p.x)) {
                m.get(p.y).put(p.x, new TreeMap<Integer, Voxel>());
            }
            m.get(p.y).get(p.x).put(p.z, p);
        }

        List<Voxel> triangle = new LinkedList<>();

        for (Map.Entry<Integer, NavigableMap<Integer, NavigableMap<Integer, Voxel>>> entry : m.entrySet()) {
            Voxel value1 = entry.getValue().firstEntry().getValue().firstEntry().getValue();
            Voxel value2 = entry.getValue().lastEntry().getValue().firstEntry().getValue();
            triangle.addAll(getLine3d(value1, value2, color, normal));
        }

        return triangle;
    }

    public static Voxel getVoxel(Vertex v, int scale, TextureCoordinate textureCoordinate) {
        return new Voxel(
                coordinateTransform(v.x, scale, false),
                coordinateTransform(v.y, scale, true),
                coordinateTransform(v.z, scale, false),
                textureCoordinate.u,
                textureCoordinate.v);
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

    public static void fillTrianglePoints(Polygon p, Image image, Color color) {
        Voxel v1 = getVoxel(p.polygonElement1.vertex, image.getScale(), p.polygonElement1.textureCoordinate);
        Voxel v2 = getVoxel(p.polygonElement2.vertex, image.getScale(), p.polygonElement2.textureCoordinate);
        Voxel v3 = getVoxel(p.polygonElement3.vertex, image.getScale(), p.polygonElement3.textureCoordinate);

        List<Voxel> list = getTrianglePoints3d(v1, v2, v3, color, p.normal);
        Voxel[][] visible = image.getVisible();

        for (Voxel v : list) {
            if (visible[v.y][v.x] == null || v.z > visible[v.y][v.x].z) {
                visible[v.y][v.x] = v;
            }
        }
    }

    public static Color getLightIntencityColor(Color color, double intensity) {
        return new Color((int) (intensity * color.getRed()), (int) (intensity * color.getGreen()), (int) (intensity * color.getBlue()));
    }

    public static int coordinateDirectTransform(double value, int range, boolean reverse) {
        int coord = Math.round((float) (value * (range - 1)));
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
