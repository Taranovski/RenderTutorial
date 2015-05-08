/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj;

import render.obj.sub.Vertex;
import render.obj.sub.TextureCoordinate;
import render.obj.sub.VertexNormal;
import java.util.ArrayList;
import java.util.List;
import render.obj.sub.Group;


/**
 *
 * @author Alyx
 */
public class ObjItem {
    
    public List<String> comments = new ArrayList<>();
    
    public List<Vertex> vertices = new ArrayList<>();
    public List<TextureCoordinate> textureCoordinates = new ArrayList<>();
    public List<VertexNormal> vertexNormals = new ArrayList<>();
    
    public List<Group> groups = new ArrayList<>();

    public ObjItem() {
    }
    
}
