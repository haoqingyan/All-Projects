/** 
 * MouseListener
 * @author Alexander "Apollon" Jerabek
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MineSweeperGUI extends JFrame { // implements Runnable {

  public static void main(String[] args) {
    // @SuppressWarnings("unused")
    MineSweeperGUI gui = new MineSweeperGUI();
    gui.setVisible(true);
  }

  private static final long serialVersionUID = 2795047810126143006L;

  public static String baseDir = System.getProperty("user.dir") + File.separator + "images"
      + File.separator;

  private final static int SQUARESIZE = 20;
  private final static ImageIcon UNCLICKED = new ImageIcon(baseDir + "unclicked.GIF");
  private final static ImageIcon BLANK = new ImageIcon(baseDir + "blank.GIF");
  private final static ImageIcon MINE = new ImageIcon(baseDir + "mine.GIF");
  private final static ImageIcon ONE = new ImageIcon(baseDir + "one.GIF");
  private final static ImageIcon TWO = new ImageIcon(baseDir + "two.GIF");
  private final static ImageIcon THREE = new ImageIcon(baseDir + "three.GIF");
  private final static ImageIcon FOUR = new ImageIcon(baseDir + "four.GIF");
  private final static ImageIcon FIVE = new ImageIcon(baseDir + "five.GIF");
  private final static ImageIcon SIX = new ImageIcon(baseDir + "six.GIF");
  private final static ImageIcon SEVEN = new ImageIcon(baseDir + "seven.GIF");
  private final static ImageIcon EIGHT = new ImageIcon(baseDir + "eight.GIF");
  private final static ImageIcon FLAG = new ImageIcon(baseDir + "flag.GIF");
  private final static ImageIcon EXPLODED = new ImageIcon(baseDir + "exploded.GIF");
  private final static ImageIcon NOTMINE = new ImageIcon(baseDir + "notmine.GIF");
  private final static ImageIcon YELLOWFACE = new ImageIcon(baseDir + "yellowface.GIF");
  private final static ImageIcon DEADFACE = new ImageIcon(baseDir + "deadface.GIF");
  private final static ImageIcon SUNGLASSFACE = new ImageIcon(baseDir + "sunglassface.GIF");

  private int rows, cols, mines;

  private boolean gameOver;
  private MineSweeper model;
  private Container cp;
  private JButton[][] squares;
  private JPanel buttons;
  private JPanel scoreTray;
  private Timer time;
  private JTextField clock;
  private JTextField flagsLeft;
  private JButton yellowFace;

  public MineSweeperGUI() {
    // defaults
    rows = 16;
    cols = 16;
    mines = 40;

    // Frame options

    this.setTitle("A-MineSweeper");
    setLocation(100, 100);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    cp = this.getContentPane();
    setSize(cols * SQUARESIZE, rows * SQUARESIZE + 80);

    // Score file
    FileWriter fw;
    try {
      fw = new FileWriter("./scores.txt");
      fw.write("999 Not you\n999 Not you\n 999 Not you\n");
      fw.flush();
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // make scoreTray
    scoreTray = new JPanel();
    clock = new JTextField("" + 0);
    flagsLeft = new JTextField("" + mines);
    flagsLeft.setEditable(false);
    flagsLeft.setColumns(6);
    clock.setEditable(false);
    clock.setColumns(3);
    yellowFace = new JButton(YELLOWFACE);
    yellowFace.addActionListener(new NewGameListener());
    scoreTray.add(clock);
    scoreTray.add(yellowFace);
    scoreTray.add(flagsLeft);

    JPanel north = new JPanel();
    // make menu
    JMenuBar menu = new JMenuBar();
    JMenu game = new JMenu("Game");
    game.setMnemonic('g');
    JMenuItem newGame = new JMenuItem("New");
    newGame.setMnemonic('n');
    newGame.addActionListener(new NewGameListener());
    JMenuItem options = new JMenuItem("Options");
    options.setMnemonic('o');
    options.addActionListener(new OptionsListener());
    game.add(newGame);
    game.add(options);
    menu.add(game);
    JMenu help = new JMenu("Help");
    help.setMnemonic('h');
    JMenuItem about = new JMenuItem("About");
    about.setMnemonic('a');
    about.addActionListener(new AboutListener());
    JMenuItem how2 = new JMenuItem("How-To");
    how2.setMnemonic('t');
    how2.addActionListener(new HowtoListener());
    help.add(how2);
    help.add(about);
    menu.add(help);
    JMenuItem highs = new JMenuItem("High Scores");
    highs.setMnemonic('s');
    highs.addActionListener(new ScoreListener());
    menu.add(highs);

    north.setSize(20, cols * SQUARESIZE);
    north.setLayout(new BoxLayout(north, 1));
    menu.setSize(10, cols * SQUARESIZE);
    north.add(menu);
    scoreTray.setSize(10, cols * SQUARESIZE);
    north.add(scoreTray);
    cp.add(north, BorderLayout.NORTH);

    // Timer to tick every 1000ms (1 second)
    time = new Timer(1000, new TimerListener());

    // initialize button[][] and model
    resetSizeAndModel();
  }

  private class HowtoListener implements ActionListener {

    public void actionPerformed(ActionEvent arg0) {
      JOptionPane
          .showMessageDialog(
              cp,
              "The player assumes the role of a guy with a mine detector tasked\n"
                  + " to find the land mines in the field. The field is a two-dimensional board of squares\n"
                  + "containing either mines, or the number of mines next to the square. The objective of the\n"
                  + "game is to clear every square that doesn't contain a mine using logical reasoning based\n"
                  + "on the number squares you have cleared. Initially, none of the board is cleared and the\n"
                  + "contents of every square are not visible to the player. To clear a square, the player clicks\n"
                  + "on it. If it's a mine, the player has lost (since touching mines is generally not productive.\n)"
                  + "If the square has a number of mines adjacent to it greater than 0, the number is revealed.\n"
                  + "If the square has zero mines next to it, the game clears all the nearby squares, since all\n"
                  + "squares next to a '0' would be safe. Here's where things get tricky: if one of the squares\n"
                  + "next to the '0' is also a '0' (has no mines next to it.) then everything around it gets\n"
                  + "cleared as well, and the same goes for any newly found '0's, and so on until the \"blank\"\n"
                  + "area is completely cleared. Of course, you know all that since you wrote the code ;-)");
    }
  }

  private class AboutListener implements ActionListener {

    public void actionPerformed(ActionEvent arg0) {
      JOptionPane
          .showMessageDialog(
              cp,
              "A-MinesSeeper v1.0\nTo be used in C Sc 227 as a testing GUI\nAuthor: Alexander \"Apollon\" Jerabek");
    }
  }

  private class NewGameListener implements ActionListener {

    public void actionPerformed(ActionEvent arg0) {
      resetSizeAndModel();
    }
  }

  private class TimerListener implements ActionListener {

    public void actionPerformed(ActionEvent arg0) {
      if (clock.getText().compareTo("999") < 0)
        clock.setText((Integer.parseInt(clock.getText()) + 1) + "");
    }
  }

  private class OptionsListener implements ActionListener {

    public void actionPerformed(ActionEvent arg0) {
      @SuppressWarnings("unused")
      OptionsFrame opts = new OptionsFrame();
    }

    private class OptionsFrame extends JFrame {

      private static final long serialVersionUID = -2209006387279353918L;

      private JTextField r;

      private JTextField c;

      private JTextField m;

      JButton easy, medium, hard;

      public OptionsFrame() {
        setLocation(100, 100);
        // setResizable(false);
        setSize(330, 140);

        JPanel txt = new JPanel();
        txt.setLayout(new GridLayout(3, 2));
        JTextField rs = new JTextField("Rows: ");
        rs.setEditable(false);
        txt.add(rs);
        r = new JTextField("" + rows);
        txt.add(r);
        JTextField cs = new JTextField("Columns: ");
        cs.setEditable(false);
        txt.add(cs);
        c = new JTextField("" + cols);
        txt.add(c);
        JTextField ms = new JTextField("Mines: ");
        ms.setEditable(false);
        txt.add(ms);
        m = new JTextField("" + mines);
        txt.add(m);
        getContentPane().add(txt, BorderLayout.NORTH);

        // preset games options
        easy = new JButton("Beginner");
        medium = new JButton("Intermediate");
        hard = new JButton("Expert");
        easy.addActionListener(new ModeListener());
        medium.addActionListener(new ModeListener());
        hard.addActionListener(new ModeListener());
        JPanel modes = new JPanel();
        modes.setLayout(new GridLayout(1, 3));
        modes.add(easy);
        modes.add(medium);
        modes.add(hard);

        getContentPane().add(modes, BorderLayout.CENTER);
        JButton okay = new JButton("OK");
        okay.addActionListener(new OkayListener(this));
        getContentPane().add(okay, BorderLayout.SOUTH);

        setVisible(true);
      }

      private class ModeListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
          if (arg0.getSource().equals(easy)) {
            r.setText("" + 9);
            c.setText("" + 9);
            m.setText("" + 10);
          } else if (arg0.getSource().equals(medium)) {
            r.setText("" + 16);
            c.setText("" + 16);
            m.setText("" + 40);
          } else if (arg0.getSource().equals(hard)) {
            r.setText("" + 16);
            c.setText("" + 30);
            m.setText("" + 99);
          }
        }
      }

      private class OkayListener implements ActionListener {

        private JFrame window;

        public OkayListener(JFrame window) {
          this.window = window;
        }

        public void actionPerformed(ActionEvent arg0) {
          try {
            int tmp = Integer.parseInt(r.getText());
            int tmp2 = Integer.parseInt(c.getText());
            int tmp3 = Integer.parseInt(m.getText());
            if (tmp > 0 && tmp2 > 0 && tmp3 < tmp * tmp2) {
              rows = tmp;
              cols = tmp2;
              mines = tmp3;
              resetSizeAndModel();
            } else
              throw new NumberFormatException();
            window.dispose();
          } catch (NumberFormatException nfe) {
            JOptionPane
                .showMessageDialog(window,
                    "Please enter valid numbers (positive rows/columns and less than rows * columns mines");
          }
        }
      }
    }
  }

  private class MSMouseListener implements MouseListener {

    private int mrow, mcol;

    public MSMouseListener(int mrow, int mcol) {
      this.mrow = mrow;
      this.mcol = mcol;
    }

    public void mouseClicked(MouseEvent e) {
      if (gameOver)
        return;

      if (!time.isRunning())
        time.start();

      if (!gameOver && SwingUtilities.isRightMouseButton(e)) {// right-click
        if (!model.isVisible(mrow, mcol)) {
          model.toggleFlagged(mrow, mcol);
          if (model.isFlagged(mrow, mcol)) {
            squares[mrow][mcol].setIcon(FLAG);
            flagsLeft.setText("" + ((Integer.parseInt(flagsLeft.getText())) - 1));
          } else {
            squares[mrow][mcol].setIcon(UNCLICKED);
            flagsLeft.setText("" + ((Integer.parseInt(flagsLeft.getText())) + 1));
          }
        }
      } else {
        // handle left click
        handleLeftClick();
      }
    }

    private void handleLeftClick() {
      if (!model.isFlagged(mrow, mcol)) {
        model.click(mrow, mcol);
        if (model.lost()) {
          time.stop();
          deadrebutton();
          gameOver = true;
          yellowFace.setIcon(DEADFACE);
          JOptionPane.showMessageDialog(cp, "You have exploded... gg");
        } else
          saferebutton();
        if (model.won()) {
          time.stop();
          gameOver = true;
          yellowFace.setIcon(SUNGLASSFACE);
          if (rows == 9 && cols == 9 && mines == 10)// easy
          {
            if (getScore(0) > Integer.parseInt(clock.getText())) {
              String playerName = JOptionPane.showInputDialog("You won! Enter your name: ");
              writeScore(0, playerName);
            } else
              JOptionPane.showMessageDialog(cp, "You won! But your score wasn't that good...");
          } else if (rows == 16 && cols == 16 && mines == 40)// medium
          {
            if (getScore(1) > Integer.parseInt(clock.getText())) {
              String playerName = JOptionPane.showInputDialog("You won! Enter your name: ");
              writeScore(1, playerName);
            } else
              JOptionPane.showMessageDialog(cp, "You won! But your score wasn't that good...");
          } else if (rows == 16 && cols == 30 && mines == 99)// hard
          {
            if (getScore(2) > Integer.parseInt(clock.getText())) {
              String playerName = JOptionPane.showInputDialog("You won! Enter your name: ");
              writeScore(2, playerName);
            } else
              JOptionPane.showMessageDialog(cp, "You won! But your score wasn't that good...");
          } else
            JOptionPane.showMessageDialog(cp, "You won! (Custom games don't get high scores :-P");
        }
      }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
  }

  private class ScoreListener implements ActionListener {

    public void actionPerformed(ActionEvent arg0) {
      Scanner scores = null;
      try {
        scores = new Scanner(new File("./scores.txt"));
      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(cp, "Stop messing around with the scores file! Delete it!");
      }
      String dis;
      dis = "" + scores.nextInt() + " ";
      dis += scores.nextLine() + "\n";
      dis += scores.nextInt() + " ";
      dis += scores.nextLine() + "\n";
      dis += scores.nextInt() + " ";
      dis += scores.nextLine();
      JOptionPane.showMessageDialog(cp, dis);
    }
  }

  private int getScore(int i) {
    Scanner scores = null;
    try {
      scores = new Scanner(new File("./scores.txt"));
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(cp, "Stop messing around with the scores file! Delete it!");
    }
    String[] parsed = new String[6];
    try {
      parsed[0] = "" + scores.nextInt();
      parsed[1] = scores.nextLine();
      parsed[2] = "" + scores.nextInt();
      parsed[3] = scores.nextLine();
      parsed[4] = "" + scores.nextInt();
      parsed[5] = scores.nextLine();
    } catch (Exception ioe) {
      JOptionPane.showMessageDialog(cp, "Stop messing around with the scores file! Delete it!");
      return -1;
    }
    return Integer.parseInt(parsed[(i + i)]);
  }

  private void writeScore(int i, String name) {
    FileWriter fw = null;
    Scanner scores = null;
    try {
      scores = new Scanner(new File("./scores.txt"));
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(cp, "Stop messing around with the scores file! Delete it!");
    }
    String[] parsed = new String[6];
    try {
      parsed[0] = "" + scores.nextInt();
      parsed[1] = scores.nextLine();
      parsed[2] = "" + scores.nextInt();
      parsed[3] = scores.nextLine();
      parsed[4] = "" + scores.nextInt();
      parsed[5] = scores.nextLine();
    } catch (Exception ioe) {
      JOptionPane.showMessageDialog(cp, "Stop messing around with the scores file! Delete it!");
      return;
    }
    try {
      fw = new FileWriter("./scores.txt");
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    i = i + i;
    parsed[i] = clock.getText();
    parsed[i + 1] = name;
    try {
      fw.write(parsed[0] + " ");
      fw.append(parsed[1] + "\n");
      fw.append(parsed[2] + " ");
      fw.append(parsed[3] + "\n");
      fw.append(parsed[4] + " ");
      fw.append(parsed[5] + "\n");
      fw.flush();
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void deadrebutton() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (model.isMine(i, j) && !model.isFlagged(i, j)) {
          if (model.isVisible(i, j))
            squares[i][j].setIcon(EXPLODED);
          else
            squares[i][j].setIcon(MINE);
        } else if (!model.isMine(i, j) && model.isFlagged(i, j)) {
          squares[i][j].setIcon(NOTMINE);
        }
      }
    }
  }

  private void saferebutton() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (model.isVisible(i, j) && !model.isFlagged(i, j) && !model.isMine(i, j)) {
          int num = model.getAdjacentMines(i, j);
          switch (num) {
          case 0:
            squares[i][j].setIcon(BLANK);
            break;
          case 1:
            squares[i][j].setIcon(ONE);
            break;
          case 2:
            squares[i][j].setIcon(TWO);
            break;
          case 3:
            squares[i][j].setIcon(THREE);
            break;
          case 4:
            squares[i][j].setIcon(FOUR);
            break;
          case 5:
            squares[i][j].setIcon(FIVE);
            break;
          case 6:
            squares[i][j].setIcon(SIX);
            break;
          case 7:
            squares[i][j].setIcon(SEVEN);
            break;
          case 8:
            squares[i][j].setIcon(EIGHT);
            break;
          }
        }
      }
    }
  }

  private void resetSizeAndModel() {
    gameOver = false;
    flagsLeft.setText("" + mines);
    if (buttons != null)
      this.remove(buttons);
    buttons = new JPanel();
    buttons.setLocation(0, 50);
    buttons.setPreferredSize(new Dimension(cols * SQUARESIZE, rows * SQUARESIZE));
    model = new MineSweeper(rows, cols, mines);
    squares = new JButton[rows][cols];
    buttons.setLayout(new GridLayout(rows, cols));
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        squares[i][j] = new JButton(UNCLICKED);
        squares[i][j].addMouseListener(new MSMouseListener(i, j));
        buttons.add(squares[i][j]);
      }
    }
    cp.add(buttons, BorderLayout.CENTER);
    this.setResizable(true);
    setSize(cols * SQUARESIZE, rows * SQUARESIZE + 80);
    this.setResizable(false);
    clock.setText(0 + "");
    yellowFace.setIcon(YELLOWFACE);
    validate();
  }
}