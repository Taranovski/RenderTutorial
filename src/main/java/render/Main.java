/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
import static render.util.PointUtils.getTrianglePoints3d;
import static render.util.PointUtils.getVoxel;
import static render.util.Utils.getLightIntencityColor;

/**
 *
 * @author Alyx
 */
public class Main {

    private static final int SCALE = 4096;

    private static final BufferedImage bi
            = new BufferedImage(SCALE, SCALE, BufferedImage.TYPE_INT_RGB);

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        Image image = new ImageImpl(bi, SCALE);

        InputStream inputStream = ClassPathResourceReader
                .getResourceAsInputStream("head.obj");
        ObjParcer objParcer = new SaxObjParserImpl();
        ObjItem item = objParcer.parseObjItem(inputStream);

        Vertex lightDirection = new Vertex(0, 0, -1);
        Color color = Color.white;

        int i = 0;
        
        for (Group g : item.groups) {
            for (Polygon p : g.polygons) {
                double intensity = -p.normal.product(lightDirection);
                if (intensity > 0) {
                    System.out.println(i++);
                    fillTrianglePoints(p, image, getLightIntencityColor(color, intensity));
                }
            }
        }

        image.draw();
        ImageFileWriter.writeImageToFile("c:\\1.bmp", "bmp", image);
    }

    private static void fillTrianglePoints(Polygon p, Image image, Color color) {
        Voxel v1 = getVoxel(p.polygonElement1.vertex, image.getScale());
        Voxel v2 = getVoxel(p.polygonElement2.vertex, image.getScale());
        Voxel v3 = getVoxel(p.polygonElement3.vertex, image.getScale());

        List<Voxel> list = getTrianglePoints3d(v1, v2, v3, color, p.normal);
        int[][][] visible = image.getVisible();

        for (Voxel v : list) {
            if (v.z > visible[v.y][v.x][0]) {
                visible[v.y][v.x][0] = v.z;
                visible[v.y][v.x][1] = color.getRGB();
            }
        }
    }

}
