package runGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import Map.Map;
import Trainer.Trainer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class startGame extends JFrame {
	private static MediaPlayer mp;
  public static void main(String[] args) {
    startGame window = new startGame();
    window.setVisible(true);
  }
  private imageView imagePanel;
  private Map pokemonMap;
  private Game game;
  private JPanel startMenu = new JPanel();
  private JButton startButton = new JButton("Start");
  private JButton loadButton = new JButton("Load");
  
 
  public startGame() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(320, 340);
    setLocation(700, 300);
    setTitle("Teemo");  
    addKeyListener(new ArrowKeyListener());
    
    startButton.addActionListener(new startButtonListener());
    
    startMenu.add(startButton);
    startMenu.add(loadButton);
    loadButton.addActionListener(new loadListener());
    add(startMenu);

    
    setFocusable(true);
  }
  public startGame(boolean start,int x,int y){
	  
	  if(start == true){
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1000, 1000);
	    setLocation(100, 30);
	    setTitle("Teemo"); 
	   
	    game = new Game(x, y);
	    pokemonMap = new Map(4.0);
	    imagePanel = new imageView(game,pokemonMap);
	    game.addObserver(imagePanel);
	    imagePanel.update(game, null);
	    add(imagePanel);
	    JButton saveButton = new JButton("save");
	    this.add(saveButton,BorderLayout.NORTH);
	    saveButton.addActionListener(new saveListener());
	    JButton checkStatusB = new JButton("check Items");
	    this.add(checkStatusB,BorderLayout.SOUTH);
	    checkStatusB.addActionListener(new checkStatusBListener());
	    setFocusable(true);
	    addKeyListener(new ArrowKeyListener());
	  }
  }
  public startGame(Trainer a,int x,int y){
	  
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1000, 1000);
	    setLocation(100, 30);
	    setTitle("Teemo"); 
	   
	    game = new Game(x, y);
	    game.trainer = a;
	    pokemonMap = new Map(4.0);
	    imagePanel = new imageView(game,pokemonMap);
	    game.addObserver(imagePanel);
	    imagePanel.update(game, null);
	    add(imagePanel);
	    JButton saveButton = new JButton("save");
	    this.add(saveButton,BorderLayout.NORTH);
	    saveButton.addActionListener(new saveListener());
	    JButton checkStatusB = new JButton("check Items");
	    this.add(checkStatusB,BorderLayout.SOUTH);
	    checkStatusB.addActionListener(new checkStatusBListener());
	    setFocusable(true);
	    addKeyListener(new ArrowKeyListener());
	    new JFXPanel();
	    File songFile = new File("./BGM/bgm.mp3");
	    javafx.scene.media.Media song = new Media(songFile.toURI().toString());
	    mp = new MediaPlayer(song);
	    mp.play();
	  
  }
  
  private class checkStatusBListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		CheckItems a = new CheckItems(game);
		a.setVisible(true);
		
	}
	  
  }
  private class ArrowKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent ke) {

      if (ke.getKeyCode() == KeyEvent.VK_UP)
        game.movePlayer(Direction.NORTH);

      if (ke.getKeyCode() == KeyEvent.VK_DOWN)
        game.movePlayer(Direction.SOUTH);

      if (ke.getKeyCode() == KeyEvent.VK_LEFT)
        game.movePlayer(Direction.WEST);

      if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
        game.movePlayer(Direction.EAST);
      
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
 
  }
  
  private class startButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    startGame window = new startGame(true,4,4);
	    window.setVisible(true);
	    new JFXPanel();
	    File songFile = new File("./BGM/bgm.mp3");
	    javafx.scene.media.Media song = new Media(songFile.toURI().toString());
	    mp = new MediaPlayer(song);
	    mp.play();
	   
	    
	}
	  
  }
  private class saveListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try{   
				FileOutputStream fout = new FileOutputStream("save2.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fout);   
				oos.writeObject(game);
				oos.close();
				System.out.println("Done");
				   
			   }catch(Exception ex){
				   ex.printStackTrace();
			   }

		}
		  
	  }
  
  private class loadListener implements ActionListener{
		private Game savedgame;
		public void actionPerformed(ActionEvent e) {
			 try{
				    
				   FileInputStream fin = new FileInputStream("save2.ser");
				   ObjectInputStream ois = new ObjectInputStream(fin);
				   savedgame = (Game) ois.readObject();
				   ois.close();
				   System.out.println("Done");
				   
			   }catch(Exception ex){
				   ex.printStackTrace();
			   }
			 
			 startGame window2 = new startGame(savedgame.trainer,savedgame.getCurrentRow(),savedgame.getCurrentColumn());
			 window2.setVisible(true);
		}
		  
	  }
  
  
}
