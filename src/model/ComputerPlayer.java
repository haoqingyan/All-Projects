package model;

import java.awt.Point;

/**
 * This class allows a tic tac to player to play games
 * against a variety of AIs.  It completely relies on 
 * the TicTacToeStrategy for it's next move with the  
 * desiredMove method that can "see" the game.
 * 
 * @author mercer
 *
 */
public class ComputerPlayer {

  private TicTacToeStrategy myStrategy;

  public ComputerPlayer() {
    // This default TicTacToeStrategy can be changed with setStrategy
    myStrategy = new RandomAI();
  }

  /**
   * Change the AI for this ComputerPlayer
   * @param stategy
   */
  public void setStrategy(TicTacToeStrategy strategy) {
    myStrategy = strategy;
  }

  /**
   * Delegate to my strategy, which can "see" the game for my next move
   * 
   * @param theGame The current state of the game when asked for a move
   * 
   * @return A java.awt.Point that store two ints: an x and a y
   */
  public Point desiredMove(TicTacToeGame theGame) {
    return myStrategy.desiredMove(theGame);
  }

}