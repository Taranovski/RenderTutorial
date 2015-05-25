/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import render.image.impl.Voxel;

/**
 *
 * @author Alyx
 */
public interface Image {

    void setPixel(int i, int j, Color color);

    RenderedImage getSource();

    int getWidth();

    int getHeight();
    
    int getScale();

    Voxel[][] getVisible();
    
    void draw();

    public void fillTextures(BufferedImage textureImage);
}
