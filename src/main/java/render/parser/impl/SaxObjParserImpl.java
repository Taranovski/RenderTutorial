/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.parser.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import render.obj.ObjItem;
import render.parser.ObjParcer;
import render.parser.operation.Operation;

/**
 *
 * @author Alyx
 */
public class SaxObjParserImpl implements ObjParcer {

    static Map<String, Operation> operations = new HashMap<String, Operation>() {
        {

        }
    };

    @Override
    public ObjItem parseObjItem(InputStream inputStream) {
        ObjItem item = new ObjItem();
        Scanner scanner = new Scanner(inputStream);

        String temp = null;

        while (scanner.hasNextLine()) {
            temp = scanner.nextLine().trim();
            if (temp.length() != 0) {
                handleLine(item, temp);
            }
        }

        return item;
    }

    private void handleLine(ObjItem item, String temp) {
        operations.get(temp.split("\\s", 1)[0]).processString(item, temp);
    }
}
