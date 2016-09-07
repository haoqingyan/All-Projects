package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.RandomAI;
import model.StopperAI;
import model.TicTacToeGame;

public class TicTacToeBoardTest {

  @Test
  public void testInitialValuesAre_() {
    TicTacToeGame b3 = new TicTacToeGame();
    // board will refer to the changing board in the TicTacToeBoard class
    char[][] board3 = b3.getTicTacToeBoard();

    for (int row = 0; row < 3; row++)
      for (int col = 0; col < 3; col++)
        assertTrue(board3[row][col] == '_');

    // System.out.println(b3.toString());
    // ___
    // ___
    // ___
    //
  }

  @Test
  public void testChoose() {
    TicTacToeGame b = new TicTacToeGame();
    // board will refer to the changing board in the TicTacToeBoard class
    char[][] board = b.getTicTacToeBoard();

    // X chooses correctly (returns true)
    assertTrue(b.choose(0, 0));
    assertEquals('X', board[0][0]);
    assertEquals('_', board[1][1]);
    assertEquals('_', board[2][2]); // could be more, but 2 will do

    // O tries the same place that X did (returns false)
    assertFalse(b.choose(0, 0));

    // Fill in a few more, notice it X and O toggles
    assertTrue(b.choose(0, 1));
    assertEquals('O', board[0][1]);

    assertTrue(b.choose(1, 1));
    assertEquals('X', board[1][1]);

    assertTrue(b.choose(2, 2));
    assertEquals('O', board[2][2]);

    // Board should now look like this if toString() is used
    // System.out.println(b.toString());
    // XO_
    // _X_
    // __O

    // Fill the board to a tie
    assertTrue(b.choose(0, 2));
    assertTrue(b.choose(1, 0));
    assertTrue(b.choose(1, 2));
    assertTrue(b.choose(2, 0));
    assertTrue(b.choose(2, 1));

    // System.out.println(b.toString());
    // XOX
    // OXX
    // OXO
  }

  @Test
  public void testTie() {
    TicTacToeGame b = new TicTacToeGame();

    // Fill the board to a tie
    assertTrue(b.choose(0, 0));
    assertTrue(b.choose(0, 1));
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(2, 2));
    assertTrue(b.choose(0, 2));
    assertTrue(b.choose(1, 0));
    assertTrue(b.choose(1, 2));
    assertTrue(b.choose(2, 0));
    assertTrue(b.choose(2, 1));

    // System.out.println(b);
    // XOX
    // OXX
    // OXO
    assertFalse(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertTrue(b.tied());
  }

  @Test
  public void testEasyXWinRow0() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    assertTrue(b.choose(0, 0));
    assertTrue(b.choose(2, 0));
    assertTrue(b.choose(0, 1));
    assertTrue(b.choose(2, 1));
    assertTrue(b.choose(0, 2));
    // System.out.println(b);
    // XXX
    // ___
    // OO_
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyXWinRow1() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    assertTrue(b.choose(1, 0));
    assertTrue(b.choose(2, 0));
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(2, 1));
    assertTrue(b.choose(1, 2));
    // System.out.println(b);
    // ___
    // XXX
    // OO_
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyXWinRow2() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    assertTrue(b.choose(2, 0));
    assertTrue(b.choose(1, 0));
    assertTrue(b.choose(2, 1));
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(2, 2));

    // System.out.println(b);
    // ___
    // XXX
    // OO_
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyOWinRow0() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(0, 0));
    assertTrue(b.choose(2, 1));
    assertTrue(b.choose(0, 1));
    assertTrue(b.choose(1, 2));
    assertTrue(b.choose(0, 2));

    // System.out.println(b);
    // OOO
    // _XX
    // _X_
    assertTrue(b.didWin('O'));
    assertFalse(b.didWin('X'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyOWinCol0() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(0, 0));
    assertTrue(b.choose(0, 2));
    assertTrue(b.choose(1, 0));
    assertTrue(b.choose(2, 2));
    assertTrue(b.choose(2, 0));

    // System.out.println(b);
    // O_X
    // OX_
    // O_X
    assertEquals(3, b.maxMovesRemaining());
    assertTrue(b.didWin('O'));
    assertFalse(b.didWin('X'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyOWinColTwo() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    assertTrue(b.choose(2, 0));
    assertTrue(b.choose(0, 2));
    assertTrue(b.choose(1, 0));
    assertTrue(b.choose(1, 2));
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(2, 2));

    // System.out.println(b.toString());
    // __O
    // XXO
    // X_O
    assertEquals(3, b.maxMovesRemaining());
    assertTrue(b.didWin('O'));
    assertFalse(b.didWin('X'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyOWinColOne() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    assertTrue(b.choose(2, 2));
    assertTrue(b.choose(0, 1));
    assertTrue(b.choose(1, 0));
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(1, 2));
    assertTrue(b.choose(2, 1));

    // System.out.println(b.toString());
    // _O_
    // XOX
    // _OX
    assertEquals(3, b.maxMovesRemaining());
    assertTrue(b.didWin('O'));
    assertFalse(b.didWin('X'));
    assertFalse(b.tied());
  }

  @Test
  public void testWinDiagonal() {
    TicTacToeGame b = new TicTacToeGame();
    assertTrue(b.choose(0, 0));
    assertTrue(b.choose(0, 1));
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(2, 0));
    assertTrue(b.choose(2, 2));
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    // System.out.println(b);
    // XO_
    // _X_
    // O_X
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testBackDiagonal() {
    TicTacToeGame b = new TicTacToeGame();
    assertTrue(b.choose(0, 2));
    assertTrue(b.choose(0, 1));
    assertTrue(b.choose(1, 1));
    assertTrue(b.choose(1, 2));
    assertTrue(b.choose(2, 0));
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));

    // System.out.println(b);
    // _OX
    // _XO
    // X__
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testUseUnusedMethodsAsIfSomeoneClickedOnTheGUI() {
    TicTacToeGame game = new TicTacToeGame();
    game.getComputerPlayer();
    game.setComputerPlayerStrategy(new RandomAI());
    game.startNewGame();
    game.setComputerPlayerStrategy(new StopperAI());
    game.stillRunning(); 
  }
  

}