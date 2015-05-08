/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.domain;

import render.obj.domain.element.Vertex;
import render.obj.domain.element.TextureCoordinate;
import render.obj.domain.element.VertexNormal;
import java.util.ArrayList;
import java.util.List;
import render.obj.domain.element.Group;


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
