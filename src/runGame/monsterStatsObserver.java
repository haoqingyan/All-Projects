package runGame;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class monsterStatsObserver extends JPanel implements Observer{

	private Battle b;
	public monsterStatsObserver(Battle b){
		this.b = b;
	}
	public void update(Observable arg0, Object arg1) {
		
	}
	

}
