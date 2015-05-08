/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.operation.impl;

import render.obj.domain.ObjItem;
import render.obj.domain.element.TextureCoordinate;
import render.obj.parser.operation.Operation;
import render.obj.parser.orm.Convertor;
import render.obj.parser.orm.impl.TextureCoordinateConvertor;

/**
 *
 * @author Alyx
 */
public class TextureCoordinateOperation implements Operation{

    Convertor<TextureCoordinate> convertor = new TextureCoordinateConvertor();
    
    @Override
    public void processString(ObjItem item, String s) {
        item.textureCoordinates.add(convertor.fromObjString(s));
    }
    
}
