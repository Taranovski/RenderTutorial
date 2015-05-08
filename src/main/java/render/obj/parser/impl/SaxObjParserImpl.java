/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import render.obj.domain.ObjItem;
import render.obj.parser.ObjParcer;
import render.obj.parser.operation.Operation;
import render.obj.parser.operation.impl.CommentOperation;
import render.obj.parser.operation.impl.GroupNameOperation;
import render.obj.parser.operation.impl.GroupSmoothOperation;
import render.obj.parser.operation.impl.PolygonOperation;
import render.obj.parser.operation.impl.TextureCoordinateOperation;
import render.obj.parser.operation.impl.VertexNormalOperation;
import render.obj.parser.operation.impl.VertexOperation;

/**
 *
 * @author Alyx
 */
public class SaxObjParserImpl implements ObjParcer {

    static Map<String, Operation> operations = new HashMap<String, Operation>() {
        {
            put("#", new CommentOperation());
            put("v", new VertexOperation());
            put("vt", new TextureCoordinateOperation());
            put("vn", new VertexNormalOperation());
            put("f", new PolygonOperation());
            put("g", new GroupNameOperation());
            put("s", new GroupSmoothOperation());
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
        operations.get(temp.split("\\s")[0]).processString(item, temp);
    }
}
