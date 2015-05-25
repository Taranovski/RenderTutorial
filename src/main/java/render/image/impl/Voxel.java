/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.image.impl;

import render.obj.domain.element.Vertex;

/**
 *
 * @author Alyx
 */
public class Voxel {

    public int x;
    public int y;
    public int z;
    public int rgb;
    public Vertex normal;

    public double u;
    public double v;

    public Voxel(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Voxel(int x, int y, int z, double u, double v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
    }

    public Voxel(int x, int y, int z, double u, double v, Vertex normal) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
        this.normal = normal;
    }

    @Override
    public String toString() {
        return "Voxel{" + "x=" + x + ", y=" + y + ", z=" + z + ", color=" + rgb + ", normal=" + normal + '}';
    }

}
