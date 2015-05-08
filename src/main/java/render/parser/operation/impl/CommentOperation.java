/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.operation.impl;

import render.obj.ObjItem;
import render.parser.operation.Operation;

/**
 *
 * @author Alyx
 */
public class CommentOperation implements Operation{

    @Override
    public void processString(ObjItem item, String s) {
        item.comments.add(s);
    }
    
}
