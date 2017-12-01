package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
  * A JPanel GUI for Netpaint that has all paint objects drawn on it.
  * A JPanel exists in this JFrame that will draw this list of paint objects.
  * 
  * Recommended: One team member work on drawing images with a mouse listener
  * as each new PaintObject is added to a Vector.  Also need a "ghost" image
  * that is drawn as the mouse is dragged.
  * 
  * @author Rick Mercer
 */

public class ToBeTheClient extends JFrame {

  public static void main(String[] args) {
    ToBeTheClient view = new ToBeTheClient();
    view.setVisible(true);
  }

  private DrawingPanel drawingPanel;
  // private static Vector<PaintObject> allPaintObjects;

  public ToBeTheClient() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(20, 20);
    setSize(1100, 800); // to be changed
    setLayout(null); // to be changed if you want a different layout manager
    drawingPanel = new DrawingPanel();
    drawingPanel.setSize(1100, 600); // to be changed
    drawingPanel.setLocation(10, 10); // to be changed

    add(drawingPanel);
    JPanel bottom = new JPanel();
    bottom.setSize(1100, 200); // to be changed
    bottom.setLocation(10, 610); // to be changed
    bottom.setBackground(Color.PINK); // to be changed
    bottom.add(new JLabel("stuff to be added later")); // to be changed
    add(bottom);
  }

  /**
   * This is where all the drawing goes .
   */
  class DrawingPanel extends JPanel {

    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      super.paintComponent(g2);
      g2.setColor(Color.white);
      g2.fillRect(0, 0, this.getWidth(), this.getHeight());

      // draw a few 
      g2.setColor(Color.BLUE);
      g2.fillRect(100, 100, 200, 99);
      g2.setColor(Color.RED);
      g2.drawLine(333, 199, 400, 10);
      g2.setColor(Color.BLACK);
      g2.drawLine(400, 10, 600, 1111);  // too far
      g2.setColor(Color.GREEN);
      g2.fillOval(200, 333, 200, 100);
          
    }
  }
}
