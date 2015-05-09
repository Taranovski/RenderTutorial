/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.image;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.RenderedImage;
import render.obj.domain.element.Polygon;

/**
 *
 * @author Alyx
 */
public interface Image {

    void setPixel(int i, int j, Color color);

    void setPixel(Point point, Color color);

    void drawLine(Point p1, Point p2, Color color);

    RenderedImage getSource();

    int getWidth();

    int getHeight();

    void drawWireFramePolygon(Polygon p, Color color);
    
    void drawPolygon(Polygon p, Color color);
}
