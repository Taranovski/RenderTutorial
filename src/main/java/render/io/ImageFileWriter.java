/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.io;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import render.image.Image;

/**
 *
 * @author Alyx
 */
public class ImageFileWriter {

    public static void writeImageToFile(String path, String extension, Image image) throws IOException {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
            f.createNewFile();
        } else {
            f.createNewFile();
        }
        ImageIO.write(image.getSource(), extension, f);
    }

}
