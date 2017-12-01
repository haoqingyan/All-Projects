//
// 1) Yichao Tang, 2) Haoqing Yan.
//
import static org.junit.Assert.*;
import org.junit.Test;

public class BoggleTrayTest 
{

  private char[][] tray = 
	  {   // Always use upper case letters in the dice tray
      {'A', 'B', 'C', 'D' }, 
      {'E', 'F', 'G', 'H' },
      {'I', 'J', 'K', 'L' }, 
      {'M', 'N', 'O', 'P' } };
  private char[][] prob = 
	  {   // Always use upper case letters in the dice tray
      {'M', 'O', 'S', 'E' }, 
      {'D', 'A', 'L', 'N' },
      {'T', 'O', 'P', 'D' }, 
      {'S', 'S', 'E', 'N' } };
  private char[][] prob2 = 
	  {   // Always use upper case letters in the dice tray
      {'r', 'e', 'd', 'm' }, 
      {'b', 'a', 'n', 'o' },
      {'t', 'q', 'd', 'f' }, 
      {'l', 'o', 'E', 'v' } };
  private char[][] prob3 = 
	  {   // Always use upper case letters in the dice tray
      {'n', 'a', 't', 'r' }, 
      {'e', 'e', 'e', 'e' },
      {'e', 'r', 'i', 't' }, 
      {'s', 'q', 'e', 'N' } };
  @Test
  public void testStringFindWhenThereStartingInUpperLeftCorner() 
  {
    BoggleTray bt = new BoggleTray(tray);
    assertTrue(bt.foundInBoggleTray("ABC"));  
    assertTrue(bt.foundInBoggleTray("abC"));  // Must be case insensitive
    assertTrue(bt.foundInBoggleTray("aBf"));
    assertTrue(bt.foundInBoggleTray("abc"));
    assertTrue(bt.foundInBoggleTray("ABCD"));
    // ... 
    assertTrue(bt.foundInBoggleTray("ABFEJINM"));
    assertTrue(bt.foundInBoggleTray("AbCdHgFeIjKLpONm"));
    assertTrue(bt.foundInBoggleTray("ABCDHLPOKJNMIEFG"));
  }

  @Test
  public void testStringFindWhenNotThere () 
  {
    BoggleTray bt = new BoggleTray(tray);
    assertFalse(bt.foundInBoggleTray("acb"));
    assertFalse(bt.foundInBoggleTray("AiE"));
    // ... 
  }

  @Test
  public void testStringFindWhenAttemptIsMadeToUseALetterTwice () 
  {
    BoggleTray bt = new BoggleTray(tray);
    assertFalse(bt.foundInBoggleTray("ABA"));
    assertFalse(bt.foundInBoggleTray("ABB"));
    assertFalse(bt.foundInBoggleTray("aEa"));
    // ... 
  }


  
}

