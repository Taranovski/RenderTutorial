/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.orm.impl;

import render.obj.domain.element.Vertex;
import render.obj.parser.orm.Convertor;

/**
 *
 * @author Alyx
 */
public class VertexConvertor implements Convertor<Vertex>{

    private static final String IDENTIFIER = "v";
    
    @Override
    public Vertex fromObjString(String string) {
        String[] strings = string.split(DELIMITER_REGEXP);
        double d1 = Double.parseDouble(strings[1]);
        double d2 = Double.parseDouble(strings[2]);
        double d3 = Double.parseDouble(strings[3]);
        return new Vertex(d1, d2, d3);
    }

    @Override
    public String toObjString(Vertex t) {
        return IDENTIFIER + t.x + DELIMITER + t.y + DELIMITER + t.z;
    }
    
}
