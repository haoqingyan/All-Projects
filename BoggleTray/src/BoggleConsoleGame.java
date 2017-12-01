//Programmers: Yichao Tang & Haoqing Yan
//1) Yichao Tang, 
//2) Haoqing Yan.
//

import java.util.Scanner;


public class BoggleConsoleGame {

	  
	public static void main(String[] args) 
	{
		
		//intialize
		BoggleTray NewGame = new BoggleTray();
		Boggle game = new Boggle();
	    game.setBoggleTray(NewGame);
	    System.out.println("Play one game of Boggle:\n");
	    System.out.println(game.getBoggleTrayAsString().toUpperCase());
	    System.out.println("Enter words or ZZ to quit:\n");
	    boolean ContinueToInput = true;
	    while (ContinueToInput) 
	    {
	    	Scanner inputAn= new Scanner(System.in);
	    	while (inputAn.hasNext()) 
		    {
	    		String temp = inputAn.next();
	    		if(temp.toLowerCase().equals("zz"))
	    		{
	    			 ContinueToInput = false;
	    			 break;
	    		}
	    		else 
	    		{
	    			game.addGuess(temp);				
				}
		    }			
		}
	    BoggleConsoleGame outputor = new BoggleConsoleGame();
	    System.out.println("\nYour score: "+game.getScore()+"\n");
	    System.out.println("Words you found: ");
	    outputor.formatedprinting(game.getWordsFound());
	    System.out.println("\n");
	    System.out.println("Incorrect words: ");
	    outputor.formatedprinting(game.getWordsIncorrect());
	    System.out.println("\n");
	    System.out.println("You could have found "+(game.getWordsNotGuessed().size()) + " more words.\nThe computer found all of your words plus these: \n");
	    outputor.formatedprinting(game.getWordsNotGuessed());
	    System.out.println("\n");
	}
 
	 private void formatedprinting(java.util.List<String> temp)
	    {
	    	for (int i = 1; i <= temp.size(); i++) 
	    	{
				if(i%10==0)
				{
					System.out.print(temp.get(i-1)+"\n");
				}
				else 
				{
					System.out.print(temp.get(i-1)+" ");
				}
			}
	    }
}
