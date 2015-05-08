/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.orm.impl;

import render.obj.domain.element.VertexNormal;
import render.obj.parser.orm.Convertor;

/**
 *
 * @author Alyx
 */
public class VertexNormalConvertor implements Convertor<VertexNormal> {

    private static final String IDENTIFIER = "vn";
    
    @Override
    public VertexNormal fromObjString(String string) {
        String[] strings = string.split(DELIMITER_REGEXP);
        double d1 = Double.parseDouble(strings[1]);
        double d2 = Double.parseDouble(strings[2]);
        double d3 = Double.parseDouble(strings[3]);
        return new VertexNormal(d1, d2, d3);
    }

    @Override
    public String toObjString(VertexNormal t) {
        return IDENTIFIER + t.x + DELIMITER + t.y + DELIMITER + t.z;
    }
    
}
