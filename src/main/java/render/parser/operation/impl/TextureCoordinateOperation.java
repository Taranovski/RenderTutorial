/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.operation.impl;

import render.obj.ObjItem;
import render.obj.sub.TextureCoordinate;
import render.parser.operation.Operation;
import render.parser.orm.Convertor;
import render.parser.orm.impl.TextureCoordinateConvertor;

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
