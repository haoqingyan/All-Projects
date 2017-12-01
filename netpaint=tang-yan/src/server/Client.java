//Author : Yichao Tang && Haoqing Yan
// the client, sent message to server, recerive all message from server.
package server;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;



import GUI.Netpaint;
import GUI.Netpaint.BasicButtonListener;
import model.*;

public class Client extends JFrame implements Serializable, MouseMotionListener, MouseListener{
	private static final String ADDRESS = "localhost";
	ObjectInputStream fromServer;
	ObjectOutputStream toServer;
	Socket socket;
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
	private Vector<PaintObject> element2;
	private Point currentPoint = new Point();
	private Point LastClicked;
	private int ClickCheck = 0;
	private int drawed = 0;
	private boolean drawing= false;
	//set up the client.
	public Client(){
		this.setLayout(new BorderLayout());
		this.setSize(1080,720);
		this.setTitle("NetPaint");
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
		element2 = new Vector<PaintObject>();
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
	    
	    openConnection();
	    ServerListener serverListener = new ServerListener();
	    serverListener.start();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		new Client().setVisible(true);
	}	
	//open the connection with server.
	private void openConnection() {
		try {
			socket = new Socket(ADDRESS, Server.PORT_NUMBER);
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			System.out.println("cannot connect to server");
			e.printStackTrace();
		}
	}
	
	//add the server listnere, receive from server.
	private class ServerListener extends Thread implements Serializable{
		public void run() {
			try {
				while (true){
					element=((Vector<PaintObject>)fromServer.readObject());
					
					repaint();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}

	}
	//add the lisenter to run the cilent.
	public void addListener() {
		Line.addActionListener(new BasicButtonListener());
		Rectangle.addActionListener(new BasicButtonListener());
		Oval.addActionListener(new BasicButtonListener());
		image.addActionListener(new BasicButtonListener());
		ColorButtom.addActionListener(new BasicButtonListener());
	}
    //check click, for draw use.
	public boolean checkClick() {
		ClickCheck++;
		if (ClickCheck == 1) {
			return true;
		} else {
			ClickCheck = 0;
			return false;
		}
	}

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

	public void paintAll() {
		PaintObject tmp = null;
		switch (Action) {

		case "Line":
			tmp = new Line(SelectedColor, LastClicked.getLocation(), currentPoint.getLocation());
			
			break;

		case "Rectangle":
			tmp = new Rectangle(SelectedColor, LastClicked.getLocation(), currentPoint.getLocation());
			break;
		case "Oval":
			tmp = new Oval(SelectedColor, LastClicked.getLocation(), currentPoint.getLocation());
			break;

		case "Image":
			tmp = new image(SelectedColor, LastClicked.getLocation(), currentPoint.getLocation());
			break;
		}
		
		if(drawing)
		{
		
			element2.add(tmp);
		}
		else
		{
			element.add(tmp);
			try {
				toServer.writeObject(element);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

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
			drawing=true;
		} else {
			drawing=false;
			element2.removeAllElements();
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
			if(drawing)
			{
				for (int i = 0; i < element2.size(); i++) {
					element2.get(i).draw(g);
				}
				revoke();
			}
		}

		private void revoke() {
			if (element2.size() != 1) {
				element2.removeElementAt(element2.size()-1);
				revoke();
			}
			return;

		}
	}

}