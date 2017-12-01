// GUI components and interfaces you will need

//Programmers: Yichao Tang & Haoqing Yan
//1) Yichao Tang, 
//2) Haoqing Yan.
//
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * An event driven graphical user interface for the Boggle game using the design
 * of BoggleTray and Boggle classes during Rick Mercer's CSc classes at the UofA
 */
@SuppressWarnings("serial")
public class BoggleGUI extends JFrame 
{

 public static void main(String[] args) 
 {
   // This main method allows us to can run this class as a Java Application
   BoggleGUI theView = new BoggleGUI();
   theView.setVisible(true);
 }

 // Need the well test model in several methods of this GUI
 private Boggle game;
 private BoggleTray NewGametray;
 // GUI components you will need
 private JTextArea diceTrayArea;
 private JButton newGameButton = new JButton("New Game");  // will be a warning
 private JButton endButton = new JButton("End game");      // will be a warning
 private JTextArea userInputArea = new JTextArea();  // will be a warning

 public BoggleGUI() 
 {
   
   setUpModel();     // given below
   layoutWindow();   // started below
   setupListeners(); // will be a compile time error until you add the helper method
   startNewGame();   // will be a compile time error until you add the helper method
 }
 private void setupListeners ()
 {
	 NewGamelistener newGamelister = new NewGamelistener();
	 newGameButton.addActionListener(newGamelister);
	 
	 EndGamelistener endGamelistener = new EndGamelistener();
	 endButton.addActionListener(endGamelistener);

 }

private class NewGamelistener implements ActionListener 
{
	public void actionPerformed(ActionEvent anActionEvent) 
	{
		NewGametray = new BoggleTray();
	    game.setBoggleTray(NewGametray);
	    diceTrayArea.setText(game.getBoggleTrayAsString().toUpperCase());
	    userInputArea.setText("");
	  }
}

private class EndGamelistener implements ActionListener 
{
	public void actionPerformed(ActionEvent arg0) 
	{
		//input
		Scanner inputstring = new Scanner(userInputArea.getText());
		while (inputstring.hasNext()) 
		{
			String temp= inputstring.next();
			game.addGuess(temp);
			
		}
		
		//get result
		    JOptionPane result= new JOptionPane();
		    String resultString="";
		
		    BoggleGUI outputor = new BoggleGUI();
		    resultString+="Your score: "+game.getScore()+"\n\n";
		    resultString+="Words you found: \n\n";
		    resultString+=outputor.formatedprinting(game.getWordsFound());
		    resultString+="\n\n";
		    resultString+="Incorrect words: \n\n";
		    resultString+=outputor.formatedprinting(game.getWordsIncorrect());
		    resultString+="\n\n";
		    resultString+="You could have found "+(game.getWordsNotGuessed().size()) + " more words.\nThe computer found all of your words plus these you could have: \n\n";
		    resultString+=outputor.formatedprinting(game.getWordsNotGuessed());
		    resultString+="\n\n";
		    result.showMessageDialog(null,resultString); 
		    game=new Boggle();
		    game.setBoggleTray(NewGametray);
		    userInputArea.setText("");
	}
}

private String formatedprinting(java.util.List<String> temp)
{
	String str="";
	for (int i = 1; i <= temp.size(); i++) 
	{
		if(i%10==0)
		{
			str+=temp.get(i-1)+"\n";
		}
		else 
		{
			str+=temp.get(i-1)+" ";
		}
	}
	return str;
}

 private void startNewGame()
 {
	NewGametray = new BoggleTray();
	game = new Boggle();
	game.setBoggleTray(NewGametray);
	diceTrayArea.setText(game.getBoggleTrayAsString().toUpperCase());
	
 }
 
 private void setUpModel() {
   game = new Boggle();
 }

 // Add five GUI components to this JFrame
 private void layoutWindow() {
   // Set up the JFrame
   this.setSize(500, 270);
   this.setResizable(false);
   setLocation(10, 10);
   setTitle("Boggle");
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   // With null layout, you must set the size and location of every component.
   setLayout(null);

   // Layout the dice tray area as a JTextArea
   diceTrayArea = new JTextArea();
   diceTrayArea.setEditable(false);
   diceTrayArea.setBackground(Color.cyan);
   diceTrayArea.setFont(new Font("Courier", Font.BOLD, 25));

   // Every GUI component must receive a setSize and setLocation 
   // message and then be added to the this JFrame 
   diceTrayArea.setSize(210, 220);
   diceTrayArea.setLocation(10, 10);
   add(diceTrayArea);

   
  
   // Declare and setSize and setLocation of a JLabel for "Enter your words below"
   JLabel enterwordsJLabel = new JLabel("Enter your words below");
   enterwordsJLabel.setSize(200,20);
   enterwordsJLabel.setLocation(240,10);
   add(enterwordsJLabel);
   
   // setSize and setLocation of the user input area. Also set line wrap true
   userInputArea = new JTextArea();
   userInputArea.setEditable(true);
   userInputArea.setSize(200,150);
   userInputArea.setLocation(240, 40);;
   add(userInputArea);
   // setSize and setLocation of the newGameButton
    newGameButton= new JButton();
    newGameButton.setSize(100,20);
    newGameButton.setText("New Game");
    newGameButton.setLocation(240,200);
   add(newGameButton);
  
   // setSize and setLocation of the endButton
   endButton= new JButton();
   endButton.setSize(100,20);
   endButton.setText("End Game");
   endButton.setLocation(350,200);
   add(endButton);
   // If you haven't already done so, add all graphical components to this JFrame
 }
}
