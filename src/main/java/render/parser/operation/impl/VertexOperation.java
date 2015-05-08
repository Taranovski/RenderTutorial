/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.operation.impl;

import render.obj.ObjItem;
import render.obj.sub.Vertex;
import render.parser.operation.Operation;
import render.parser.orm.Convertor;
import render.parser.orm.impl.VertexConvertor;

/**
 *
 * @author Alyx
 */
public class VertexOperation implements Operation{

    Convertor<Vertex> convertor = new VertexConvertor();
        
    @Override
    public void processString(ObjItem item, String s) {
        item.vertices.add(convertor.fromObjString(s));
    }
    
}
