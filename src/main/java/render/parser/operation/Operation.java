/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.operation;

import render.obj.ObjItem;

/**
 *
 * @author Alyx
 */
public interface Operation {
    void processString(ObjItem item, String s);
}
