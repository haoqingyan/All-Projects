package runGame;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import javafx.scene.media.MediaPlayer;

public class battleViewObserver extends JPanel implements Observer {
	private MediaPlayer mp;
	private BufferedImage battleBackground;
	private BufferedImage playerIcon;
	private Image enemyTrainerIcon;
	private Image tainerIcons;
	private BufferedImage Bulbasaur,Charmander,Charizard,catchIcons,HPSkill,Trainerskill,MPSkill,changeSkill, thunderSkill, fireSkill, Pikachu, Squirtle, Vulpix, Jigglypuff, ButterFree, ManKey, NineTales,Mew;
	private Image BulbasaurIcon,CharmanderIcon,CharizardIcon, PikachuIcon, SquirtleIcon, VulpixIcon, JigglypuffIcon, ButterFreeIcon, ManKeyIcon, NineTalesIcon,MewIcon;
	
	private BufferedImage currentPokemonIcons;
	private BufferedImage currentPokemonIcon;
	private BufferedImage currentEnemyIcons;
	private BufferedImage currentEnemyIcon;
	
	private Image catchIcon;
	private Image fireEffect;
	private Image thunderEffect;
	private Image HPEffect;
	private Image MPEffect;
	private Image changeEffect;
	private Image trainerIcon;
	private Image currentTrainer;
	
	private Battle b;
	private int playersPokemonX =200;
	private int playersPokemonY =200;
	private int catchBallX = 220;
	private int catchBallY = 200;
	private int trainerX = 160;
	private int trainerY = 180;
	private int enemyPokemonX = 440;
	private int enemyPokemonY = 200;
	private JTextArea playersPokemonStats;
	private JTextArea enenmyPokemonStats;
	
	
	

	
	public battleViewObserver(Battle b){
		this.b = b;
		if(b.currentPokemon() == null){
			playersPokemonStats = new JTextArea("No pokemon");	
		}
		else{
			playersPokemonStats = new JTextArea(b.currentPokemon().getName()+"   HP:   "+ b.currentPokemon().getHP()+"   MP:   " + b.currentPokemon().getMP());	
		}
		enenmyPokemonStats = new JTextArea(b.enemyPokemon.getName()+"   HP:   "+ b.enemyPokemon.getHP()+"   MP:   " + b.enemyPokemon.getMP());
		add(playersPokemonStats);
		add(enenmyPokemonStats);
		
		
		try {
			fireSkill = ImageIO.read(new File("./images/fireSkill.png"));
			thunderSkill = ImageIO.read(new File("./images/thunderEffect.png"));
			HPSkill = ImageIO.read(new File("./images/HPSkill.png"));
			changeSkill = ImageIO.read(new File("./images/change.png"));
			tainerIcons = ImageIO.read(new File("./images/mainCharacter.png"));
			catchIcons = ImageIO.read(new File("./images/catchIcons.png"));
			Trainerskill  =ImageIO.read(new File("./images/trainer.png"));
			MPSkill = ImageIO.read(new File("./images/mp.png"));
			
			Charmander = ImageIO.read(new File("./images/Charmander.png"));
			Charizard = ImageIO.read(new File("./images/Charizard.png"));
			Bulbasaur = ImageIO.read(new File("./images/Bulbasaur.png"));
			Mew = ImageIO.read(new File("./images/Mew.png"));
			Pikachu = ImageIO.read(new File("./images/Pikachu.png"));
			Squirtle = ImageIO.read(new File("./images/Squirtle.png"));
			Vulpix = ImageIO.read(new File("./images/Vulpix.png"));
			Jigglypuff = ImageIO.read(new File("./images/Jigglypuff.png"));
			ButterFree = ImageIO.read(new File("./images/ButterFree.png"));
			ManKey = ImageIO.read(new File("./images/ManKey.png"));
			NineTales = ImageIO.read(new File("./images/NineTales.png"));
			
			CharmanderIcon = ImageIO.read(new File("./images/CharmanderIcon.png"));
			CharmanderIcon = CharmanderIcon.getScaledInstance(100, 100, 0);	
			CharizardIcon = ImageIO.read(new File("./images/CharmanderIcon.png"));
			CharizardIcon = CharizardIcon.getScaledInstance(100, 100, 0);
			BulbasaurIcon = ImageIO.read(new File("./images/BulbasaurIcon.png"));
			BulbasaurIcon = BulbasaurIcon.getScaledInstance(100, 100, 0);
			MewIcon = ImageIO.read(new File("./images/MewIcon.png"));
			MewIcon = MewIcon.getScaledInstance(100, 100, 0);
			PikachuIcon = ImageIO.read(new File("./images/PikachuIcon.png"));
			PikachuIcon = PikachuIcon.getScaledInstance(100, 100, 0);
			SquirtleIcon = ImageIO.read(new File("./images/SquirtleIcon.png"));
			SquirtleIcon = SquirtleIcon.getScaledInstance(100, 100, 0);
			VulpixIcon = ImageIO.read(new File("./images/VulpixIcon.png"));
			VulpixIcon = VulpixIcon.getScaledInstance(100, 100, 0);
			JigglypuffIcon = ImageIO.read(new File("./images/JigglypuffIcon.png"));
			JigglypuffIcon = JigglypuffIcon.getScaledInstance(100, 100, 0);
			ButterFreeIcon = ImageIO.read(new File("./images/ButterFreeIcon.png"));
			ButterFreeIcon = ButterFreeIcon.getScaledInstance(100, 100, 0);
			ManKeyIcon = ImageIO.read(new File("./images/ManKeyIcon.png"));
			ManKeyIcon = ManKeyIcon.getScaledInstance(100, 100, 0);
			NineTalesIcon = ImageIO.read(new File("./images/NineTalesIcon.png"));
			NineTalesIcon = NineTalesIcon.getScaledInstance(100, 100, 0);
			
			playerIcon = ImageIO.read(new File("./images/player.png"));
			enemyTrainerIcon = ImageIO.read(new File("./images/zhiba.png"));
			battleBackground = ImageIO.read(new File("./images/battleBackground.png"));
			currentTrainer  = Trainerskill.getSubimage(0, 0, 192, 192);
			currentTrainer = currentTrainer.getScaledInstance(96, 96, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//-----------------------player's current pokemonIcon --------------------------
		if(b.currentPokemon() == null){
			currentPokemonIcon = null;
		}
		else if((b.currentPokemon().getName().equals("Bulbasaur"))){
	    	currentPokemonIcons = Bulbasaur;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Charmander")){
	    	currentPokemonIcons = Charmander;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Butterfree")){
	    	currentPokemonIcons = ButterFree;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Charizard")){
	    	currentPokemonIcons = Charizard;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Jigglypuff")){
	    	currentPokemonIcons = Jigglypuff;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("ManKey")){
	    	currentPokemonIcons = ManKey;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Mew")){
	    	currentPokemonIcons = Mew;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Ninetales")){
	    	currentPokemonIcons = NineTales;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Pikachu")){
	    	currentPokemonIcons = Pikachu;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Squirtle")){
	    	currentPokemonIcons = Squirtle;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }
	    else if(b.currentPokemon().getName().equals("Vulpix")){
	    	currentPokemonIcons = Vulpix;
	    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
	    }

	    
	    //---------------------- enemy's PokemonIcon --------------
	    if(b.enemyPokemon.getName().equals("Bulbasaur")){
	    	currentEnemyIcons = Bulbasaur;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = BulbasaurIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Charmander")){
	    	currentEnemyIcons = Charmander;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = CharmanderIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Butterfree")){
	    	currentEnemyIcons = ButterFree;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = ButterFreeIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Charizard")){
	    	currentEnemyIcons = Charizard;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = CharizardIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Jigglypuff")){
	    	currentEnemyIcons = Jigglypuff;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = JigglypuffIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("ManKey")){
	    	currentEnemyIcons = ManKey;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = ManKeyIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Mew")){
	    	currentEnemyIcons = Mew;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = MewIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Ninetales")){
	    	currentEnemyIcons = NineTales;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = NineTalesIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Pikachu")){
	    	currentEnemyIcons = Pikachu;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = PikachuIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Squirtle")){
	    	currentEnemyIcons = Squirtle;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = SquirtleIcon;
	    }
	    else if(b.enemyPokemon.getName().equals("Vulpix")){
	    	currentEnemyIcons = Vulpix;
	    	currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
	    	enemyTrainerIcon = VulpixIcon;
	    }
	}
	//-----------------------EnemyTrainerIcon --------------------------
	
	
	//-----------------------------------------READ IMAGE IS ABOVE -----------------------------------------------
	
	public void update(Observable o, Object arg) {
	    b = (Battle) o;
		if (b.command == 1){
//			b.enemyPokemon.changeHP( b.playersPokemon.trueAttack(b.enemyPokemon.getProperty()));
//			b.playersPokemon.changeHP( b.enemyPokemon.trueAttack(b.playersPokemon.getProperty()));
//			b.enemyPokemon.changeMP(10);
//			b.playersPokemon.changeMP(10);
			attackAnimation();
			b.command =0;	
		}
		if(b.command == 2){
			healAnimation();
			b.command =0;
		}
		if(b.command == 3){
			catchAnimation();
			b.command =0;
		
		}
		if(b.command == 4){
			
			pokemonChangeAnimation();
			b.command =0;
		}
		if (b.command == 5){
			mprAnimation();
			b.command =0;
		}
		
	}
	
	private javax.swing.Timer timer;
	private int tic =0;
	  
	public void attackAnimation(){
		timer = new Timer(100, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
		    if(tic<20){
		    	if(tic%4 == 0){
		    		currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
		    		playersPokemonX = playersPokemonX + 10;
		    	}
		    	if(tic%4 == 1){
		    		currentPokemonIcon = currentPokemonIcons.getSubimage(48, 96, 48, 48);
		    		playersPokemonX = playersPokemonX + 10;
		    	}
		    	if(tic%4 == 2){
		    		currentPokemonIcon = currentPokemonIcons.getSubimage(96, 96, 48, 48);
		    		playersPokemonX = playersPokemonX + 10;
		    	}
		    	if(tic%4 == 3){
		    		currentPokemonIcon = currentPokemonIcons.getSubimage(144, 96, 48, 48);
		    		playersPokemonX = playersPokemonX + 10;
		    	}
		    	repaint();
		    	tic = tic +1; 		
		    }
		    else if(tic == 20){
	    		fireEffect = fireSkill.getSubimage(0, 0, 192, 192);
	    		fireEffect = fireEffect.getScaledInstance(48, 48, 0);
	    		tic ++;
	    		repaint();
	    	}
		    else if(tic == 21){
	    		fireEffect = fireSkill.getSubimage(192, 0, 192, 192);
	    		fireEffect = fireEffect.getScaledInstance(48, 48, 0);
	    		tic ++;
	    		repaint();
	    	}
		    else if(tic == 22){
	    		fireEffect = fireSkill.getSubimage(384, 0, 192, 192);
	    		fireEffect = fireEffect.getScaledInstance(48, 48, 0);
	    		tic ++;
	    		repaint();
	    	}
		    else if(tic == 23){
	    		fireEffect = fireSkill.getSubimage(576, 0, 192, 192);
	    		fireEffect = fireEffect.getScaledInstance(48, 48, 0);
	    		tic ++;
	    		repaint();
	    	}
		    else if(tic == 24){
	    		fireEffect = fireSkill.getSubimage(768, 0, 192, 192);
	    		fireEffect = fireEffect.getScaledInstance(48, 48, 0);
	    		tic ++;
	    		repaint();
	    	}
		    else if(tic == 25){
	    		fireEffect = fireSkill.getSubimage(0, 192, 192, 192);
	    		fireEffect = fireEffect.getScaledInstance(48, 48, 0);
	    		tic ++;
	    		repaint();
	    	}
		    else if(tic == 26){
	    		fireEffect = fireSkill.getSubimage(192, 192, 192, 192);
	    		fireEffect = fireEffect.getScaledInstance(48, 48, 0);
	    		tic ++;
	    		repaint();
	    	}
		    else if(tic == 27){
	    		fireEffect = null;
	    		tic ++;
	    		repaint();
	    	}
		    else if (tic ==28){        //----   finish one round attack, now it is enemy attack  -----
		    	playersPokemonX =200;
			    tic ++;
			    repaint(); 
			    enemyAttackAnimation();
		    }
		    else {
		    	tic = 0;
		    	timer.stop();
		    	b.enemyPokemon.changeHP( b.playersPokemon.trueAttack(b.enemyPokemon.getProperty()));
				b.playersPokemon.changeMP(10);
		    	playersPokemonStats.setText(b.currentPokemon().getName()+"   HP:   "+ b.currentPokemon().getHP()+"   MP:   " + b.currentPokemon().getMP());
		    	enenmyPokemonStats.setText(b.enemyPokemon.getName()+"   HP:   "+ b.enemyPokemon.getHP()+"   MP:   " + b.enemyPokemon.getMP());
		    	if(b.enemyPokemon.getHP()<=0){
		    		b.player.removeAlivePokemon(b.currentPokemon());
		    		b.end = true;
		    		b.setEnd();
		    		JOptionPane.showMessageDialog(null, "Yeah! "+b.currentPokemon().getName() +" beat "+ b.enemyPokemon.getName());
		    	}
		    }
		    
		}
		
	    Timer enemyTimer;
	    public void enemyAttackAnimation(){
	    	if(b.enemyPokemon.getHP()>0){
	    		enemyTimer = new Timer(100, new enemyTimerListener());
	    		enemyTimer.start();
	    	}
	    	else{
    			currentEnemyIcon = null;
    			repaint();
    			enenmyPokemonStats.setText(b.enemyPokemon.getName()+"   HP:   0   MP:   0");
	    	}
	    }
		public class enemyTimerListener implements ActionListener{
		    private int etic = 0;     	
		    public void actionPerformed(ActionEvent arg0){
		    	if(etic<20){
		    		if(etic%4 == 0){
		    			currentEnemyIcon = currentEnemyIcons.getSubimage(0, 48, 48, 48);
		    			enemyPokemonX = enemyPokemonX -10;
		    		}
		    		if(etic%4 == 1){
		    			currentEnemyIcon = currentEnemyIcons.getSubimage(48, 48, 48, 48);
		    			enemyPokemonX = enemyPokemonX -10;			    			
		    		}
		    		if(etic%4 == 2){
		    			currentEnemyIcon = currentEnemyIcons.getSubimage(96, 48, 48, 48);
		    			enemyPokemonX = enemyPokemonX -10;		
		    		}
		    		if(etic%4 == 3){
		    			currentEnemyIcon = currentEnemyIcons.getSubimage(144, 48, 48, 48);
		    			enemyPokemonX = enemyPokemonX -10;
		    		}
		    		etic = etic+1;
		    		repaint();
		    	}
		    	else if(etic == 20){
		    		thunderEffect = thunderSkill.getSubimage(768, 0, 192, 384);
		    		thunderEffect = thunderEffect.getScaledInstance(96, 96, 0);
		    		etic ++;
		    		repaint();
		    	}
			    else if(etic == 21){
			    	thunderEffect = thunderSkill.getSubimage(576, 0, 192, 384);
			    	thunderEffect = thunderEffect.getScaledInstance(96, 96, 0);
		    		etic ++;
		    		repaint();
		    	}
			    else if(etic == 22){
			    	thunderEffect = thunderSkill.getSubimage(384, 0, 192, 384);
			    	thunderEffect = thunderEffect.getScaledInstance(96, 96, 0);
		    		etic ++;
		    		repaint();
		    	}
			    else if(etic == 23){
			    	thunderEffect = thunderSkill.getSubimage(192, 0, 192, 384);
			    	thunderEffect = thunderEffect.getScaledInstance(96, 96, 0);
		    		etic ++;
		    		repaint();
		    	}
			    else if(etic == 24){
			    	thunderEffect = thunderSkill.getSubimage(0, 0, 192, 384);
			    	thunderEffect = thunderEffect.getScaledInstance(96, 96, 0);
		    		etic ++;
		    		repaint();
		    	}
		    	else{
		    		thunderEffect = null;
		    		if(b.currentPokemon().getHP()<=0){
		    			currentPokemonIcon = null;
		    			playersPokemonStats.setText(b.currentPokemon().getName()+"   HP:   0   MP:   0");
		    			b.player.addDeadPokemon(b.currentPokemon());
		    			b.player.removeAlivePokemon(b.currentPokemon());
		    			b.playersPokemon = b.player.getNextPokemon();
		    		}
		    		else{
//		    			b.enemyPokemon.changeHP( b.playersPokemon.trueAttack(b.enemyPokemon.getProperty()));
		    			b.playersPokemon.changeHP( b.enemyPokemon.trueAttack(b.playersPokemon.getProperty()));
		    			b.enemyPokemon.changeMP(10);
//		    			b.playersPokemon.changeMP(10);
		    			playersPokemonStats.setText(b.currentPokemon().getName()+"   HP:   "+ b.currentPokemon().getHP()+"   MP:   " + b.currentPokemon().getMP());
				    	enenmyPokemonStats.setText(b.enemyPokemon.getName()+"   HP:   "+ b.enemyPokemon.getHP()+"   MP:   " + b.enemyPokemon.getMP());
		    		}
		    		if(b.player.alivePokemons.size()==0&& b.player.getItems(0).getNums()==0){
		    			gameOverGUI a  = new gameOverGUI(b.player);
		    			a.setVisible(true);
		    		}
		    		enemyTimer.stop();
		    		etic =0;
		    		enemyPokemonX =440;
		    		repaint();
		    		
		    	}
		    }
		} 
		
	  }
	
	Timer healAnimationTimer;
	private int healtic = 0;
	public void healAnimation(){
		healAnimationTimer = new Timer(100, new healListener());
		healAnimationTimer.start();
		
	}
	private class healListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		    if(healtic<5){
		    	if(healtic == 0){
		    		HPEffect = HPSkill.getSubimage(0, 0, 192, 192);
		    		HPEffect = HPEffect.getScaledInstance(48, 48, 0);
		    	}
		    	if(healtic == 1){
		    		HPEffect = HPSkill.getSubimage(192, 0, 192, 192);
		    		HPEffect = HPEffect.getScaledInstance(48, 48, 0);
		    	}
		    	if(healtic == 2){
		    		HPEffect = HPSkill.getSubimage(384, 0, 192, 192);
		    		HPEffect = HPEffect.getScaledInstance(48, 48, 0);
		    	}
		    	if(healtic== 3){
		    		HPEffect = HPSkill.getSubimage(576, 0, 192, 192);
		    		HPEffect = HPEffect.getScaledInstance(48, 48, 0);
		    	}
		    	if(healtic== 4){
		    		HPEffect = HPSkill.getSubimage(768, 0, 192, 192);
		    		HPEffect = HPEffect.getScaledInstance(48, 48, 0);
		    	}
		    	repaint();
		    	healtic = healtic +1; 		
		    }
		    else{
		    	healtic =0;
		    	HPEffect = null;
		    	repaint();
		    	healAnimationTimer.stop();
		    }
		    if(b.currentPokemon().getHP()> b.currentPokemon().getMaxHP()){
		    	b.currentPokemon().changeHP(b.currentPokemon().getHP()- b.currentPokemon().getMaxHP());
		    	System.out.println(b.currentPokemon().getHP());
		    }
		    playersPokemonStats.setText(b.currentPokemon().getName()+"   HP:   "+ b.currentPokemon().getHP()+"   MP:   " + b.currentPokemon().getMP());
		    enenmyPokemonStats.setText(b.enemyPokemon.getName()+"   HP:   "+ b.enemyPokemon.getHP()+"   MP:   " + b.enemyPokemon.getMP());
		    
		}
		
	}
	Timer catchAnimationTimer;
	private int cathtic = 0;
	public void catchAnimation(){
		trainercatch();
		catchAnimationTimer = new Timer(100, new catchListener());
		catchAnimationTimer.start();
	}
	private class catchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		    if(cathtic<16){
		    	if(cathtic == 0){
		    		catchIcon =  catchIcons.getSubimage(0, 0, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic == 1){
		    		catchIcon =  catchIcons.getSubimage(192, 0, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic == 2){
		    		catchIcon =  catchIcons.getSubimage(384, 0, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic== 3){
		    		catchIcon =  catchIcons.getSubimage(576, 0, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic== 4){
		    		catchIcon =  catchIcons.getSubimage(0, 192, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic == 5){
		    		catchIcon =  catchIcons.getSubimage(192, 192, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic == 6){
		    		catchIcon =  catchIcons.getSubimage(384, 192, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic == 7){
		    		catchIcon =  catchIcons.getSubimage(576, 192, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic== 8){
		    		catchIcon =  catchIcons.getSubimage(0, 384, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic== 9){
		    		catchIcon =  catchIcons.getSubimage(192, 384, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic == 10){
		    		catchIcon =  catchIcons.getSubimage(384, 384, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic == 11){
		    		catchIcon =  catchIcons.getSubimage(576, 384, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic == 12){
		    		catchIcon =  catchIcons.getSubimage(0, 576, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic== 13){
		    		catchIcon =  catchIcons.getSubimage(192, 576, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic== 14){
		    		catchIcon =  catchIcons.getSubimage(384, 576, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	if(cathtic== 15){
		    		catchIcon =  catchIcons.getSubimage(576, 576, 192, 192);
		    		catchIcon = catchIcon.getScaledInstance(192, 192, 0);
		    		catchBallX  = catchBallX + 15;
		    	}
		    	repaint();
		    	cathtic = cathtic +1; 		
		    }
		    else{
		    	cathtic =0;
		    	catchIcon = null;
		    	currentEnemyIcon = null; 	
		    	repaint();
		    	catchBallX  = 220;
		    	catchAnimationTimer.stop();
		    	b.player.addPokemons(b.enemyPokemon);
		    	b.catchindicate =true;
		    	b.setEnd();
		    	
		    }
		    
		   
		}
		
	}
	Timer trainerAnimationTimer;
	private int trainertic = 0;
	public void trainercatch(){
		trainerAnimationTimer = new Timer(100, new trainerListener());
		trainerAnimationTimer.start();
	}
	
	private class trainerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(trainertic<5){
		    	if(trainertic == 0){
		    		currentTrainer = Trainerskill.getSubimage(0, 0, 192, 192);
		    		currentTrainer = currentTrainer.getScaledInstance(96, 96, 0);
		    	}
		    	if(trainertic == 1){
		    		currentTrainer = Trainerskill.getSubimage(192, 0, 192, 192);
		    		currentTrainer = currentTrainer.getScaledInstance(96, 96, 0);
		    	}
		    	if(trainertic == 2){
		    		currentTrainer = Trainerskill.getSubimage(384, 0, 192, 192);
		    		currentTrainer = currentTrainer.getScaledInstance(96, 96, 0);
		    	}
		    	if(trainertic== 3){
		    		currentTrainer = Trainerskill.getSubimage(576, 0, 192, 192);
		    		currentTrainer = currentTrainer.getScaledInstance(96, 96, 0);
		    	}
		    	if(trainertic== 4){
		    		currentTrainer = Trainerskill.getSubimage(768, 0, 192, 192);
		    		currentTrainer = currentTrainer.getScaledInstance(96, 96, 0);
		    	}
		    	repaint();
		    	trainertic = trainertic +1;
		    }
			else {
				trainertic =0;
				currentTrainer  = Trainerskill.getSubimage(0, 0, 192, 192);
				currentTrainer = currentTrainer.getScaledInstance(96, 96, 0);
		    	repaint();
		    	trainerAnimationTimer.stop();
			}
		}
	}
	Timer changeAnimationTimer;
	private int changetic = 0;
	public void pokemonChangeAnimation(){
		changeAnimationTimer = new Timer(150, new changeListener());
		changeAnimationTimer.start();

	}
	private class changeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(changetic<5){
		    	if(changetic == 0){
		    		changeEffect = changeSkill.getSubimage(0, 0, 192, 192);
		    		changeEffect = changeEffect.getScaledInstance(96, 96, 0);
		    	}
		    	if(changetic == 1){
		    		changeEffect = changeSkill.getSubimage(192, 0, 192, 192);
		    		changeEffect = changeEffect.getScaledInstance(96, 96, 0);
		    	}
		    	if(changetic == 2){
		    		changeEffect = changeSkill.getSubimage(384, 0, 192, 192);
		    		changeEffect = changeEffect.getScaledInstance(96, 96, 0);
		    	}
		    	if(changetic== 3){
		    		changeEffect = changeSkill.getSubimage(576, 0, 192, 192);
		    		changeEffect = changeEffect.getScaledInstance(96, 96, 0);
		    	}
		    	if(changetic== 4){
		    		changeEffect = changeSkill.getSubimage(768, 0, 192, 192);
		    		changeEffect = changeEffect.getScaledInstance(96, 96, 0);
		    	}
		    	repaint();
		    	changetic = changetic +1; 		
		    }
			else {
				if((b.currentPokemon().getName().equals("Bulbasaur"))){
			    	currentPokemonIcons = Bulbasaur;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Charmander")){
			    	currentPokemonIcons = Charmander;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Butterfree")){
			    	currentPokemonIcons = ButterFree;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Charizard")){
			    	currentPokemonIcons = Charizard;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Jigglypuff")){
			    	currentPokemonIcons = Jigglypuff;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("ManKey")){
			    	currentPokemonIcons = ManKey;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Mew")){
			    	currentPokemonIcons = Mew;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Ninetales")){
			    	currentPokemonIcons = NineTales;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Pikachu")){
			    	currentPokemonIcons = Pikachu;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Squirtle")){
			    	currentPokemonIcons = Squirtle;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
			    else if(b.currentPokemon().getName().equals("Vulpix")){
			    	currentPokemonIcons = Vulpix;
			    	currentPokemonIcon = currentPokemonIcons.getSubimage(0, 96, 48, 48);
			    	repaint();
			    }
				changetic =0;
		    	changeEffect = null;
		    	repaint();
		    	changeAnimationTimer.stop();
		        playersPokemonStats.setText(b.currentPokemon().getName()+"   HP:   "+ b.currentPokemon().getHP()+"   MP:   " + b.currentPokemon().getMP());
			}
		}
	}
	Timer mprAnimationTimer;
	private int mprtic = 0;
	public void mprAnimation(){
		mprAnimationTimer = new Timer(100, new mprListener());
		mprAnimationTimer.start();
	
	}
	private class mprListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		    if(mprtic<5){
		    	if(healtic == 0){
		    		MPEffect = MPSkill.getSubimage(0, 0, 192, 192);
		    		MPEffect = MPEffect.getScaledInstance(96, 96, 0);
		    	}
		    	if(mprtic == 1){
		    		MPEffect = MPSkill.getSubimage(192, 0, 192, 192);
		    		MPEffect = MPEffect.getScaledInstance(96, 96, 0);
		    	}
		    	if(mprtic == 2){
		    		MPEffect = MPSkill.getSubimage(384, 0, 192, 192);
		    		MPEffect = MPEffect.getScaledInstance(96, 96, 0);
		    	}
		    	if(mprtic== 3){
		    		MPEffect = MPSkill.getSubimage(576, 0, 192, 192);
		    		MPEffect = MPEffect.getScaledInstance(96, 96, 0);
		    	}
		    	if(mprtic== 4){
		    		MPEffect = MPSkill.getSubimage(768, 0, 192, 192);
		    		MPEffect = MPEffect.getScaledInstance(96, 96, 0);
		    	}
		    	repaint();
		    	mprtic = mprtic +1; 		
		    }
		    else{
		    	mprtic =0;
		    	MPEffect = null;
		    	repaint();
		    	mprAnimationTimer.stop();
		    }
		    if(b.currentPokemon().getMP()> b.currentPokemon().getMaxMP()){
		    	b.currentPokemon().changeMP(b.currentPokemon().getMP()- b.currentPokemon().getMaxMP());
		    	System.out.println(b.currentPokemon().getMP());
		    }
//		    System.out.println("Now" + b.enemyPokemon.getMP());
		    playersPokemonStats.setText(b.currentPokemon().getName()+"   HP:   "+ b.currentPokemon().getHP()+"   MP:   " + b.currentPokemon().getMP());
		    enenmyPokemonStats.setText(b.enemyPokemon.getName()+"   HP:   "+ b.enemyPokemon.getHP()+"   MP:   " + b.enemyPokemon.getMP());
		}
		
	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    g.drawImage(battleBackground, 0, 0, null);    
	    g.drawImage(playerIcon, 0, 0, null);
	    g.drawImage(enemyTrainerIcon, 540, 0, null);
	    g.drawImage(currentEnemyIcon, enemyPokemonX, enemyPokemonY, null);
	    g.drawImage(currentPokemonIcon, playersPokemonX, playersPokemonY, null);
	    g.drawImage(fireEffect,enemyPokemonX,enemyPokemonY, null);
	    g.drawImage(thunderEffect,playersPokemonX-10,playersPokemonY-24, null);
	    g.drawImage(HPEffect, playersPokemonX, playersPokemonY, null);
	    g.drawImage(catchIcon, catchBallX, catchBallY, null);
	    g.drawImage(changeEffect,playersPokemonX-24,playersPokemonY-34, null);
	    g.drawImage(currentTrainer, trainerX, trainerY, null);
	    g.drawImage(MPEffect, playersPokemonX-24, playersPokemonY-50, null);
	
	}    
}
