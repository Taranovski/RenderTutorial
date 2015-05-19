/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import render.image.Image;
import render.image.impl.ImageImpl;
import render.image.impl.Voxel;
import render.io.ClassPathResourceReader;
import render.io.ImageFileWriter;
import render.obj.domain.ObjItem;
import render.obj.domain.element.Group;
import render.obj.domain.element.Polygon;
import render.obj.domain.element.Vertex;
import render.obj.parser.ObjParcer;
import render.obj.parser.impl.SaxObjParserImpl;

/**
 *
 * @author Alyx
 */
public class Utils {

    private final static Random RANDOM = new Random();

    public static Color getLightIntencityColor(Color color, double intensity) {
        return new Color((int) (intensity * color.getRed()), (int) (intensity * color.getGreen()), (int) (intensity * color.getBlue()));
    }

    public static Color getRandomColor() {
        return new Color(RANDOM.nextInt(0xffffff));
    }

    public static boolean checkVision(Polygon p, int[][] zBuffer, int scale) {

        Voxel v1 = PointUtils.getVoxel(p.polygonElement1.vertex, scale);
        Voxel v2 = PointUtils.getVoxel(p.polygonElement2.vertex, scale);
        Voxel v3 = PointUtils.getVoxel(p.polygonElement3.vertex, scale);
        Voxel v = PointUtils.getVoxel(p.center, scale);

        if (v1.z > zBuffer[v1.y][v1.x]) {
            return true;
        }
        if (v2.z > zBuffer[v2.y][v2.x]) {
            return true;
        }
        if (v3.z > zBuffer[v3.y][v3.x]) {
            return true;
        }
        if (v.z > zBuffer[v.y][v.x]) {
            return true;
        }
        return false;
    }

    public static void addOrSkip(int[][] zBuffer, List<Voxel> visibleVoxels, List<Voxel> trianglePoints) {
        for (Voxel v : trianglePoints) {
            if (v.z > zBuffer[v.y][v.x]) {
                zBuffer[v.y][v.x] = v.z;
                visibleVoxels.add(v);
            }
        }
    }

    public static int[][] createEmptyZBuffer(int scaleY, int scaleX) {
        return new int[scaleY][scaleX];
    }

    public static Comparator<Polygon> comp() {
        final Comparator<Polygon> comparator = new Comparator<Polygon>() {

            @Override
            public int compare(Polygon o1, Polygon o2) {
                return Double.compare(o1.center.z, o2.center.z);
            }
        };
        return comparator;
    }

    private static final int SCALE = 4096;

    private static final BufferedImage bi
            = new BufferedImage(SCALE, SCALE, BufferedImage.TYPE_INT_RGB);

    public static void main1(String[] args) throws IOException {

        Image image = new ImageImpl(bi, SCALE);

        InputStream inputStream = ClassPathResourceReader
                .getResourceAsInputStream("head.obj");
        ObjParcer objParcer = new SaxObjParserImpl();
        ObjItem item = objParcer.parseObjItem(inputStream);

        Vertex lightDirection = new Vertex(0, 0, -1);
        Color color = Color.white;
        List<Voxel> visibleVoxels = new LinkedList<>();
        int[][] zBuffer = createEmptyZBuffer(SCALE, SCALE);

        for (Group g : item.groups) {
            Comparator<Polygon> comparator = comp();
            Collections.sort(g.polygons, comparator);
            for (Polygon p : g.polygons) {
//                boolean eligible = checkVision(p, zBuffer, SCALE);
                double intensity = -p.normal.product(lightDirection);
//                if (eligible & intensity > 0) {
                if (intensity > 0) {
                    final List<Voxel> trianglePoints = PointUtils.getTrianglePoints(p, SCALE, getLightIntencityColor(color, intensity));
                    addOrSkip(zBuffer, visibleVoxels, trianglePoints);
                }
            }
        }

//        image.draw(visibleVoxels);
        ImageFileWriter.writeImageToFile("c:\\1.bmp", "bmp", image);
    }
}
