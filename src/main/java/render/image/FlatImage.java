/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.image;

/**
 *
 * @author Alyx
 */
public class FlatImage {
    public final int sizeX;
    public final int sizeY;
    
    public int[][][] visiblePixels;

    public FlatImage(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        visiblePixels = new int[sizeY][sizeX][2];
    }
}
