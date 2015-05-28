/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.operation.impl;

import render.obj.domain.ObjItem;
import render.obj.domain.element.Vertex;
import render.obj.domain.element.VertexNormal;
import render.obj.parser.operation.Operation;
import render.obj.parser.orm.Convertor;
import render.obj.parser.orm.impl.VertexNormalConvertor;

/**
 *
 * @author Alyx
 */
public class VertexNormalOperation implements Operation{

    Convertor<Vertex> convertor = new VertexNormalConvertor();
    
    @Override
    public void processString(ObjItem item, String s) {
        item.vertexNormals.add(convertor.fromObjString(s));
    }
    
}
