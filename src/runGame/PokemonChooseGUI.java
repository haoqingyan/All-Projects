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


public class PokemonChooseGUI extends JFrame{
	
	showPokemonPanel showItemP;
	
    JButton BulbasaurB  = new JButton("Bulbasaur");
	JButton ButterFreeB  = new JButton("ButterFree");
	JButton CharizardB  = new JButton("CharizardB");
	JButton CharmanderB = new JButton("Charmander");
	JButton JigglypuffB = new JButton("Jigglypuff");
	JButton MankeyB = new JButton("Mankey");
	JButton MewB = new JButton("Mew");
	JButton PikachuB = new JButton("Pikachu");
	JButton SquirtleB = new JButton("Squirtle");
	JButton VulpixB = new JButton("Vulpix");
	JButton ConfirmB = new JButton("Confirm");
	JButton NinetalesB = new JButton("Ninetales");
	
	private Battle newbattle;
	private BufferedImage BulbasaurImage,NinetalesImage, Bag, ButterFreeImage,CharizardImage,CharmanderImage,JigglypuffImage,MankeyImage,MewImage,PikachuImage,SquirtleImage,VulpixImage;
	private Image defaultImage;
	
	
	public PokemonChooseGUI(Battle b){
		newbattle = b;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(660, 200);
		setLocation(600, 650);
	    setTitle("Choose Pokemon");
	    
	    try {
	    	BulbasaurImage = ImageIO.read(new File("./images/BulbasaurIcon.png"));
	    	ButterFreeImage = ImageIO.read(new File("./images/ButterFreeIcon.png"));
	    	CharizardImage = ImageIO.read(new File("./images/CharizardIcon.png"));
	    	CharmanderImage = ImageIO.read(new File("./images/CharmanderIcon.png"));
	    	JigglypuffImage = ImageIO.read(new File("./images/JigglypuffIcon.png"));
	    	MankeyImage = ImageIO.read(new File("./images/MankeyIcon.png"));
	    	MewImage = ImageIO.read(new File("./images/MewIcon.png"));
	    	PikachuImage = ImageIO.read(new File("./images/PikachuIcon.png"));
	    	SquirtleImage = ImageIO.read(new File("./images/SquirtleIcon.png"));
	    	VulpixImage = ImageIO.read(new File("./images/VulpixIcon.png"));
	    	NinetalesImage = ImageIO.read(new File("./images/NineTalesIcon.png"));
	    	Bag = ImageIO.read(new File("./images/Bag.png"));
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
//	    showItemP = new showPokemonPanel();
	    ConfirmB.addActionListener(new ConfirmBListener());
	    
		add(new showPokemonPanel(), BorderLayout.CENTER);
		add(ConfirmB, BorderLayout.SOUTH);
		setVisible(true);
		
	}
	private class ConfirmBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
		
	}
	private class showPokemonPanel extends JPanel{
		
		public showPokemonPanel(){
			BulbasaurB.addActionListener(new BulbasaurBListener());
			ButterFreeB.addActionListener(new ButterFreeBListener());
			CharizardB.addActionListener(new CharizardBListener());
			CharmanderB.addActionListener(new CharmanderBListener());
			JigglypuffB.addActionListener(new JigglypuffBListener());
			MankeyB.addActionListener(new MankeyBListener());
			MewB.addActionListener(new MewB());
			PikachuB.addActionListener(new PikachuBListener());
			SquirtleB.addActionListener(new SquirtleBListener());
			VulpixB.addActionListener(new VulpixBListener());
			NinetalesB.addActionListener(new NinetalesBListener());
			
			
			for (int i  =0 ;i<newbattle.player.getAliveNum(); i++){
				if(newbattle.player.getPokemons(i).getName().equals("Bulbasaur")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(BulbasaurB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Butterfree")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(ButterFreeB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Charizard")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(CharizardB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Charmander")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(CharmanderB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Jigglypuff")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(JigglypuffB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("ManKey")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(MankeyB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Mew")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(MewB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Pikachu")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(PikachuB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Squirtle")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(SquirtleB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Vulpix")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(VulpixB);
				}
				if(newbattle.player.getPokemons(i).getName().equals("Ninetales")&& newbattle.player.getPokemons(i).getHP()>0){
					System.out.println(i);
					add(NinetalesB);
				}
				
			}
			defaultImage = Bag.getScaledInstance(100, 100, 0);
		
		}
		
		private class BulbasaurBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Bulbasaur");
				defaultImage = BulbasaurImage;
				repaint();
				newbattle.command(4);
			
			}	
		}
		private class ButterFreeBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Butterfree");
				defaultImage = ButterFreeImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class CharizardBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Charizard");
				defaultImage = CharizardImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class CharmanderBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Charmander");
				defaultImage = CharmanderImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class JigglypuffBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Jigglypuff");
				defaultImage = JigglypuffImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class MankeyBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Mankey");
				defaultImage = MankeyImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class MewB implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Mew");
				defaultImage = MewImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class PikachuBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Pikachu");
				defaultImage = PikachuImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class SquirtleBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Squirtle");
				defaultImage = SquirtleImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class VulpixBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Vulpix");
				defaultImage = VulpixImage;
				repaint();
				newbattle.command(4);
			}
		}
		private class NinetalesBListener implements ActionListener{			
			public void actionPerformed(ActionEvent e) {
				newbattle.playersPokemon = newbattle.player.getPokemon("Ninetales");
				defaultImage = NinetalesImage;
				repaint();
				newbattle.command(4);
			}
		}
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;
		    g2.drawImage(defaultImage, 20 , 20 ,null);
		}    
	
	}
}
