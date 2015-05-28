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
import render.obj.domain.element.Vertex;
import render.util.VoxelUtils;

/**
 *
 * @author Alyx
 */
public class ImageImpl implements Image {

    private final BufferedImage bi;
    private final int scale;
    public final int sizeX;
    public final int sizeY;

    public Voxel[][] visibleVoxels;

    public ImageImpl(BufferedImage bi, int scale) {
        this.bi = bi;
        this.scale = scale;
        sizeX = scale;
        sizeY = scale;
        visibleVoxels = new Voxel[sizeY][sizeX];
    }

    @Override
    public void setPixel(int i, int j, Color color) {
        bi.setRGB(i, j, color.getRGB());
    }

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
    public void draw() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (visibleVoxels[y][x] != null) {
                    bi.setRGB(x, y, visibleVoxels[y][x].rgb);
                }
            }
        }
    }

    @Override
    public int getScale() {
        return scale;
    }

    @Override
    public Voxel[][] getVisible() {
        return visibleVoxels;
    }

    @Override
    public void fillTextures(BufferedImage textureImage) {
        int scaleX = textureImage.getWidth();
        int scaleY = textureImage.getHeight();

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (visibleVoxels[y][x] != null) {
                    int tx = VoxelUtils.coordinateDirectTransform(visibleVoxels[y][x].u, scaleX, false);
                    int ty = VoxelUtils.coordinateDirectTransform(visibleVoxels[y][x].v, scaleY, true);

                    visibleVoxels[y][x].rgb = textureImage.getRGB(tx, ty);
                }
            }
        }
    }

    @Override
    public void fillLightning(Vertex lightDirection) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (visibleVoxels[y][x] != null) {
                    fixLightning(visibleVoxels[y][x], lightDirection);
                }
            }
        }
    }

    private void fixLightning(Voxel visibleVoxel, Vertex lightDirection) {
        double intensity = -visibleVoxel.normal.product(lightDirection);
        if (intensity < 0) {
            intensity = 0;
        }
        visibleVoxel.rgb = adjustColor(visibleVoxel.rgb, intensity);
    }

    private int adjustColor(int rgb, double intensity) {
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        red = (int) (red * intensity);
        green = (int) (green * intensity);
        blue = (int) (blue * intensity);

        int newRgb = red;
        newRgb = (newRgb << 8) + green;
        newRgb = (newRgb << 8) + blue;
        return newRgb;
    }
}
