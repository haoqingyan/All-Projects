import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A model for the game of 20 questions
 * 
 * @author Haoqing Yan
 */
public class GameTree {

	/**
	 * Constructor needed to create the game.
	 * 
	 * @param name
	 *            this is the name of the file we need to import the game
	 *            questions and answers from.
	 */

	private class TreeNode {
		private String data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(String Data) {
			data = Data;
			left = null;
			right = null;
		}

		public TreeNode(String ref, TreeNode refleft, TreeNode refright) {
			data = ref;
			left = refleft;
			right = refright;
		}
	}

	private TreeNode root;
	private Scanner inFile;
	private String currentFileName;

	public GameTree(String file) {
		currentFileName = file;
		try {
			inFile = new Scanner(new File(currentFileName));
		} catch (FileNotFoundException e) {
			// This block would execute if currentFileNane is not found.
			// We will not have tests to construct GameTree objects with
			// non-existent files.
		}

		root = build();
		inFile.close();
		currNode = root;
	}

	private TreeNode build() {
		String data;
		TreeNode tempTree = null;
		TreeNode left;
		TreeNode right;

		if (inFile.hasNextLine()) {
			data = inFile.nextLine().trim();
			if (data.contains("?")) {
				left = build();
				right = build();
				tempTree = new TreeNode(data, left, right);
			} else {
				tempTree = new TreeNode(data);
			}
		}

		return tempTree;

	}

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQuestion
	 *            The question to add where the old answer was.
	 * @param newAnswer
	 *            The new Yes answer for the new question.
	 */
 
	public void add(String newQuestion, String newAnswer) {
		
		TreeNode newTree = new TreeNode(newQuestion,
				new TreeNode(newAnswer), new TreeNode(currNode.data));
		currNode = newTree;
		LastNode.left=newTree;
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 * 
	 * @return False if the current node is an internal node rather than an
	 *         answer at a leaf.
	 */
	public boolean foundAnswer() {
		if (this.getCurrent().endsWith("?")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.
	 * 
	 * @return The current question or answer.
	 */
	private TreeNode currNode;
	
	public String getCurrent() {

		return currNode.data;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 * 
	 * @param yesOrNo
	 */
	private TreeNode LastNode;
	public void playerSelected(Choice yesOrNo){
		LastNode=currNode;
		if (yesOrNo == Choice.Yes) {
			currNode = currNode.left;
		} else {
			currNode = currNode.right;
		}
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the
	 * question at the root of this GameTree.
	 */
	public void reStart() {
		LastNode=null;
		currNode = root;
	}

	/**
	 * Return a textual version of this object
	 */
	public String toString() {

		return help(root, 0);
	}

	private String help(TreeNode tempRoot, int level) {

		String result = "";
		if (tempRoot.right != null) {
			result += help(tempRoot.right, level + 1);
		}

		for (int i = 0; i < level; i++) {
			result += "- ";
		}

		result += tempRoot.data + "\n";
		if (tempRoot.left != null) {
			result += help(tempRoot.left, level + 1);
		}
		return result;
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 * 
	 */
	public void saveGame() {
		String outputFileName = currentFileName;
		FileWriter charToBytesWriter = null;
		try {
			charToBytesWriter = new FileWriter(outputFileName);
		} catch (IOException ioe) {
			System.out.println("Could not create the new file: " + ioe);
		}
		PrintWriter diskFile = new PrintWriter(charToBytesWriter);
	
		help1(diskFile, root);

		diskFile.close();

	}

	private void help1(PrintWriter diskFile, TreeNode temp) {
		if (temp != null) {
			diskFile.println(temp.data);
			help1(diskFile, temp.left);
			help1(diskFile, temp.right);
		}

	}
}