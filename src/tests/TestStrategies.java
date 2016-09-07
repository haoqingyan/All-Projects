package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import model.ComputerPlayer;
import model.RandomAI;
import model.StopperAI;
import model.TicTacToeGame;
import model.IGotNowhereToGoException;

import org.junit.Test;

public class TestStrategies {

  @Test(expected = IGotNowhereToGoException.class)
  public void showWhatHappensWhenAnExcpetionMustBeThrown() {
    TicTacToeGame theGame = new TicTacToeGame();

    ComputerPlayer playerWithRandomStrategy = new ComputerPlayer();
    playerWithRandomStrategy.setStrategy(new RandomAI());

    // Make one move
    Point aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);

    // and another
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);
    // and another
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);
    // and another
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);
    // and another
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);
    // and another 
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);
    // and another
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);
    // and an eight 
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);
    // and the ninth
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
    theGame.choose(aRandomSquare.x, aRandomSquare.y);

    // This should throw an exception since the board is filled 
    aRandomSquare = playerWithRandomStrategy.desiredMove(theGame);
  }

  @Test
  public void run1000TicTacToeGames() {
    ComputerPlayer randomBot = new ComputerPlayer();
    randomBot.setStrategy(new RandomAI());
    ComputerPlayer stopperBot = new ComputerPlayer();
    stopperBot.setStrategy(new StopperAI());

    int randomPlayerWins = 0;
    int stopperPlayerWins = 0;
    int ties = 0;

    // 
    for (int game = 1; game <= 500; game++) {
      char winner = playOneGame(randomBot, stopperBot);
      if (winner == 'X')
        randomPlayerWins++;
      if (winner == 'O')
        stopperPlayerWins++;
      if (winner == 'T')
        ties++;
    }

    for (int game = 1; game <= 500; game++) {
      char winner = playOneGame(stopperBot, randomBot);
      if (winner == 'X')
        stopperPlayerWins++;
      if (winner == 'O')
        randomPlayerWins++;
      if (winner == 'T')
        ties++;
    }

    System.out.println("StopperAI strategy shoud have more wins than");
    System.out.println("than RandomAI strategy when they both go first");
    System.out.println("the same number of times. And ties do happen");
    System.out.println("===========================================");
    System.out.println("Stopper wins: " + stopperPlayerWins);
    System.out.println("Random wins: " + randomPlayerWins);
    System.out.println("Ties: " + ties);
  }

  private char playOneGame(ComputerPlayer first, ComputerPlayer second) {
    TicTacToeGame theGame = new TicTacToeGame();

    while (true) {
      Point firstsMove = first.desiredMove(theGame);
      assertTrue(theGame.choose(firstsMove.x, firstsMove.y));

      if (theGame.tied())
        return 'T';

      if (theGame.didWin('X'))
        return 'X';
      if (theGame.didWin('O'))
        return 'O';

      Point secondsMove = second.desiredMove(theGame);
      assertTrue(theGame.choose(secondsMove.x, secondsMove.y));

      if (theGame.tied())
        return 'T';

      if (theGame.didWin('X'))
        return 'X';
      if (theGame.didWin('O'))
        return 'O';
    }
  }

  @Test
  public void testStopper() {
    TicTacToeGame theGame = new TicTacToeGame();

    ComputerPlayer playerWithStopperStrategy = new ComputerPlayer();
    playerWithStopperStrategy.setStrategy(new StopperAI());
    // X
    theGame.choose(0, 0);
    // O
    theGame.choose(2, 0);
    // X
    theGame.choose(0, 1);

    Point computerMove = playerWithStopperStrategy.desiredMove(theGame);
    theGame.choose(computerMove.x, computerMove.y);
    assertEquals(0, computerMove.x);
    assertEquals(2, computerMove.y);
  }

  @Test
  public void testStopper2() {
    TicTacToeGame theGame = new TicTacToeGame();

    ComputerPlayer playerWithStopperStrategy = new ComputerPlayer();
    playerWithStopperStrategy.setStrategy(new StopperAI());
    // X
    theGame.choose(0, 0);
    // O
    theGame.choose(2, 0);
    // X
    theGame.choose(0, 2);

    Point computerMove = playerWithStopperStrategy.desiredMove(theGame);
    theGame.choose(computerMove.x, computerMove.y);
    assertEquals(0, computerMove.x);
    assertEquals(1, computerMove.y);
  }

  @Test
  public void testStopper3() {
    TicTacToeGame theGame = new TicTacToeGame();

    ComputerPlayer playerWithStopperStrategy = new ComputerPlayer();
    playerWithStopperStrategy.setStrategy(new StopperAI());
    // X
    theGame.choose(0, 0);
    // O
    theGame.choose(0, 2);
    // X
    theGame.choose(1, 0);

    Point computerMove = playerWithStopperStrategy.desiredMove(theGame);
    theGame.choose(computerMove.x, computerMove.y);
    assertEquals(2, computerMove.x);
    assertEquals(0, computerMove.y);
  }

  @Test
  public void testStopperTakesWin() {
    TicTacToeGame theGame = new TicTacToeGame();

    ComputerPlayer playerWithStopperStrategy = new ComputerPlayer();
    playerWithStopperStrategy.setStrategy(new StopperAI());
    // X
    theGame.choose(0, 0);
    // O
    theGame.choose(2, 0);
    // X
    theGame.choose(1, 0);
    // O
    theGame.choose(2, 1);
    // X
    theGame.choose(0, 2);

    Point computerMove = playerWithStopperStrategy.desiredMove(theGame);
    theGame.choose(computerMove.x, computerMove.y);
    System.out.println(theGame);
    assertEquals(2, computerMove.x);
    assertEquals(2, computerMove.y);
  }
}