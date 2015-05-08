/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.io;

import java.io.InputStream;

/**
 *
 * @author Alyx
 */
public class ClassPathResourceReader {

    public static InputStream getResourceAsInputStream(String resource) {
        return ClassPathResourceReader.class.getClassLoader().getResourceAsStream(resource);
    }
}
