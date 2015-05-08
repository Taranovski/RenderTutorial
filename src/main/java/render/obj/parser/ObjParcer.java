/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser;

import java.io.InputStream;
import render.obj.domain.ObjItem;

/**
 *
 * @author Alyx
 */
public interface ObjParcer {

    ObjItem parseObjItem(InputStream inputStream);
}
