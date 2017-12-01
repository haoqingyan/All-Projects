package runGame;
import java.awt.Point;


import java.util.Observable;

import Trainer.Trainer;

public class Game extends Observable implements java.io.Serializable{

	int stepAmount = 0;
	public int grassStepAmount = 0;
	public Trainer trainer;

	int currentRow, currentCol, oldRow, oldCol;

  public Game(int row, int column) {
	trainer = new Trainer();
    currentRow = row;
    currentCol = column;
    oldRow = row;
    oldCol = column;
  } 
  public Game(int row, int column, Trainer trainer){
	  this.trainer = trainer;
	  currentRow = row;
	  currentCol = column;
	  oldRow = row;
	  oldCol = column;
  }

  public void movePlayer(Direction dir) {
    oldRow = currentRow;
    oldCol = currentCol;
    if (dir == Direction.NORTH)
      currentRow--;
    if (dir == Direction.EAST)
      currentCol++;
    if (dir == Direction.SOUTH)
      currentRow++;
    if (dir == Direction.WEST)
      currentCol--;
    stepAmount = stepAmount + 1;
    setChanged();
    notifyObservers(dir);
  }

  public int getCurrentRow() {
    return currentRow;
  }

  public int getCurrentColumn() {
    return currentCol;
  }

  public Point getOldPoint() {
	  return new Point(oldCol, oldRow);
   }
  
   public Point getPoint() {
     return new Point(currentCol, currentRow);
   }
}