package runGame;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Trainer.Trainer;

public class gameOverGUI extends JFrame{
	private playerStatus ha;
	private Image playerImage;
	private Trainer player;
	
	public gameOverGUI(Trainer player){
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.player = player;
	    setSize(500, 500);
	    setLocation(700, 300);
	    try {
			playerImage = ImageIO.read(new File("./images/player.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    ha = new playerStatus(player);
	    add(ha);
	    this.setVisible(true);
	}

	private class playerStatus extends JPanel{
		
		private JTextArea allInfo;
		private String allInfoString;
		public playerStatus(Trainer player) {
			repaint();
			setAllInfo();
			allInfo = new JTextArea(allInfoString);
			add(allInfo);
			
		}
		
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;
		    g2.drawImage(playerImage, 20, 20, null);
		}
		
		public void setAllInfo(){
			allInfoString = "Game Over \n players Status:";
			allInfoString = allInfoString +"\n Number of PokeBall:   " +player.getItems(0).getNums();
			allInfoString = allInfoString +"\n Number of HP Potion:   " +player.getItems(1).getNums();
			allInfoString = allInfoString +"\n Number of MP Potion:   " +player.getItems(2).getNums();
			

			allInfoString  = allInfoString + "\n you have "+player.getAliveNum()+"Pokemons \n";
			for(int i = 0; i < player.getPokNum(); i++){
				allInfoString = allInfoString+"\n   "+player.getPokemons(i).getName();
			}	
				
			
		}
	}
}
