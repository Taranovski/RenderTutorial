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
public class Polygon {

    public PolygonElement polygonElement1;
    public PolygonElement polygonElement2;
    public PolygonElement polygonElement3;

    public Vertex normal;

    public Polygon(
            PolygonElement polygonElement1,
            PolygonElement polygonElement2,
            PolygonElement polygonElement3
    ) {
        this.polygonElement1 = polygonElement1;
        this.polygonElement2 = polygonElement2;
        this.polygonElement3 = polygonElement3;
        normal = countNormal();
    }

    private Vertex countNormal() {
        Vertex vertex1 = polygonElement1.vertex;
        Vertex vertex2 = polygonElement2.vertex;
        Vertex vertex3 = polygonElement3.vertex;

        return ((vertex2.minus(vertex1)).crossProduct((vertex3.minus(vertex1))))
                .normalize();
    }
}
