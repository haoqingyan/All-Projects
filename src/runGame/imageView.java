package runGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.java.swing.plaf.windows.resources.windows;

import Items.Recover;
import Map.Map;
import Map.Square;
import PokemonInit.Bulbasaur;
import PokemonInit.Butterfree;
import PokemonInit.Charizard;
import PokemonInit.Charmander;
import PokemonInit.Jigglypuff;
import PokemonInit.ManKey;
import PokemonInit.Mew;
import PokemonInit.Ninetales;
import PokemonInit.Pikachu;
import PokemonInit.Pokemon;
import PokemonInit.Squirtle;
import PokemonInit.Vulpix;
import Trainer.Trainer;

public class imageView extends JPanel implements Observer{
	
	private Game game;
	private Map mainMap;
	private int X, Y;
	private Direction direction;
	private BufferedImage sourcePicture, trainerSourcePicture;
	private final static int SOURPICTURE_UNIT = 32;
	private Image ground,tree_01, tree_02, trainer, grass, martHouse_01,martHouse_02,martHouse_03,martHouse_04,martHouse_05,
				  martHouse_06,martHouse_07,martHouse_08,martHouse_09,martHouse_10,martHouse_11,martHouse_12,martHouse_13,
				  martHouse_14,martHouse_15,martHouse_16, stone, fence, flower,HPpotion;

	
	
	
	public imageView(Game game, Map map){
		this.game = game;
		mainMap = map;
		try {
			sourcePicture = ImageIO.read(new File("./images/outside.png"));
			trainerSourcePicture = ImageIO.read(new File("./images/mainCharacter.png"));
			HPpotion = ImageIO.read(new File("./images/HPpotionIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ground = sourcePicture.getSubimage(0, 0, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		tree_01 = sourcePicture.getSubimage(4*SOURPICTURE_UNIT, 21*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		tree_02 = sourcePicture.getSubimage(4*SOURPICTURE_UNIT, 22*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		grass = sourcePicture.getSubimage(0, SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		stone = sourcePicture.getSubimage(6*SOURPICTURE_UNIT, 20*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		fence = sourcePicture.getSubimage(4*SOURPICTURE_UNIT, 20*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		flower = sourcePicture.getSubimage(0, 36*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		
		
		martHouse_01 = sourcePicture.getSubimage(0, 41*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_02 = sourcePicture.getSubimage(1*SOURPICTURE_UNIT, 41*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_03 = sourcePicture.getSubimage(2*SOURPICTURE_UNIT, 41*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_04 = sourcePicture.getSubimage(3*SOURPICTURE_UNIT, 41*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_05 = sourcePicture.getSubimage(0, 42*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_06 = sourcePicture.getSubimage(1*SOURPICTURE_UNIT, 42*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_07 = sourcePicture.getSubimage(2*SOURPICTURE_UNIT, 42*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_08 = sourcePicture.getSubimage(3*SOURPICTURE_UNIT, 42*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_09 = sourcePicture.getSubimage(0, 43*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_10 = sourcePicture.getSubimage(1*SOURPICTURE_UNIT, 43*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_11 = sourcePicture.getSubimage(2*SOURPICTURE_UNIT, 43*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_12 = sourcePicture.getSubimage(3*SOURPICTURE_UNIT, 43*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_13 = sourcePicture.getSubimage(0, 44*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_14 = sourcePicture.getSubimage(1*SOURPICTURE_UNIT, 44*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_15 = sourcePicture.getSubimage(2*SOURPICTURE_UNIT, 44*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		martHouse_16 = sourcePicture.getSubimage(3*SOURPICTURE_UNIT, 44*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);

		trainer = trainerSourcePicture.getSubimage(0, 0, SOURPICTURE_UNIT, 50);
		trainer.getScaledInstance(32, 32, 0);
		repaint();
	}

	public void update(Observable arg0, Object arg1) {
		game = (Game) arg0;
		direction = (Direction) arg1;
		X = game.currentCol;
		Y = game.currentRow;
		if(mainMap.map[X][Y].StepIn() == false){
			X = game.oldCol;
			Y = game.oldRow;
			game.currentCol = game.oldCol;
			game.currentRow = game.oldRow;
		}
		
		if(mainMap.map[X][Y].squareType.equals("grass")){
			double PokemonMeetPossibility = Math.random();
			Pokemon PokemonMeet = meetWhatPokemon(PokemonMeetPossibility);
			game.grassStepAmount++;
			if(game.grassStepAmount == 5){
				Battle meetEnemy = new Battle(game.trainer, PokemonMeet);
				BattleGUI startBattle = new BattleGUI(meetEnemy);
				startBattle.setVisible(true);
				game.grassStepAmount = 0;
			}
		}
		if(mainMap.map[X][Y].squareType.equals("martHouse_15")){
			this.setVisible(false);
			new startBlueHouse(game.trainer);
			
		}
		if(mainMap.map[X][Y].squareType.equals("HPpotion")){
			mainMap.map[X][Y] =  mainMap.grassSquare;
			game.trainer.getItems(1).addNums(1);
			JOptionPane.showMessageDialog(null, "HaHa! you get HPpotion");
		}
		if(game.stepAmount == 200){
			System.out.println(game.trainer.getPokNum());
			new gameOverGUI(game.trainer);	
		}
		if(game.trainer.getItems(0).getNums()==0){
			new gameOverGUI(game.trainer);
		}
		if(game.trainer.getPokNum() == 5){
			new gameOverGUI(game.trainer);
		}
		
		setCharacterDirectionPicture(direction);
		
		repaint();
	}
	
	public Pokemon meetWhatPokemon(double PokemonMeetPossibility){
		double pokemonRandom = Math.random();
		int normalRandom = (int) (pokemonRandom*5);
		int rareRandom = (int)(pokemonRandom*4); 
		Pokemon[] normalPokemons ={new Bulbasaur(), new Charizard(), new Pikachu(), new Squirtle(), new Vulpix()};
		Pokemon[] rarePokemons ={new Ninetales(), new Jigglypuff(), new Charmander(), new Butterfree()};
		int possiblity = (int)PokemonMeetPossibility*100;
		if(possiblity < 70){
			return normalPokemons[normalRandom];
			
		}
		else if(possiblity >= 70 && possiblity <95){
			return rarePokemons[rareRandom];
		}
		else if(possiblity >= 95 && possiblity <= 100){
			return new Mew();
		}
		else{
			return new Bulbasaur();
		}
	}
	
	private void setCharacterDirectionPicture(Direction direction) {
		if(direction == Direction.NORTH){
			trainer = trainerSourcePicture.getSubimage(0, 150, SOURPICTURE_UNIT, 50);
			trainer.getScaledInstance(32, 32, 0);
		}
		if(direction == Direction.SOUTH){
			trainer = trainerSourcePicture.getSubimage(0, 0, SOURPICTURE_UNIT, 50);
			trainer.getScaledInstance(32, 32, 0);
		}
		if(direction == Direction.EAST){
			trainer = trainerSourcePicture.getSubimage(0, 100, SOURPICTURE_UNIT, 50);
			trainer.getScaledInstance(32, 32, 0);
		}
		if(direction == Direction.WEST){
			trainer = trainerSourcePicture.getSubimage(0, 50, SOURPICTURE_UNIT, 50);
			trainer.getScaledInstance(32, 32, 0);
		}
		
	}

	
	public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;

//		    
//		    for (int r = 0; r < 32*Map.MAP_HEIGHT; r += 32){
//		      for (int c = 0; c < 32*Map.MAP_WIDTH; c += 32){
//		    	if(mainMap.map[r/32][c/32].squareType.equals("tree_01")){
//		    		g2.drawImage(tree_01, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("ground")){
//		    		g2.drawImage(ground, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("tree_02")){
//		    		g2.drawImage(tree_02, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("grass")){
//		    		g2.drawImage(grass, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_01")){
//		    		g2.drawImage(martHouse_01, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_02")){
//		    		g2.drawImage(martHouse_02, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_03")){
//		    		g2.drawImage(martHouse_03, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_04")){
//		    		g2.drawImage(martHouse_04, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_05")){
//		    		g2.drawImage(martHouse_05, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_06")){
//		    		g2.drawImage(martHouse_06, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_07")){
//		    		g2.drawImage(martHouse_07, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_08")){
//		    		g2.drawImage(martHouse_08,r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_09")){
//		    		g2.drawImage(martHouse_09, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_10")){
//		    		g2.drawImage(martHouse_10, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_11")){
//		    		g2.drawImage(martHouse_11, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_12")){
//		    		g2.drawImage(martHouse_12, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_13")){
//		    		g2.drawImage(martHouse_13, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_14")){
//		    		g2.drawImage(martHouse_14, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_15")){
//		    		g2.drawImage(martHouse_15, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("martHouse_16")){
//		    		g2.drawImage(martHouse_16, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("stone")){
//		    		g2.drawImage(stone, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("fence")){
//		    		g2.drawImage(fence, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("flower")){
//		    		g2.drawImage(flower, r, c+32, null);
//		    	}
//		    	else if(mainMap.map[r/32][c/32].squareType.equals("HPpotion")){
//		    		g2.drawImage(HPpotion, r, c+32, null);
//		    	}
//		      }
//		    }
//		    
//		    g2.drawImage(trainer, X*SOURPICTURE_UNIT, Y*SOURPICTURE_UNIT, null);
		   
		    
		    Point[] a = {new Point(X-1,Y-1), new Point(X,Y-1), new Point(X+1,Y-1), new Point(X-1,Y), new Point(X,Y), new Point(X+1,Y) , new Point(X-1,Y+1), new Point(X,Y+1), new Point(X+1,Y+1)};
		    for(int i = 0; i<9; i++){
	    		if(mainMap.map[a[i].x][a[i].y].squareType.equals("stone")){
	    			g2.drawImage(stone, a[i].x*32, a[i].y*32+32, null);
	    		}
	    		else if(mainMap.map[a[i].x][a[i].y].squareType.equals("fence")){
	    			g2.drawImage(fence, a[i].x*32, a[i].y*32+32, null);
	    		}
	    		else if(mainMap.map[a[i].x][a[i].y].squareType.equals("HPpotion")){
	    			g2.drawImage(HPpotion, a[i].x*32, a[i].y*32+32, null);
	    		}
	    		else if(mainMap.map[a[i].x][a[i].y].squareType.equals("flower")){
	    			g2.drawImage(flower, a[i].x*32, a[i].y*32+32, null);
	    		}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("tree_01")){
		    		g2.drawImage(tree_01, a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("ground")){
		    		g2.drawImage(ground,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("tree_02")){
		    		g2.drawImage(tree_02,  a[i].x*32, a[i].y*32+32,null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("grass")){
		    		g2.drawImage(grass,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_01")){
		    		g2.drawImage(martHouse_01,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_02")){
		    		g2.drawImage(martHouse_02,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_03")){
		    		g2.drawImage(martHouse_03,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_04")){
		    		g2.drawImage(martHouse_04,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_05")){
		    		g2.drawImage(martHouse_05,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_06")){
		    		g2.drawImage(martHouse_06,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_07")){
		    		g2.drawImage(martHouse_07, a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_08")){
		    		g2.drawImage(martHouse_08, a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_09")){
		    		g2.drawImage(martHouse_09,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_10")){
		    		g2.drawImage(martHouse_10,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_11")){
		    		g2.drawImage(martHouse_11,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_12")){
		    		g2.drawImage(martHouse_12, a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_13")){
		    		g2.drawImage(martHouse_13, a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_14")){
		    		g2.drawImage(martHouse_14,  a[i].x*32, a[i].y*32+32,null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_15")){
		    		g2.drawImage(martHouse_15,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    	else if(mainMap.map[a[i].x][a[i].y].squareType.equals("martHouse_16")){
		    		g2.drawImage(martHouse_16,  a[i].x*32, a[i].y*32+32, null);
		    	}
		    }
		    g2.drawImage(trainer, X*SOURPICTURE_UNIT, Y*SOURPICTURE_UNIT, null);
	}
	
	private class Point{
		private int x;
		private int y;
		public Point(int x,int y){
			this.x = x;
			this.y = y;
		}
		
	}
}
