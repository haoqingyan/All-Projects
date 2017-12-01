
/**
   * The model for a Tic Tac Toe game that can be played the console or with 
   * the GUI http://www.cs.arizona.edu/~mercer/Projects/TicTacToeGUI.java
   * 
   * Yichao Tang
   * Haoqing Yan
   */
public class TicTacToe 
{

  char[][] Gameboard = {{'_','_','_'},{'_','_','_'},{'_','_','_'}}; 
  char NextStep='X';

  // Construct a 3x3 array of char to store '_' for not used or 
  // an 'X' for the eX player or 'O' for the Oh player (do not use zero),
  // Also set the first player to be 'X'
  public TicTacToe()  
  {}
  // Let a player choose a move at the given row and col (if not taken) 
  // and return true.  Return false if the row or col are out of range
  // of 0..2 or there is an attempt to take a spot that was already chosen. 
  // Note: Always have 'X' go first. 
  public boolean choose(int row, int column) 
  {
	  if(row>2||row<0||column>2||column<0)
	  {
		  return false;
	  }
	    
	  if(Gameboard[row][column]=='_')
	  {
		  Gameboard[row][column]=NextStep;
		  TicTacToe game= new TicTacToe();
	
		  if (NextStep=='X') 
		  {
			NextStep='O';
		  }
		  else 
		  {
			NextStep='X';
		  }
		  return true;
		  
	  }
	  else 
	  {
		  return false;
	  }
  }

  
  // Return the next player as 'X' or 'O'.
  public char getNextPlayerChar()
  {
	  return NextStep;
  }

  // Return a textual version of the tic tac toe board like this:
  //   O _ X 
  //   O X _ 
  //   O _ X
  //
  @Override
  public String toString() 
  {
	  String result="";
	  for (int row = 0; row < Gameboard.length; row++) 
	  {
		for (int index = 0; index < Gameboard.length; index++)
		{
			result+=Gameboard[row][index]+" ";
		}
		result+="\n";
	  }
	  return result;
  }
  // Get back the current state of the game with 'X' and 'O' if chosen.
  // This is needed in the GUI to display a graphical view.
 public char[][] getCharArray()
  {
	 return Gameboard;
  }
// Allow anyone to check if either the 'X' or 'O' player has won
 public boolean didWin(char playerChar) 
  {
	 boolean result = false;
	 for (int index = 0; index < Gameboard.length; index++) 
	 {
		 if((Gameboard[index][0]==Gameboard[index][1]&&Gameboard[index][1]==Gameboard[index][2]&&Gameboard[index][2]==playerChar)
				 ||(Gameboard[0][index]==Gameboard[1][index]&&Gameboard[1][index]==Gameboard[2][index]&&Gameboard[1][index]==playerChar)
				 ||(Gameboard[0][0]==Gameboard[1][1]&&Gameboard[2][2]==Gameboard[1][1]&&Gameboard[2][2]==playerChar)
				 ||(Gameboard[2][0]==Gameboard[1][1]&&Gameboard[0][2]==Gameboard[1][1]&&Gameboard[0][2]==playerChar))
		 {
			result=true;
		 }
	}
	 return result;
  }
  // Use this method to see if there is a tie or not.
  // Return true if there are no places remaining and there is no win
  public boolean didTie() 
  {
	  boolean result= true;
	  TicTacToe judge= new TicTacToe();
	  if(judge.didWin('X')==false&&judge.didWin('O')==false)
	  {
		  for (int row = 0; row < Gameboard.length; row++) {
			for (int column = 0; column < Gameboard.length; column++) 
			{
				if (Gameboard[row][column]=='_') 
				{
				     result=false;
				}
			}
		}
	  }
	  return result;
  }
  // Allow users to know if there are any more possible choices
  // Return true if the game is over, false otherwise
  public boolean notDone() 
  {
	  boolean result = false;
	  for (int row = 0; row < Gameboard.length; row++) {
			for (int column = 0; column < Gameboard.length; column++) 
			{
				if (Gameboard[row][column]=='_') 
				{
				     result=true;
				}
			}
		}
	  return result;
  }

   
}