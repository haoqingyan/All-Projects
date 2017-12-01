import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToeGUI extends JFrame {

  public static void main(String[] gui) {
    TicTacToeGUI g = new TicTacToeGUI();
    g.setVisible(true);
  }

  private TicTacToe ttt;

  public TicTacToeGUI() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(600, 625);
    this.setResizable(false);
    this.setTitle("Tic Tac Toe");
    Container c = getContentPane();
    ttt = new TicTacToe();
    Menu men = new Menu("File");
    MenuItem item1 = new MenuItem("New Game");
    item1.addActionListener(new NextGameListener());
    MenuItem exit = new MenuItem("Exit");
    exit.addActionListener(new ExitListener());
    men.add(item1);
    men.add("-");
    men.add(exit);
    MenuBar mb = new MenuBar();
    mb.add(men);
    this.setMenuBar(mb);
    c.add((new TTTPanel(ttt)));
    this.addMouseListener(new MouseClick());
  }

  private class TTTPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private TicTacToe myBoard;
    private Font myFont;
    private static final int OFFSET = 70;

    public TTTPanel(TicTacToe t) {
      myBoard = t;
      myFont = new Font("Arial", Font.TRUETYPE_FONT, 90);
    }

    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.BLACK);
      g2.drawLine(0, 200, 600, 200);
      g2.drawLine(0, 400, 600, 400);
      g2.drawLine(200, 0, 200, 600);
      g2.drawLine(400, 0, 400, 600);
      g2.setFont(myFont);
      char[][] temp = ttt.getCharArray();
      for (int i = 0; i < temp.length; i++) {
        for (int j = 0; j < temp[i].length; j++) {
          if (temp[i][j] == 'O') {
            g2.setColor(Color.BLUE);
            g2.drawString(temp[i][j] + "", (i * 200) + OFFSET, (j * 200) + 2
                * OFFSET);
          } else if (temp[i][j] == 'X') {
            g2.setColor(Color.RED);
            g2.drawString(temp[i][j] + "", (i * 200) + OFFSET, (j * 200) + 2
                * OFFSET);
          }
        }
      }
      g2.setColor(Color.BLACK);
      if (ttt.didTie()) {
        g2.drawString("Tie Game!", 100, 266);
      }
      if (ttt.didWin('O')) {
        g2.drawString("O wins!", 200, 266);
      } else if (ttt.didWin('X')) {
        g2.drawString("X wins!", 200, 266);
      }
    }
  }

  private class NextGameListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      ttt = new TicTacToe();
      repaint();
    }
  }

  private class ExitListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      System.exit(0);
    }
  }

  private class MouseClick extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      if (!ttt.didTie() && !ttt.didWin('X') && !ttt.didWin('O')) {
        int x = e.getX();
        int y = e.getY();
        int row;
        if(x <= 200)
          row = 0;
        else if(x <= 400)
          row = 1;
        else row = 2;

        int col;
        if(y <= 225)
           col = 0;
        else if(y <= 425)
          col = 1;
        else col = 2;

        char[][] temp = ttt.getCharArray();
        if (temp[row][col] == '_') {
          ttt.choose(row, col);
        }
      }
      repaint();
    }
  }

  private static final long serialVersionUID = 1L;
}