package runGame;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Map.Map;

public class textView extends JPanel implements Observer,java.io.Serializable{

	private Map pokemonMap;
	private int x;
	private int y;
	private Game game;
	private JTextArea textMap;
	
	
	
	public textView(Map map){
		pokemonMap = map;
		textMap = new JTextArea();
		add(textMap);

		
	}

	public void update(Observable arg0, Object arg1) {
		game = (Game) arg0;
		x = game.currentRow;
		y = game.currentCol;
		
		String s ="";
		if(game.stepAmount >= 10){
			s = "Congratulation! You Win!";
		}
		else{
		String[][] originalType = new String[10][10];
		
		for(int i = 0; i < Map.MAP_WIDTH; i++){
			for(int j = 0; j < Map.MAP_HEIGHT; j++){
				originalType[i][j] = pokemonMap.map[i][j].toString();				
			}
		}
		
		
		if (pokemonMap.map[x][y].StepIn()){
			originalType[x][y] = "P";
		}
		else{
			game.currentRow = game.oldRow;
			game.currentCol = game.oldCol;
			originalType[game.currentRow][game.currentCol] = "P";
			
		}
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++ ){
				s = s + originalType[i][j];
				if(j == 9){
					s = s+"\n";
				}
			}
		}
		}
		textMap.setText(s);
		
		//originalType[x][y] = pokemonMap.map[x][y].toString();
		
		
	}
	

}
