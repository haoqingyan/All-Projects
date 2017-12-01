/*
 * File:PaintObject.java
//Author : Yichao Tang && Haoqing Yan
 * the super class that able to draw four required images.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class PaintObject implements Serializable{
	protected Color color;
	protected Point upperleftPoint;
	 protected Point Lowerright;
	 protected String shape;
	 protected int width;
	 protected int height;

	// constructor and intialize two point
	public PaintObject(Color color, Point upperleftPoint, Point Lowerright) {
		this.color = color;
		this.upperleftPoint = upperleftPoint;
		this.Lowerright = Lowerright;
	}
	// fix the two points, and calculate the real upper left point and the width
	// height
	public void FixPoints() {
		width = Math.abs(Lowerright.x - upperleftPoint.x);
		height = Math.abs(Lowerright.y - upperleftPoint.y);

		if (upperleftPoint.x > Lowerright.x) {
			upperleftPoint.x = Lowerright.x;
		}
		if (upperleftPoint.y > Lowerright.y) {
			upperleftPoint.y = Lowerright.y;
		}
	}

	public abstract void draw(Graphics g);
}
