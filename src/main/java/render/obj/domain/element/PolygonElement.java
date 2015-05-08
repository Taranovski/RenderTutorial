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
public class PolygonElement {
    public Vertex vertex;
    public TextureCoordinate textureCoordinate;
    public VertexNormal vertexNormal;

    public PolygonElement(
            Vertex vertex, 
            TextureCoordinate textureCoordinate, 
            VertexNormal vertexNormal
    ) {
        this.vertex = vertex;
        this.textureCoordinate = textureCoordinate;
        this.vertexNormal = vertexNormal;
    }
    
}
