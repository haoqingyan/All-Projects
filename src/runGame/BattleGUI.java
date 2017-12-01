package runGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import PokemonInit.Bulbasaur;
import PokemonInit.Butterfree;
import PokemonInit.Jigglypuff;
import PokemonInit.ManKey;
import PokemonInit.Mew;
import PokemonInit.Ninetales;
import PokemonInit.Pokemon;
import Trainer.Trainer;
import Items.Recover;
public class BattleGUI extends JFrame implements Observer{

	private Battle newbattle;
	ItemGUI a;

	private monsterStatsObserver monsterStatsPanel;
	private battleViewObserver battleViewPanel;
	private JPanel battleControlPanel;
	
//	  public static void main(String[] args) {
//		  	Trainer a = new Trainer();
//		  	Pokemon c = new Ninetales();
//		  	Battle d = new Battle(a,c);
//		    BattleGUI window = new BattleGUI(d);
//		    window.setVisible(true);
//		  }
	
	public BattleGUI (Battle b){
		
		newbattle = b;
		a = new ItemGUI(newbattle);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(660, 600);
		setLocation(600, 300);
	    setTitle("battle");
	   
	    battleViewPanel = new battleViewObserver(newbattle);
	    battleControlPanel = new battleControlPanel();
	    newbattle.addObserver(battleViewPanel);
	    newbattle.addObserver(this);

	    add(battleControlPanel,BorderLayout.SOUTH);
	    add(battleViewPanel, BorderLayout.CENTER);
	    setVisible(true);

	}
	

	private class battleControlPanel extends JPanel{
		private JButton attackB = new JButton("Attack");
		private JButton escapeB = new JButton("Escape");
		private JButton itemB   = new JButton("Item");
		private JButton PokemonChooseB = new JButton("Pokemons");
		
		public battleControlPanel(){
			attackB.addActionListener(new attackListener());
			escapeB.addActionListener(new escapeListener());
			itemB.addActionListener(new itemListener());
			PokemonChooseB.addActionListener(new PokemonChooseListener());
			add(attackB);
			add(escapeB);
			add(itemB);
			add(PokemonChooseB);
			
		}
			
	}
	private class attackListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(newbattle.enemyPokemon.getHP()<=0){
				newbattle.command(0);
			}
			else{
				newbattle.command(1);
			}
		}
	}
	
	private class escapeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {	
			newbattle.end = true;
			endGui(newbattle.end);
		}
		
	}
	
	private class PokemonChooseListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			PokemonChooseGUI p= new PokemonChooseGUI(newbattle);
			
		}
		
	}
	private class itemListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			a.setVisible(true);
				
		}
		
	}
	public void endGui(boolean b){
		if (b == true){
			setVisible(false);
		}
	}


	public void update(Observable arg0, Object arg1) {
		newbattle = (Battle)arg0;
		endGui(newbattle.end);
		a.setVisible(false);
		
	}

	  
}
