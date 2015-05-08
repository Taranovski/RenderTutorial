/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.operation.impl;

import render.obj.ObjItem;
import render.obj.sub.Group;
import render.parser.operation.Operation;

/**
 *
 * @author Alyx
 */
public class GroupNameOperation implements Operation{

    @Override
    public void processString(ObjItem item, String s) {
        Group group = new Group();
        group.groupName = s.split("\\s")[1];
        item.groups.add(group);
    }
    
}
