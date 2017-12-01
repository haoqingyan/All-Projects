/*
 * File:Line.java

 * //Author : Yichao Tang && Haoqing Yan
 * able to draw the line
 */
package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line extends PaintObject {

	// constructor
	public Line(Color color, Point upperleftPoint, Point Lowerright) {
		super(color, upperleftPoint, Lowerright);
		// set the shape into a line
	}

	public void draw(Graphics g){	
		g.setColor(color);
		g.drawLine(upperleftPoint.x, upperleftPoint.y, Lowerright.x, Lowerright.y);	
	
	}
}
