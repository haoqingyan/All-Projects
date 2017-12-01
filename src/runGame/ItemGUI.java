package runGame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ItemGUI extends JFrame{
	
	showItemPanel showItemP;
	
    JButton HPrecoverB  = new JButton("HPrecover");
	JButton MPrecoverB  = new JButton("MPrecover");
	JButton PokeballB  = new JButton("Pokeball");
	JButton ConfirmB = new JButton("confire");
	private Battle newbattle;
	private BufferedImage PokeballImage, Bag, MPpotion,HPpotion;
	private Image defaultImage;
	private boolean catchend;
	
	
	public ItemGUI(Battle b){
		catchend = false;
		newbattle = b;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(660, 200);
		setLocation(600, 650);
	    setTitle("Choose Item");
	    
	    try {
			PokeballImage = ImageIO.read(new File("./images/PokeBallItem.png"));
			Bag = ImageIO.read(new File("./images/Bag.png"));
			MPpotion = ImageIO.read(new File("./images/MPpotion.png"));
			HPpotion = ImageIO.read(new File("./images/HPpotion.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    showItemP = new showItemPanel();
	    ConfirmB.addActionListener(new ConfirmBListener());
	    
		add(showItemP, BorderLayout.CENTER);
		add(ConfirmB, BorderLayout.SOUTH);
		
		
	}
	
	public boolean getEnd(){
		return catchend;
	}
	
	private class ConfirmBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
		
	}
	private class showItemPanel extends JPanel{
		
		public showItemPanel(){
			HPrecoverB.addActionListener(new HPrecoverBListener());
			MPrecoverB.addActionListener(new MPrecoverBListener());
			PokeballB.addActionListener(new PokeballBListener());
			
			add(HPrecoverB);
			add(MPrecoverB);
			add(PokeballB);
			defaultImage = Bag.getScaledInstance(100, 100, 0);
			
		}
		
		private class HPrecoverBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				defaultImage = HPpotion;
				repaint();
				
				if(newbattle.player.getItems(1).getNums() != 0){
					newbattle.currentPokemon().changeHP(-20);
					newbattle.player.getItems(1).addNums(-1);
					newbattle.command(2);

				}
				else{
					JOptionPane.showMessageDialog(null, "you do not have HP medecine");
				}
			}
			
		}
		private class MPrecoverBListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				defaultImage = MPpotion;
				repaint();
				if(newbattle.player.getItems(2).getNums() != 0){
					newbattle.currentPokemon().changeMP(-10);
					newbattle.player.getItems(2).addNums(-1);
					newbattle.command(5);
				}
				else{
					JOptionPane.showMessageDialog(null, "you do not have MP medecine");
				}
				
			}
			
		}
		private class PokeballBListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				defaultImage = PokeballImage;
				repaint();
				if(newbattle.player.getItems(0).getNums() != 0){
					newbattle.player.getItems(0).addNums(-1);
					newbattle.command(3);
					
					catchend = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "you do not have pokeball");
				}
				
			} 
		}
		
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;
		    g2.drawImage(defaultImage, 20 , 20 ,null);
		}    
	}
}
