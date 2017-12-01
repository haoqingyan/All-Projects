/*
 * File:Oval.java
//Author : Yichao Tang && Haoqing Yan
 * sble to draw the oval
 */
package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public  class Oval extends PaintObject 
{

	//constructor
	public Oval(Color color, Point upperleftPoint, Point Lowerright) {
		super(color, upperleftPoint, Lowerright);
		//set the shape into Oval
		//Fix the Point argument and calculate the width and height
		FixPoints();
	}

	public void draw(Graphics g){	
		g.setColor(color);
		g.fillOval(upperleftPoint.x, upperleftPoint.y, width, height);
	}
}
