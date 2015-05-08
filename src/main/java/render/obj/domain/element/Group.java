/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.obj.domain.element;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alyx
 */
public class Group {
    public String groupName;
    public int smooth;
    public List<Polygon> polygons = new ArrayList<>();
}
