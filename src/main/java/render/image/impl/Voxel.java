/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.image.impl;

import java.awt.Color;
import render.obj.domain.element.Vertex;

/**
 *
 * @author Alyx
 */
public class Voxel {

    public int x;
    public int y;
    public int z;
    public Color color;
    public Vertex normal;

    public Voxel(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Voxel(int x, int y, int z, Color color, Vertex normal) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
        this.normal = normal;
    }

    @Override
    public String toString() {
        return "Voxel{" + "x=" + x + ", y=" + y + ", z=" + z + ", color=" + color + ", normal=" + normal + '}';
    }

}
