package runGame;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import Map.blueHouseMap;
import Trainer.Trainer;


public class startBlueHouse extends JFrame {
	

//	public static void main(String[] args) {
//		Trainer trainer = new Trainer();
//		startBlueHouse window = new startBlueHouse(trainer);
//		window.setVisible(true);
//	}
	
	private blueHouseImageView imagePanel;
	private Game game;
	private blueHouseMap enterBlueHouseMap;
	
	  public startBlueHouse(Trainer trainer) {
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setSize(340, 360);
		    setLocation(700, 300);
		    setTitle("BlueHouse"); 
		   
		    game = new Game(8, 5, trainer);
		    enterBlueHouseMap = new blueHouseMap();
		    imagePanel = new blueHouseImageView(game,enterBlueHouseMap);
		    game.addObserver(imagePanel);
		    add(imagePanel);
		    
		    setFocusable(true);
		    addKeyListener(new ArrowKeyListener());
		    this.setVisible(true);
		  }
	  
/*
 * 
 * ArrowKeyListener: move player
 * 
 * 
 */
	  private class ArrowKeyListener implements KeyListener {

		    @Override
		    public void keyPressed(KeyEvent ke) {

		      if (ke.getKeyCode() == KeyEvent.VK_UP)
		        game.movePlayer(Direction.NORTH);

		      if (ke.getKeyCode() == KeyEvent.VK_DOWN)
		        game.movePlayer(Direction.SOUTH);

		      if (ke.getKeyCode() == KeyEvent.VK_LEFT)
		        game.movePlayer(Direction.WEST);

		      if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
		        game.movePlayer(Direction.EAST);
		      
		    }

		    @Override
		    public void keyTyped(KeyEvent e) {
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		    }
		 
	  }
}
