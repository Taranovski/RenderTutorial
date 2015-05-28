/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.domain.element;

/**
 *
 * @author Alyx
 */
public class Vertex {

    public double x;
    public double y;
    public double z;
    public double length;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        length = Math.sqrt(x * x + y * y + z * z);
    }

    public Vertex minus(Vertex other) {
        return new Vertex(x - other.x, y - other.y, z - other.z);
    }

    public Vertex crossProduct(Vertex other) {
        double x1 = y * other.z - z * other.y;
        double y1 = z * other.x - x * other.z;
        double z1 = x * other.y - y * other.x;

        return new Vertex(x1, y1, z1);
    }

    public double product(Vertex other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vertex normalize() {
        if (length > 0) {
            double inv = 1 / length;

            double x1 = x * inv;
            double y1 = y * inv;
            double z1 = z * inv;
            return new Vertex(x1, y1, z1);
        }
        return new Vertex(x, y, z);
    }

    @Override
    public String toString() {
        return "Vertex{" + "x=" + x + ", y=" + y + ", z=" + z + ", length=" + length + '}';
    }

    Vertex plus(Vertex other) {
        return new Vertex(x + other.x, y + other.y, z + other.z);
    }

}
