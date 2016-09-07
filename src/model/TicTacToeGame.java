package model;

import controller.OurObservable;

public class TicTacToeGame extends OurObservable {
  private char[][] board;
  private char nextChar;
  private int moveNumber;
  private int size;
  private ComputerPlayer computerPlayer;

  public TicTacToeGame() {
    size = 3;
    initializeBoard();
    computerPlayer = new ComputerPlayer();
  }

  public void startNewGame() {
    initializeBoard();
    // The state of this model just changed so tell any observer to update themselves
    notifyObservers();
  }

  public void setComputerPlayerStrategy(TicTacToeStrategy AI) {
    this.computerPlayer.setStrategy(AI);
  }

  public ComputerPlayer getComputerPlayer() {
    return computerPlayer;
  }

  private void initializeBoard() {
    board = new char[size][size];
    for (int r = 0; r < size; r++)
      for (int c = 0; c < size; c++)
        board[r][c] = '_';
    moveNumber = 0;
  }

  // Precondition row and col are both in the range of 0 through 2
  public boolean choose(int row, int col) {
    if (board[row][col] != '_')
      return false;
    else {
      if (moveNumber % 2 == 0)
        nextChar = 'X';
      else
        nextChar = 'O';
      board[row][col] = nextChar;
      moveNumber++;
      notifyObservers();
      return true;
    }
  }

  public char getCurrentPlayerChar() {
    return nextChar;
  }

  @Override
  public String toString() {
    String result = "";
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++)
        result += " " + board[r][c] + " ";
      result += "\n";
    }
    return result;
  }

  public char[][] getTicTacToeBoard() {
    return board;
  }

  public boolean didWin(char playerChar) {
    return wonByRow(playerChar) || wonByCol(playerChar) || wonByDiagonal(playerChar);
  }

  private boolean wonByRow(char playerChar) {
    for (int r = 0; r < size; r++) {
      int rowSum = 0;
      for (int c = 0; c < size; c++)
        if (board[r][c] == playerChar)
          rowSum++;
      if (rowSum == size)
        return true;
    }
    return false;
  }

  private boolean wonByCol(char playerChar) {
    for (int c = 0; c < size; c++) {
      int colSum = 0;
      for (int r = 0; r < size; r++)
        if (board[r][c] == playerChar)
          colSum++;
      if (colSum == size)
        return true;
    }
    return false;
  }

  private boolean wonByDiagonal(char playerChar) {
    // Check Diagonal from upper left to lower right
    int sum = 0;
    for (int r = 0; r < size; r++)
      if (board[r][r] == playerChar)
        sum++;
    if (sum == size)
      return true;

    // Check Diagonal from upper right to lower left
    sum = 0;
    for (int r = size - 1; r >= 0; r--)
      if (board[size - r - 1][r] == playerChar)
        sum++;
    if (sum == size)
      return true;

    // No win on either diagonal
    return false;
  }

  public boolean tied() {
    return maxMovesRemaining() == 0 && !didWin('X') && !didWin('O');
  }

  public int maxMovesRemaining() {
    int result = 0;
    for (int r = 0; r < size; r++)
      for (int c = 0; c < size; c++)
        if (board[r][c] == '_')
          result++;
    return result;
  }

  public int size() {
    return size;
  }

  public boolean available(int r, int c) {
    return board[r][c] == '_';
  }

  public boolean stillRunning() {
    return !tied() && !didWin('X') && !didWin('O');
  }
}