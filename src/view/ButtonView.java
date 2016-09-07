package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.OurObserver;
import model.ComputerPlayer;
import model.TicTacToeGame;

public class ButtonView extends JPanel implements OurObserver {

  private TicTacToeGame theGame;
  private JButton stateButton = new JButton("Click your move");
  private JButton[][] buttons = null;
  private ComputerPlayer computerPlayer;
  private int height, width;

  public ButtonView(TicTacToeGame TicTacToeGame, int width, int height) {
    theGame = TicTacToeGame;
    this.height = height;
    this.width = width;
    computerPlayer = theGame.getComputerPlayer();
    initializeButtonPanel();
  }

  // This method is called by OurObservable's notifyObservers()
  public void update() {
    if (theGame.maxMovesRemaining() == theGame.size() * theGame.size())
      resetButtons(true);

    if (!theGame.stillRunning())
      resetButtons(false);
    else {
      updateButtons();
      stateButton.setText("Click your move");
    }
  }

  private void initializeButtonPanel() {
    JPanel buttonPanel = new JPanel();
    int size = theGame.size();
    buttonPanel.setLayout(new GridLayout(size, size, 5, 5));
    Font myFont = new Font("Arial", Font.TRUETYPE_FONT, 40);
    ButtonListener buttonListener = new ButtonListener();
    buttons = new JButton[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        buttons[i][j] = new JButton();
        //        buttons[i][j].setSize(width / 3, height width / 3);
        buttons[i][j].setFont(myFont);
        buttons[i][j].addActionListener(buttonListener);
        buttonPanel.add(buttons[i][j]);
      }
    }
    this.setLayout(null);
    buttonPanel.setLocation(10, 5);
    buttonPanel.setSize(width - 30, height - 110);
    this.add(buttonPanel);
    stateButton.setSize(200, 40);
    stateButton.setFont(new Font("Arial", Font.BOLD, 18));
    stateButton.setEnabled(false);
    stateButton.setBackground(Color.PINK);
    stateButton.setLocation(40, height - 100);
    this.add(stateButton);
  }

  // Mark each selected square with an X or an O and prevent
  // selection of the selected squares with seDisabled(true)
  public void updateButtons() {
    char[][] temp = theGame.getTicTacToeBoard();
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[i].length; j++) {
        String text = "" + temp[i][j];
        if (text.equals("X") || text.equals("O")) {
          buttons[i][j].setText(text);
          buttons[i][j].setEnabled(false);
        }
      }
    }
  }

  private void resetButtons(boolean enable) {
    for (int i = 0; i < theGame.size(); i++) {
      for (int j = 0; j < theGame.size(); j++) {
        buttons[i][j].setText("");
        buttons[i][j].setEnabled(enable);
      }
    }
  }

  private class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      JButton buttonClicked = (JButton) arg0.getSource();
      for (int i = 0; i < buttons.length; i++) {
        for (int j = 0; j < buttons[i].length; j++) {
          if (buttons[i][j] == buttonClicked && buttons[i][j].isEnabled())
            theGame.choose(i, j);
        }
      }

      if (theGame.tied()) {
        stateButton.setText("Tied");
        updateButtons();
      }
      else if (theGame.didWin('X')) {
        stateButton.setText("X wins");
        updateButtons();
      }
      else {
        // If the game is not over, let the computer player choose
        // This algorithm assumes the computer player always
        // goes after the human player and is represented by 'O', not 'X'
        Point play = computerPlayer.desiredMove(theGame);
        theGame.choose(play.x, play.y);
        if (theGame.didWin('O')) {
          stateButton.setText("O wins");
          updateButtons();
        }
      }
    }

//    private void setButtonsDisabled() {
//      for (int i = 0; i < buttons.length; i++)
//        for (int j = 0; j < buttons[i].length; j++)
//          buttons[i][j].setEnabled(false);
//    }
  }
}