/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.operation.impl;

import render.obj.ObjItem;
import render.parser.operation.Operation;
import render.parser.orm.impl.PolygonConvertor;

/**
 *
 * @author Alyx
 */
public class PolygonOperation implements Operation {

    PolygonConvertor convertor = new PolygonConvertor();

    @Override
    public void processString(ObjItem item, String s) {
        convertor.item = item;
        item.groups.get(item.groups.size() - 1).polygons.add(convertor.fromObjString(s));
    }

}
