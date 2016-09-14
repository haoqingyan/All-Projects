package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.OurObserver;
import model.ComputerPlayer;
import model.TicTacToeGame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextFieldView extends JPanel implements OurObserver {

	private TicTacToeGame theGame;
	private JButton moveButton = new JButton("Make the move");
	private JTextArea textArea = null;
	private ComputerPlayer computerPlayer;
	private int height, width;
	private JTextField textFieldRow;
	private JTextField textFieldCol;
	private JTextArea txtArea;
	private String board;

	public TextFieldView(TicTacToeGame TicTacToeGame, int width, int height) {
		setBackground(Color.CYAN);
		setForeground(Color.CYAN);
		theGame = TicTacToeGame;
		this.height = height;
		this.width = width;
		computerPlayer = theGame.getComputerPlayer();
		initializeTextPanel();
	}

	// This method is called by OurObservable's notifyObservers()
	// public void update() {
	// if (theGame.maxMovesRemaining() == theGame.size() * theGame.size())
	// resetButtons(true);
	//
	// if (!theGame.stillRunning())
	// resetButtons(false);
	// else {
	// updateButtons();
	// stateButton.setText("Click your move");
	// }
	// }

	private void initializeTextPanel() {
		JPanel textPanel = new JPanel();
		int size = theGame.size();
		setLayout(null);
		moveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!moveButton.isEnabled() || textFieldRow.getText().isEmpty()
						|| textFieldCol.getText().isEmpty()) {
					return;
				}
				int row = Integer.parseInt(textFieldRow.getText());
				int col = Integer.parseInt(textFieldCol.getText());
				if (row < 0 || row >= theGame.getTicTacToeBoard().length
						|| col < 0
						|| col >= theGame.getTicTacToeBoard()[0].length) {
					JOptionPane.showMessageDialog(null,
							"Selection not available");
				} else if (!theGame.available(row, col)) {
					JOptionPane.showMessageDialog(null,
							"Selection not available");
				} else {
					
				}
				if (theGame.tied()) {
					moveButton.setText("Tied");
					moveButton.setEnabled(false);
				} else if (theGame.didWin('X')) {
					moveButton.setText("X wins");
					moveButton.setEnabled(false);
				} else {
					// If the game is not over, let the computer player choose
					// This algorithm assumes the computer player always
					// goes after the human player and is represented by 'O',
					// not 'X'
					Point play = computerPlayer.desiredMove(theGame);
					theGame.choose(play.x, play.y);
					if (theGame.didWin('O')) {
						moveButton.setText("O wins");
						moveButton.setEnabled(false);
					}
				}
				textFieldRow.setText("");
				textFieldCol.setText("");
			}
		});
		moveButton.setBounds(130, 24, 130, 23);

		this.add(moveButton);

		textFieldRow = new JTextField();
		textFieldRow.setBounds(10, 10, 66, 21);
		add(textFieldRow);
		textFieldRow.setColumns(10);

		textFieldCol = new JTextField();
		textFieldCol.setColumns(10);
		textFieldCol.setBounds(10, 49, 66, 21);
		add(textFieldCol);

		JLabel lblRow = new JLabel("Row");
		lblRow.setBounds(82, 10, 54, 15);
		add(lblRow);

		JLabel lblCol = new JLabel("Column");
		lblCol.setBounds(82, 49, 54, 15);
		add(lblCol);

		txtArea = new JTextArea();
		txtArea.setFont(new Font("Courier New", Font.BOLD, 36));
		txtArea.setEditable(false);
		txtArea.setRows(5);
		txtArea.setColumns(5);
		txtArea.setBounds(44, 80, 200, 215);
		add(txtArea);
		resetTextView();
	}

	public void update() {
		if (theGame.maxMovesRemaining() == theGame.size() * theGame.size())
			resetTextView();

		else {
			updateTextAreas();
		}
	}

	public void updateTextAreas() {
		char[][] temp = theGame.getTicTacToeBoard();
		String text = "";
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				text += " " + temp[i][j]+" ";
			}
			text += "\n";
			text += "\n";
		}
		txtArea.setText(text);
		if (theGame.tied()) {
			moveButton.setText("Tied");
			moveButton.setEnabled(false);
		} else if (theGame.didWin('X')) {
			moveButton.setText("X wins");
			moveButton.setEnabled(false);
		} else if (theGame.didWin('O')) {
			moveButton.setText("O wins");
			moveButton.setEnabled(false);
		}
	}
	public void resetTextView() {
		char[][] temp = theGame.getTicTacToeBoard();
		String text = "";
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				text += " " + "_"+" ";
			}
			text += "\n";
			text += "\n";
		}
		txtArea.setText(text);
		moveButton.setText("Make the move");
		moveButton.setEnabled(true);
	}

}