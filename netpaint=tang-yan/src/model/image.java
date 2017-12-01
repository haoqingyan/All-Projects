//Author : Yichao Tang && Haoqing Yan
// the image we chose. 
package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class image extends PaintObject {

	
	public image(Color color, Point upperleftPoint, Point Lowerright) {
		super(color, upperleftPoint, Lowerright);
		FixPoints();
	}
	
	public void draw(Graphics g){	
		try {
			g.drawImage(ImageIO.read(new File("./img/images.png")), upperleftPoint.x,upperleftPoint.y, width, height, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}