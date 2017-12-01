import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

//Programmers: Yichao Tang & Haoqing Yan
//1) Yichao Tang, 
//2) Haoqing Yan.
//

public class Boggle {

	private BoggleTray boggleTray;
	private ArrayList<String> boggleDict;
	private ArrayList<String> wordsGuessed = new ArrayList<String>();
	private ArrayList<String> wordsFound = new ArrayList<String>();
	private ArrayList<String> wordsIncorrect = new ArrayList<String>();

	public Boggle() {
		Scanner text = null;
		boggleDict = new ArrayList<String>();
		try {
			text = new Scanner(new File("BoggleWords"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (text.hasNextLine()) {
			String line = text.nextLine();
			boggleDict.add(line);
		}
	}

	public void setBoggleTray(BoggleTray dt) {
		boggleTray =dt;
	}

	// Return the BoggleTray object as a textual representation as a String
	public String getBoggleTrayAsString() {
		return boggleTray.toString();
	}

	// Record one word (a string with no whitespace) as one of the users'
	// guesses.
	// Do what you want with it, but oneGuess should be processed so all methods
	// can return the correct values such as getScore() and getWordsFound().
	public void addGuess(String oneGuess) {
		wordsGuessed.add(oneGuess.toLowerCase());
	}

	// Return a list of all words the user entered that count for the score. The
	// found words
	// must be in their natural ordering. This method should return an empty
	// list until
	// addGuess(String) is called at least once. The returned List<E> could also
	// be empty if
	// no attempts actually counted for anything. There must be no duplicates!
	public List<String> getWordsFound() {
		for (int i = 0; i < wordsGuessed.size(); i++) {
			String word = wordsGuessed.get(i);
			if (boggleTray.foundInBoggleTray(word)
					&& (word.length() >= 3 && word.length() <= 16)) {
				if (boggleDict.contains(word) && !wordsFound.contains(word)) {
					wordsFound.add(word);
				}
			}
		}
		Collections.sort(wordsFound);
		return wordsFound;
	}

	// Return a list of all words the user entered that do not count for the
	// score
	// in their natural order. This list may be empty with no words guessed or
	// all
	// guessed words actually count for points. There must be no duplicates!
	public List<String> getWordsIncorrect() {
		for (int i = 0; i < wordsGuessed.size(); i++) {
			String word = wordsGuessed.get(i);
			if (!(wordsIncorrect.contains(word))) {
				if (word.length() < 3 || word.length() > 16) {
					wordsIncorrect.add(word);
				}
				if (!boggleDict.contains(word)) {
					wordsIncorrect.add(word);
				}
				if (boggleDict.contains(word)) {
					if ((!boggleTray.foundInBoggleTray(word)))
						wordsIncorrect.add(word);
				}
			}
		}
		Collections.sort(wordsIncorrect);
		return wordsIncorrect;
	}

	// All words the user could have guessed but didn't, in their natural order.
	// This list could also be empty at first or if the user guessed ALL words
	// in the board and in the list of 80K words (unlikely).
	// There must be no duplicates!
	public List<String> getWordsNotGuessed() {
		return notGuessed(wordsGuessed);
	}

	private List<String> notGuessed(ArrayList<String> noGuess) {
		// Words not Guessed
		ArrayList<String> wordsNotGuessed = new ArrayList<String>();
		// Check All words in the List
		Scanner text = null;
		try {
			text = new Scanner(new File("BoggleWords"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (text.hasNextLine()) {
			String word = text.nextLine();
			// Make sure the target in the board (the length should in the
			// range)
			if (boggleTray.foundInBoggleTray(word)
					&& (word.length() >= 3 && word.length() <= 16)) {
				if (!(noGuess.contains(word)))
					wordsNotGuessed.add(word);
			}
		}
		Collections.sort(wordsNotGuessed);
		return wordsNotGuessed;
	}

	// Return the correct score.
	public int getScore() {
		int score = 0;
		this.getWordsFound();
		for (int i = 0; i < wordsFound.size(); i++) {
			String tempGuess = wordsFound.get(i);
			int length = tempGuess.length();
			if (length <= 4) {
				score++;
			} else if (length == 5) {
				score += 2;
			} else if (length == 6) {
				score += 3;
			} else if (length == 7) {
				score += 5;
			} else {
				score += 11;
			}
		}
		return score;
	}
}
