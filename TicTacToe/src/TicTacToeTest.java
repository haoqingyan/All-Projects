import static org.junit.Assert.*;
import org.junit.Test;
/**
   * The model for a Tic Tac Toe game that can be played the console or with 
   * the GUI http://www.cs.arizona.edu/~mercer/Projects/TicTacToeGUI.java
   * 
   * Yichao Tang
   * Haoqing Yan
   */

public class TicTacToeTest {
  private TicTacToe ttt= new TicTacToe();
  @Test
  public void testShowAllMethods() {
    TicTacToe game = new TicTacToe();
    assertEquals('X', game.getNextPlayerChar());
    assertTrue(game.choose(0, 2));
    assertEquals('O', game.getNextPlayerChar());
    assertTrue(game.choose(0, 1));
    assertEquals('X', game.getNextPlayerChar());
    assertTrue(game.choose(1, 1));
    assertEquals('O', game.getNextPlayerChar());
    // No longer available 0, 2
    assertFalse(game.choose(0, 2));
    // 3 is out of range
    assertFalse(game.choose(0, 3));
    // 999 out of range
    assertFalse(game.choose(999, 2));
    System.out.println(game.toString());
    //    _ O X 
    //    _ X _ 
    //    _ _ _ 
    assertTrue(game.notDone());
    assertFalse(game.didWin('O'));
    assertFalse(game.didWin('X'));
    assertFalse(game.didTie());
    assertEquals('O', game.getNextPlayerChar());
    game.choose(0, 0);
    // System.out.println(game.toString());
    //    O O X 
    //    _ X _ 
    //    _ _ _ 
    game.choose(2, 0);
    // System.out.println(game.toString());
    //    O O X 
    //    _ X _ 
    //    X _ _ 
    assertTrue(game.notDone());
    assertTrue(game.didWin('X'));
    assertFalse(game.didWin('O'));
    assertFalse(game.didTie());
  }
	 
  }

