/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.parser.orm.impl;

import render.obj.domain.ObjItem;
import render.obj.domain.element.Polygon;
import render.obj.domain.element.PolygonElement;
import render.obj.parser.orm.Convertor;

/**
 *
 * @author Alyx
 */
public class PolygonConvertor implements Convertor<Polygon> {

    private static final String IDENTIFIER = "f";
    private static final String SUB_DELIMITER = "/";

    public ObjItem item;

    @Override
    public Polygon fromObjString(String string) {
        String[] strings = string.split(DELIMITER_REGEXP);
        int[] indices1 = getIndices(strings[1]);
        int[] indices2 = getIndices(strings[2]);
        int[] indices3 = getIndices(strings[3]);

        PolygonElement element1 = createPolygonElement(item, indices1);
        PolygonElement element2 = createPolygonElement(item, indices2);
        PolygonElement element3 = createPolygonElement(item, indices3);

        return new Polygon(element1, element2, element3);
    }

    private static PolygonElement createPolygonElement(
            ObjItem item1,
            int[] indices1) {
        return new PolygonElement(
                item1.vertices.get(indices1[0] - 1),
                item1.textureCoordinates.get(indices1[1] - 1),
                item1.vertexNormals.get(indices1[2] - 1)
        );
    }

    @Override
    public String toObjString(Polygon t) {
        String element1 = getlementString(getIndices(item, t.polygonElement1));
        String element2 = getlementString(getIndices(item, t.polygonElement2));
        String element3 = getlementString(getIndices(item, t.polygonElement3));

        return IDENTIFIER + DELIMITER + element1 + DELIMITER + element2 + DELIMITER + element3;
    }

    private int[] getIndices(String item1) {
        int[] numbers = new int[3];
        int i = 0;
        for (String s : item1.split(SUB_DELIMITER)) {
            numbers[i] = Integer.parseInt(s);
            i++;
        }
        return numbers;
    }

    private int[] getIndices(ObjItem item, PolygonElement polygonElement) {
        int[] numbers = new int[3];

        numbers[0] = item.vertices.indexOf(polygonElement.vertex);
        numbers[1] = item.textureCoordinates.indexOf(polygonElement.textureCoordinate);
        numbers[2] = item.vertexNormals.indexOf(polygonElement.vertexNormal);

        return numbers;
    }

    private String getlementString(int[] indices1) {
        return indices1[0] + SUB_DELIMITER + indices1[1] + SUB_DELIMITER + indices1[2];
    }

}
