/*
 * File:Rectangle.java
//Author : Yichao Tang && Haoqing Yan
 * able to daw rectangles.
 */
package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends PaintObject {

	public Rectangle(Color color, Point upperleftPoint, Point Lowerright) {
		super(color, upperleftPoint, Lowerright);
		// set the shape into Oval
		// Fix the Point argument and calculate the width and height
		FixPoints();
	}
	public void draw(Graphics g){	
		g.setColor(color);
		g.fillRect(upperleftPoint.x, upperleftPoint.y, width, height);
	}
}
