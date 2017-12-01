/**
 * ---------------------------------------------------------------------.
 * Interface: Minesweeper Model.
 * 
 * @author: Alexander "Apollon" Jerabek Purpose: This interface defines the
 *          Model (Game Board) of the popular Windows game, Minesweeper. All of
 *          the methods are necessary for the model to implement for a fully
 *          functional GUI to exist.
 */
//  Inherits From: None. 
//  Interfaces: None. 
//  Constants: None. 
//  Instance Methods: 
//     void click(int, int) 
//     int getAdjacentMines(int, int) 
//     int getTotalMineCount() 
//     boolean isFlagged(int, int) 
//     void toggleFlagged(int row, int column)
//     boolean isMine(int, int) 
//     boolean isVisible(int, int) 
//     boolean lost() String toString() 
//     boolean won() 
public interface MineSweeperModel {

  /*-------------------------------------------------------------------------.
  || This method alerts the MineSweer that the user has clicked on the square at
  || the given row/column. There are five possibilities. the GameSquare object
  || stored at the row and column given to click:
  ||
  ||    1. is a mine (player looses)
  ||    2. is visible already (do nothing)
  ||    3. is flagged (do nothing)
  ||    4. has mineNeighbors >= 1 (simply mark that visible)
  ||    5. is not adjacent to any mines with mineNeighbors == 0 (this is the hard part requiring Stack
  ||       usage in nested for loops  
   */
  /**
   * This method alerts the Game Board the user has clicked on the square
   * 
   * @param row
   *          - An int value representing the row (x) value in the game board.
   * @param column
   *          - An int value representing the column (y) value in the game
   *          board.
   */
  public void click(int row, int column);

  /**
   * This method returns the number of mines surrounding the requested square
   * (the "number" on the square). A square with a mine still should return the
   * number of surrounding mines, even though it will never display that
   * information.
   * 
   * @param row
   *          An int value representing the row (x) value in the game board.
   * @param column
   *          An int value representing the column (y) value in the game board.
   * @return The number of mines next to this square.
   */
  public int getAdjacentMines(int row, int column);

  /**
   * This method returns the number of mines found in the game board.
   * 
   * @return The number of mines.
   */
  public int getTotalMineCount();

  /**
   * This method returned whether or not the square has been flagged by the
   * user. Flags are a tool used by players to quickly tell which squares they
   * think contain mines as well as prevent accidental clicking on those
   * squares.
   * 
   * @param row
   *          An int value representing the row (x) value in the game board.
   * @param column
   *          An int value representing the column (y) value in the game board.
   * @return A boolean value representing the flagged state of this location.
   */
  public boolean isFlagged(int row, int column);

  /**
   * Changes the flagged status of any GameSquare
   * 
   * @param row
   *          The row to check
   * @param column
   *          The column to check
   */
  public void toggleFlagged(int row, int column);

  /**
   * This method determines if the square in question is a mine.
   * 
   * @param row
   *          - An int value representing the row (x) value in the game board.
   * @param column
   *          - An int value representing the column (y) value in the game
   *          board.
   * @return A boolean representing the mine status of the square.
   */
  public boolean isMine(int row, int column);

  /**
   * This method gets the visibility of the square in question. Visibilty is
   * initially defined for all squares to be false and uncovered when the click
   * method checks the square.
   * 
   * @param row
   *          - An int value representing the row (x) value in the game board.
   * @param column
   *          - An int value representing the column (y) value in the game
   *          board.
   * @return A boolean representing whether or not the square is set to be
   *         visible.
   */
  public boolean isVisible(int row, int column);

  /**
   * This method determines if the player has lost on the current board. A
   * player loses if and only if they have clicked on a mine.
   * 
   * @return A boolean representing player failure.
   */
  public boolean lost();

  /**
   * Returns a textual representation of the GameBoard. Squares will be
   * represented by one character followed by a space, except the last character
   * which will have no following space. Visible squares will either be the
   * number of mines next to the square, a blank space if no mines are adjacent,
   * or a '*' character for a mine. Newlines will separate each row of the game
   * board.
   * 
   * @return A String representation of the game board data.
   */
  public String toString();

  /**
   * This method determines if a player has won the game. Winning means all
   * non-mine squares are visible and no mines have been detonated.
   * 
   * @return A boolean representing whether or not the player has won.
   */
  public boolean won();
}