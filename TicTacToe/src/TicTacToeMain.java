import java.util.Scanner;



public class TicTacToeMain {

	public static void main(String[] args) 
	{
       	Scanner input = new Scanner(System.in);
       	TicTacToe game = new TicTacToe();

       	System.out.println("Play a game of Tic Tac Toe\n");
       	while(!game.didWin('O')&&!game.didWin('X'))
       	{
           System.out.print("Row and column for "+game.getNextPlayerChar()+"? ");
           int row=input.nextInt();
           int column =input.nextInt();
       	   while(game.choose(row, column)==false)
       		  {
       		      System.out.print("Row and column for "+game.getNextPlayerChar()+"? ");
       		      row=input.nextInt();
       		      column =input.nextInt();
       		  }
       	   System.out.println(game.toString());       	   
		}
       	if(game.didWin('O')==true)
       	{
       		System.out.println("=========\nGame Over \nO won");
       	}
       	else 
       	{
       		System.out.println("=========\nGame Over \nX won");
		}	
	}

}
