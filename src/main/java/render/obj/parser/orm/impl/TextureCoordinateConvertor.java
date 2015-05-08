/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.orm.impl;

import render.obj.domain.element.TextureCoordinate;
import render.obj.parser.orm.Convertor;

/**
 *
 * @author Alyx
 */
public class TextureCoordinateConvertor implements Convertor<TextureCoordinate>{

    private static final String IDENTIFIER = "vt";
    
    @Override
    public TextureCoordinate fromObjString(String string) {
        String[] strings = string.split(DELIMITER_REGEXP);
        double d1 = Double.parseDouble(strings[1]);
        double d2 = Double.parseDouble(strings[2]);
        double d3 = Double.parseDouble(strings[3]);
        return new TextureCoordinate(d1, d2, d3);
    }

    @Override
    public String toObjString(TextureCoordinate t) {
        return IDENTIFIER + t.u + DELIMITER + t.v + DELIMITER + t.w;
    }
    
}
