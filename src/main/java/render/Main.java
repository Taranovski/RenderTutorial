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
import javax.imageio.ImageIO;
import render.image.Image;
import render.image.impl.ImageImpl;
import render.io.ClassPathResourceReader;
import render.io.ImageFileWriter;
import render.obj.domain.ObjItem;
import render.obj.domain.element.Group;
import render.obj.domain.element.Polygon;
import render.obj.domain.element.Vertex;
import render.obj.parser.ObjParcer;
import render.obj.parser.impl.SaxObjParserImpl;
import static render.util.VoxelUtils.fillTrianglePoints;

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

        long time = System.currentTimeMillis();

        Image image = new ImageImpl(bi, SCALE);

        InputStream inputStream = ClassPathResourceReader
                .getResourceAsInputStream("head.obj");
        ObjParcer objParcer = new SaxObjParserImpl();
        ObjItem item = objParcer.parseObjItem(inputStream);

        System.out.println("parced: " + (System.currentTimeMillis() - time));

        Vertex viewDirection = new Vertex(0, 0, -1);
        Color color = Color.white;

//        int i = 0;
        for (Group g : item.groups) {
            for (Polygon p : g.polygons) {
                double intensity = -p.normal.product(viewDirection);
                if (intensity > 0) {
//                    System.out.println(i++);
                    fillTrianglePoints(p, image);
                }
            }
        }

        System.out.println("filled: " + (System.currentTimeMillis() - time));

        final InputStream resourceAsInputStream = ClassPathResourceReader.getResourceAsInputStream("head_diffuse.bmp");

        BufferedImage textureImage = ImageIO.read(resourceAsInputStream);

        image.fillTextures(textureImage);

        System.out.println("textured: " + (System.currentTimeMillis() - time));

        Vertex lightDirection = new Vertex(0, 0, -1);
        image.fillLightning(lightDirection);

        System.out.println("lighted: " + (System.currentTimeMillis() - time));

        image.draw();

        System.out.println("drawn: " + (System.currentTimeMillis() - time));

        ImageFileWriter.writeImageToFile("c:\\1.bmp", "bmp", image);

        System.out.println("outed: " + (System.currentTimeMillis() - time));
    }

}
