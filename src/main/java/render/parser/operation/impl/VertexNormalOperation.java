/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.operation.impl;

import render.obj.ObjItem;
import render.obj.sub.VertexNormal;
import render.parser.operation.Operation;
import render.parser.orm.Convertor;
import render.parser.orm.impl.VertexNormalConvertor;

/**
 *
 * @author Alyx
 */
public class VertexNormalOperation implements Operation{

    Convertor<VertexNormal> convertor = new VertexNormalConvertor();
    
    @Override
    public void processString(ObjItem item, String s) {
        item.vertexNormals.add(convertor.fromObjString(s));
    }
    
}
