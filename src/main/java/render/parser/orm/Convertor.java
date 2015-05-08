/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.orm;

/**
 *
 * @author Alyx
 * @param <T>
 */
public interface Convertor<T> {
    String DELIMITER = " ";
    String DELIMITER_REGEXP = "\\s";
        
    T fromObjString(String string);
    String toObjString(T t);
}
