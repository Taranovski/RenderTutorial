/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.operation.impl;

import render.obj.domain.ObjItem;
import render.obj.domain.element.Vertex;
import render.obj.parser.operation.Operation;
import render.obj.parser.orm.Convertor;
import render.obj.parser.orm.impl.VertexConvertor;

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
