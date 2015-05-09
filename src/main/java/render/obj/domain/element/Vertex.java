/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.domain.element;

import javafx.geometry.Point3D;

/**
 *
 * @author Alyx
 */
public class Vertex {
    public Point3D p;
    public double x;
    public double y;
    public double z;

    public Vertex(double x, double y, double z) {
        p = new Point3D(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
