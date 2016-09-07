package model;

import java.awt.Point;
import java.util.Random;

/**
 * This TTT strategy tries to prevent the opponent from winning by checking
 * for a space where the opponent is about to win. If none found, it randomly
 * picks a place to win, which an sometimes be a win even if not really trying.
 * 
 * @author mercer
 */
public class StopperAI implements TicTacToeStrategy {

  private static Random generator;

  public StopperAI() {
    generator = new Random();
  } 

  @Override
  public Point desiredMove(TicTacToeGame theGame) {
    char[][] board = theGame.getTicTacToeBoard();
    char myChar = theGame.getCurrentPlayerChar();
    char opponentChar = 'O';
    if (myChar == 'O')
      opponentChar = 'X';

    // First look to block an opponent win
    Point move = findMove(opponentChar, board);
    if (move != null)
      return move;

    // Then look for a win
    move = findMove(myChar, board); 
    if (move != null)
      return move;

    // If no block or win, pick any move
    return randomSelection(theGame);
  }

  private Point findMove(char theOtherChar, char[][] board) {
    // Check for 2  moves in the same row
    for (int row = 0; row < board[0].length; row++) {
      
      if (board[row][0] == theOtherChar && board[row][1] == theOtherChar
          && board[row][2] == '_')
        return new Point(row, 2);
   
      if (board[row][0] == theOtherChar && board[row][2] == theOtherChar
          && board[row][1] == '_')
        return new Point(row, 1);
      
      if (board[row][1] == theOtherChar && board[row][2] == theOtherChar
          && board[row][0] == '_')
        return new Point(row, 0);
    }

    // Check for 2  moves in the same column
    for (int col = 0; col < board.length; col++) {
      if (board[0][col] == theOtherChar && board[1][col] == theOtherChar
          && board[2][col] == '_')
        return new Point(2, col);
      if (board[1][col] == theOtherChar && board[col][2] == theOtherChar
          && board[0][col] == '_')
        return new Point(0, col);
      if (board[0][col] == theOtherChar && board[col][2] == theOtherChar
          && board[1][col] == '_')
        return new Point(1, col);
    }

    // Check diagonals upper left to lower right
    if (board[0][0] == theOtherChar && board[2][2] == theOtherChar
        && board[1][1] == '_')
      return new Point(1, 1);
    if (board[1][1] == theOtherChar && board[2][2] == theOtherChar
        && board[0][0] == '_')
      return new Point(0, 0);
    if (board[0][0] == theOtherChar && board[1][1] == theOtherChar
        && board[2][2] == '_')
      return new Point(2, 2);

    // Check diagonals upper right to lower left
    if (board[0][2] == theOtherChar && board[2][0] == theOtherChar
        && board[1][1] == '_')
      return new Point(1, 1);
    if (board[1][1] == theOtherChar && board[0][2] == theOtherChar
        && board[2][0] == '_')
      return new Point(2, 0);
    if (board[2][0] == theOtherChar && board[1][1] == theOtherChar
        && board[0][2] == '_')
      return new Point(0, 2);

    return null;
  }

  private Point randomSelection(TicTacToeGame theGame) {
    boolean set = false;
    while (!set) {
      if (theGame.maxMovesRemaining() == 0)
        throw new IGotNowhereToGoException(
            " -- Hey there programmer, the board is filled");

      // Otherwise, try to randomly find an open spot 
      int row = generator.nextInt(3);
      int col = generator.nextInt(theGame.size());
      if (theGame.available(row, col)) {
        set = true;
        return new Point(row, col);
      }
    }
    return null;
  }

}