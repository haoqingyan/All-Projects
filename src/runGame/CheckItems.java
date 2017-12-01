package runGame;

import Trainer.Trainer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import PokemonInit.Mew;

public class CheckItems extends JFrame {
	
	private Trainer trainer;
	private JPanel itemsp;
	private JButton back;
	private Game game;
//	private
	
	public CheckItems(Game game){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 520);
	    setLocation(100, 30);
	    this.game = game;
	    trainer   = game.trainer;
		itemsp  = new JPanel();
		itemsp.setLayout( new GridLayout(6,2));
		itemsp.add(new JLabel("Pokeballs"));
		JTextField ballnums = new JTextField(10);
		ballnums.setText(""+ trainer.getItems(0).getNums());
		itemsp.add(ballnums);
		itemsp.add(new JLabel("Recovers"));
		JTextField rn = new JTextField(10);
		rn.setText(""+ trainer.getItems(1).getNums());
		itemsp.add(rn);
		itemsp.add(new JLabel("MPrecovers"));
		JTextField mn = new JTextField(10);
		mn.setText(""+ trainer.getItems(2).getNums());
		itemsp.add(mn);
		this.add(itemsp);
		back = new JButton("back");
		back.addActionListener(new backListener());
		this.add(back,BorderLayout.SOUTH);
		
	}
	
	  
	  private class backListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			startGame a = new startGame(game.trainer, game.currentRow, game.currentCol);
			a.setVisible(true);
		
	
		}
	  }
}
