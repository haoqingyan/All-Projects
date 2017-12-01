package runGame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Map.blueHouseMap;
import PokemonInit.Mew;
public class blueHouseImageView extends JPanel implements Observer{
	private Game game;
	private blueHouseMap map;
	private int X, Y;
	private Direction direction;
	private BufferedImage trainerSourcePicture,blueHouse, insideSourcePicture, Boss;
	private final static int SOURPICTURE_UNIT = 32;
	private Image blueHouseGround, trainer, carpet, finalBoss;
	
	public blueHouseImageView(Game game, blueHouseMap map){
		this.game = game;
		this.map = map;
		X = game.currentCol;
		Y = game.currentRow;
		try {
			
			trainerSourcePicture = ImageIO.read(new File("./images/mainCharacter.png"));
			insideSourcePicture = ImageIO.read(new File("./images/inside.png"));
			blueHouse = ImageIO.read(new File("./images/BlueHouseGround.png"));
			Boss = ImageIO.read(new File("./images/Mew.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		carpet = insideSourcePicture.getSubimage(7*SOURPICTURE_UNIT, 6*SOURPICTURE_UNIT, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		blueHouseGround = blueHouse.getSubimage(0, 0, SOURPICTURE_UNIT, SOURPICTURE_UNIT);
		trainer = trainerSourcePicture.getSubimage(0, 150, SOURPICTURE_UNIT, 50);
		trainer.getScaledInstance(32, 32, 0);
		finalBoss = Boss.getSubimage(0, 0, 48, 48);
		finalBoss = finalBoss.getScaledInstance(32, 32, 0);
		repaint();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		game = (Game) arg0;
		direction = (Direction) arg1;
		X = game.currentCol;
		Y = game.currentRow;
		if(map.map[X][Y].StepIn() == false){
			X = game.oldCol;
			Y = game.oldRow;
			game.currentCol = game.oldCol;
			game.currentRow = game.oldRow;
		}
		if(map.map[X][Y].squareType.equals("finalBoss")){
			Battle a = new Battle(game.trainer, new Mew());
			BattleGUI b = new BattleGUI(a);
		}
		if(map.map[X][Y].squareType.equals("outHouse")){
			startGame backMainMap = new startGame(game.trainer, 16, 14);
			backMainMap.setVisible(true);
			this.setVisible(false);
		}
		setCharacterDirectionPicture(direction);
		repaint();
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
	    
	    for (int r = 0; r < 32*blueHouseMap.blueHouseMAP_HEIGHT; r += 32){
		      for (int c = 0; c < 32*blueHouseMap.blueHouseMAP_WIDTH; c += 32){
		    	 if(map.map[r/32][c/32].squareType.equals("blueHouseGround")){
		    		 g2.drawImage(blueHouseGround, r, c, null);
		    	 }
		    	 else if(map.map[r/32][c/32].squareType.equals("carpet")){
		    		 g2.drawImage(carpet, r, c, null);
		    	 }
		    	 else if(map.map[r/32][c/32].squareType.equals("finalBoss")){
		    		 g2.drawImage(finalBoss, r, c, null);
		    	 }
		      }
	    }
	    
	    g2.drawImage(trainer, X*SOURPICTURE_UNIT, Y*SOURPICTURE_UNIT, null);
	}
	
}
