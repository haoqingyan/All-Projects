/**
 * The beginning of a unit test for MineSweeper.  
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MineSweeperTest {

  @Test
  public void testGetAdjacentMinesWithAGivenTwodArrayOfBooleans() {

    boolean[][] b1 =

    { { false, false, false, false, false },
      { false, false, true,  true,  false },
      { false, false, false, true,  false }, };

    // Use the non-random constructor when testing to avoid random mine placement.
    MineSweeper ms = new MineSweeper(b1);

    // Check adjacent mines around every possible GameSquare
    assertEquals(0, ms.getAdjacentMines(0, 0));
    assertEquals(1, ms.getAdjacentMines(0, 1));
    assertEquals(2, ms.getAdjacentMines(0, 2));
    assertEquals(2, ms.getAdjacentMines(0, 3));
    assertEquals(1, ms.getAdjacentMines(0, 4));

    assertEquals(0, ms.getAdjacentMines(1, 0));
    assertEquals(1, ms.getAdjacentMines(1, 1));
    assertEquals(2, ms.getAdjacentMines(1, 2)); // works even if it is a mine
    assertEquals(2, ms.getAdjacentMines(1, 3));
    assertEquals(2, ms.getAdjacentMines(1, 4));

    assertEquals(0, ms.getAdjacentMines(2, 0));
    assertEquals(1, ms.getAdjacentMines(2, 1));
    assertEquals(3, ms.getAdjacentMines(2, 2));
    assertEquals(2, ms.getAdjacentMines(2, 3));
    assertEquals(2, ms.getAdjacentMines(2, 4));
  }
}