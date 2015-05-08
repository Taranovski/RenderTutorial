/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.operation.impl;

import render.obj.domain.ObjItem;
import render.obj.parser.operation.Operation;

/**
 *
 * @author Alyx
 */
public class GroupSmoothOperation implements Operation {

    @Override
    public void processString(ObjItem item, String s) {
        item.groups.get(item.groups.size() - 1).smooth = Integer.parseInt(s.split("\\s")[1]);
    }

}
