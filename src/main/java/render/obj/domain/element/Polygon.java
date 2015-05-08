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

    public Polygon(
            PolygonElement polygonElement1, 
            PolygonElement polygonElement2, 
            PolygonElement polygonElement3
    ) {
        this.polygonElement1 = polygonElement1;
        this.polygonElement2 = polygonElement2;
        this.polygonElement3 = polygonElement3;
    }
    
}
