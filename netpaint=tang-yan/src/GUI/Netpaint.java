////Author : Yichao Tang && Haoqing Yan
//the GUI of netpaint. 
package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import model.*;
import model.Rectangle;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Netpaint extends JPanel implements MouseListener, MouseMotionListener {
	
	private JPanel drawingPane;
	private JButton ColorButtom;
	private JPanel interactionButtons;
	private String Action = "";
	private JRadioButton Line;
	private JRadioButton Rectangle;
	private JRadioButton Oval;
	private JRadioButton image;
	private ButtonGroup group;
	private JScrollPane scroller;
	private Color SelectedColor = Color.black;
	private Vector<PaintObject> element;
	private Point currentPoint = new Point();
	private Point LastClicked;
	private int ClickCheck = 0;
	private int drawed = 0;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1080, 720);
		frame.setTitle("Netpaint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent newContentPane = new Netpaint();
		newContentPane.setOpaque(true);
		newContentPane.setSize(new Dimension(2048, 1024));
		frame.setContentPane(newContentPane);
		frame.setVisible(true);
	}
    //set up
	public Netpaint() {
		this.setLayout(new BorderLayout());
		// Set up the drawing area.
		drawingPane = new DrawingPanel();
		drawingPane.setPreferredSize(new Dimension(2048, 1024));
		drawingPane.setBackground(Color.white);
		drawingPane.addMouseMotionListener(this);
		drawingPane.addMouseListener(this);
		// Put the drawing area in a scroll pane.
		scroller = new JScrollPane(drawingPane);
		scroller.setPreferredSize(new Dimension(200, 200));
		// Lay out drawing panel
		add(scroller, BorderLayout.CENTER);
		// Initialized object
		element = new Vector<PaintObject>();
		// set radio button
		Line = new JRadioButton("Line");
		Line.setActionCommand("Line");
		Line.setActionCommand("Line");
		Rectangle = new JRadioButton("Rectangle");
		Rectangle.setActionCommand("Rectangle");
		Rectangle.setActionCommand("Rectangle");
		Oval = new JRadioButton("Oval");
		Oval.setActionCommand("Oval");
		Oval.setActionCommand("Oval");
		image = new JRadioButton("Image");
		image.setActionCommand("Image");
		image.setActionCommand("Image");
		group = new ButtonGroup();
		group.add(Line);
		group.add(Rectangle);
		group.add(Oval);
		group.add(image);
		// set the button panel
		interactionButtons = new JPanel(new GridLayout(1, 5));
		// set select color button
		ColorButtom = new JButton("Choose Color");
		ColorButtom.setActionCommand("color");
		// add to the button
		interactionButtons.add(ColorButtom);
		interactionButtons.add(Line);
		interactionButtons.add(Rectangle);
		interactionButtons.add(Oval);
		interactionButtons.add(image);
		// set listener
		addListener();
		// set up the GUI
		add(interactionButtons, BorderLayout.SOUTH);
	}

	public void addListener() {
		Line.addActionListener(new BasicButtonListener());
		Rectangle.addActionListener(new BasicButtonListener());
		Oval.addActionListener(new BasicButtonListener());
		image.addActionListener(new BasicButtonListener());
		ColorButtom.addActionListener(new BasicButtonListener());
	}
    //motion the mouse action
	public boolean checkClick() {
		ClickCheck++;
		if (ClickCheck == 1) {
			return true;
		} else {
			ClickCheck = 0;
			return false;
		}
	}
    //all listener
	public class BasicButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (!cmd.equals("color")) {
				Action = cmd;
			} else {
				SelectedColor = JColorChooser.showDialog(null, "Choose a Color", null);
			}

		}

	}

	@Override
	public void mouseMoved(MouseEvent evt) {
		currentPoint.setLocation(evt.getX(), evt.getY());
		if (ClickCheck == 1) {
			paintAll();

		}
	}
    // recall all images drawed. 
	private void revoke() {
		if (element.size() != drawed) {
			element.remove(element.size() - 1);
			revoke();
		}

		return;

	}
   // paint all images. 
	public void paintAll() {
		PaintObject tmp;
		switch (Action) {

		case "Line":
			tmp = new Line(SelectedColor, LastClicked.getLocation(), currentPoint.getLocation());
			element.add(tmp);
			break;

		case "Rectangle":
			tmp = new Rectangle(SelectedColor, LastClicked.getLocation(), currentPoint.getLocation());
			element.add(tmp);
			break;
		case "Oval":
			tmp = new Oval(SelectedColor, LastClicked.getLocation(), currentPoint.getLocation());
			element.add(tmp);
			break;

		case "Image":
			tmp = new image(SelectedColor, LastClicked.getLocation(), currentPoint.getLocation());
			element.add(tmp);
			break;

		default:
			break;
		}

		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (Action.equals("")) {
			return;
		}

		if (checkClick()) {
			LastClicked = new Point(currentPoint.getLocation());
		} else {
			drawed++;
			paintAll();
		}

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	class DrawingPanel extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			// draw all of the paint objects
			for (int i = 0; i < element.size(); i++) {
				element.get(i).draw(g);
			}
			revoke();
		}
	}

}