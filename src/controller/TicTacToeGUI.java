package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.RandomAI;
import model.StopperAI;
import model.TicTacToeGame;
import view.ButtonView;

/**
 * Play TicTacToe the computer that can have different AIs to beat you.  Select the Options menus to begin
 * a new game, switch strategies for the computer player (BOT or AI), and to switch between the two views
 * 
 * This class represents an event-driven program with a graphical user interface as a controller
 * between the view and the model. It has listeners to mediate between the view and the model.
 * 
 * This controller employs the Observer design pattern that updates two views every time the
 * state of the tic tac toe game changes:
 * 
 *    1) whenever you make a move by clicking a button or an area of either view
 *    2) whenever the computer AI makes a move
 *    3) whenever there is a win or a tie
 *    
 * You can also select two different strategies to play against from the menus
 * 
 * @author mercer
 */
public class TicTacToeGUI extends JFrame {

  public static void main(String[] args) {
    TicTacToeGUI g = new TicTacToeGUI();
    g.setVisible(true);
  }

  private TicTacToeGame theGame;
  private ButtonView buttonView;
  private JPanel currentView;
  public static final int width = 300;
  public static final int height = 360;

  public TicTacToeGUI() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(width, height);
    this.setLocation(100, 40);
    this.setTitle("Tic Tac Toe");

    setupMenus();
    initializeGameForTheFirstTime();
    buttonView = new ButtonView(theGame, width, height);
    addObservers();
    // Set default view
    setViewTo(buttonView);
  }

  private void addObservers() {
    theGame.addObserver(buttonView);
  }

  public void initializeGameForTheFirstTime() {
    theGame = new TicTacToeGame();
    // This event driven program will always have
    // a computer player who takes the second turn
    theGame.setComputerPlayerStrategy(new RandomAI());
  }

  private void setupMenus() {
    JMenuItem menu = new JMenu("Options");
    JMenuItem newGame = new JMenuItem("New Game");
    menu.add(newGame);
    // Add two Composites to a Composite
    JMenuItem jmi2Nest = new JMenu("Stategies");
    menu.add(jmi2Nest);
    JMenuItem beginner = new JMenuItem("RandomAI");
    jmi2Nest.add(beginner);
    JMenuItem intermediate = new JMenuItem("Stopper");
    jmi2Nest.add(intermediate);

    // Set the menu bar
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    menuBar.add(menu);

    // Add the same listener to all menu items requiring action
    MenuItemListener menuListener = new MenuItemListener();
    newGame.addActionListener(menuListener);
    beginner.addActionListener(menuListener);
    intermediate.addActionListener(menuListener);
  }

  private void setViewTo(JPanel newView) {
    if (currentView != null)
      remove(currentView);
    currentView = newView;
    add(currentView);
    currentView.repaint();
    validate();
  }

  private class MenuItemListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      // Find out the text of the JMenuItem that was just clicked
      String text = ((JMenuItem) e.getSource()).getText();

      if (text.equals("Button"))
        setViewTo(buttonView);

      if (text.equals("New Game")) {
        theGame.startNewGame(); // The computer player has been set and should not change.
      }

      if (text.equals("Stopper")) {
        theGame.setComputerPlayerStrategy(new StopperAI());
      }

      if (text.equals("Random")) {
        theGame.setComputerPlayerStrategy(new RandomAI());
      }
    }
  }
}