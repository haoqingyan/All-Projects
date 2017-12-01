/**
 * This class represents one part of the Boggle, a word game designed by Allan Turoff
 * and trademarked by Parker Brothers and Hasbro. 
 * 
 * The game of Boggle has a dice tray that can hold 16 dice that can be "rolled" to 
 * generate a random collection of 16 letters. Each six- sided die has letters from 
 * which players try to find words. Words are possible if letters line up next to 
 * each other horizontally, vertically, or diagonally without being reused. 
 * There are 3 letters that can be connected to any letter on a corner, 
 * 5 for the six letters on the borders and 8 for the middle four letters 
 * (there is no wraparound).
 * 
 * @author Rick Mercer   (we are use professor Mercer's first part)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BoggleTray {

  private char[][] path;
  private char[][] board;
  public static final char TRIED = '@';
  public static final char PART_OF_WORD = '!';
  private String attempt;
  private int index;
  public static final int SIZE = 4;
  public static final int NUMBER_SIDES = 6;

  // 4x4 Boggle includes 16 dice with the character indicated here (except Q
  // would be Qu). Each string element has six letters for the six sides of the
  // die.
  String[] dice = { "LRYTTE", "VTHRWE", "EGHWNE", "SEOTIS", "ANAEEG", "IDSYTT",
      "OATTOW", "MTOICU", "AFPKFS", "XLDERI", "HCPOAS", "ENSIEU", "YLDEVR",
      "ZNRNHL", "NMIQHU", "OBBAOJ" };

  private ArrayList<String> randomDice;

  private static Random generator;

  public BoggleTray() {
    randomDice = new ArrayList<String>();
    for (int die = 0; die < SIZE * SIZE; die++)
      randomDice.add(dice[die]);

    generator = new Random();
    board = getRandomizedDiceArray();
  }

  public BoggleTray(char[][] newBoard) {
    board = newBoard;
  }

  @Override
  public String toString() {
    String result = "\n";
    for (int r = 0; r < SIZE; r++) {
      for (int c = 0; c < SIZE; c++) {
        if (board[r][c] == 'Q')
          result += " Qu";
        else
          result += "  " + board[r][c];
      }
      result += " \n\n";
    }
    return result;
  }

  /**
   * Return true if search is word that can found on the board following the
   * rules of Boggle
   * 
   * @param search
   *          A word that may be in the board by connecting consecutive letters
   * @return True if search is found
   */
  public boolean foundInBoggleTray(String search) {
    attempt = search.toUpperCase().trim();
    boolean found = false;
    for (int r = 0; r < SIZE; r++) {
      for (int c = 0; c < SIZE; c++)
        if (board[r][c] == attempt.charAt(0)) {
          init();
          found = check(r, c);
          if (found) {
            return true;
          }
        }
    }
    return found;
  }

  private void init() {
    path = new char[SIZE][SIZE];
    for (int r = 0; r < SIZE; r++)
      for (int c = 0; c < SIZE; c++)
        path[r][c] = '.';
    index = 0;
  }

  private boolean check(int r, int c) {
    boolean found = false;
    if (valid(r, c)) {
      path[r][c] = TRIED;
      if (board[r][c] == attempt.charAt(index)) {
        if (board[r][c] == 'Q')
          index += 2;
        else
          index++;
      }

      if (index >= attempt.length())
        found = true;
      else {
        found = check(r - 1, c - 1);
        if (!found)
          found = check(r - 1, c);
        if (!found)
          found = check(r - 1, c + 1);
        if (!found)
          found = check(r, c - 1);
        if (!found)
          found = check(r, c + 1);
        if (!found)
          found = check(r + 1, c - 1);
        if (!found)
          found = check(r + 1, c);
        if (!found)
          found = check(r + 1, c + 1);
        // If still not found, allow backtracking to seeds the same letter in a
        // different location later as in looking for foot in this board
        // ....
        // ..T.
        // ..O.
        // .FO.
        if (!found) {
          path[r][c] = '.';
          index--;
        }
      }
      if (found) {
        // Mark where the letter was found, not required, but could be used to
        // show the actual path of the word that was found.
        path[r][c] = board[r][c];
      }
    }
    return found;
  }

  private boolean valid(int r, int c) {
    return r >= 0 && r < SIZE && c >= 0 && c < SIZE && path[r][c] != TRIED
        && board[r][c] == attempt.charAt(index);
  }

  public char[][] getRandomizedDiceArray() {
    // Get the SIZE*SIZE dice in a different random order
    char[][] randomBoard = new char[4][4];
    Collections.shuffle(randomDice);
    int dieNumber = 0;
    for (int r = 0; r < SIZE; r++)
      for (int c = 0; c < SIZE; c++) {
        String s = randomDice.get(dieNumber);
        char letterToPlace = s.charAt(generator.nextInt(NUMBER_SIDES));
        randomBoard[r][c] = letterToPlace;
        dieNumber++;
      }
    return randomBoard;
  }
}