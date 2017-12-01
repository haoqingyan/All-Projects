/**
 * A model for John Conway's Game of Life. The design (methods, parameters,
 * return types) by Rick Mercer
 * 
 * @author haoqing yan
 */
public class GameOfLife {
	/**
	 * Write the constructor so it takes two integer arguments to represent the
	 * number of rows and columns in the game of life. The constructor creates a
	 * society with no cells but space to store rows*cols cells
	 *
	 * @param rows
	 *            The height of the grid that shows the cells.
	 * @param cols
	 *            The width of the grid that shows the cells.
	 */
	private int Row;
	private int Col;
	private String[][] array;

	public GameOfLife(int rows, int cols) {
		Row = rows;
		Col = cols;
		array = new String[Row][Col];
		for (int i = 0; i < Row; i++) {
			for (int j = 0; j < Col; j++) {
				array[i][j] = ".";
			}
		}
	}

	/**
	 * Return the number of rows, which can be indexed from 0..numberOfRows()-1.
	 * 
	 * @return The height of the society.
	 */
	public int numberOfRows() {
		return Row;
	}

	/**
	 * The number of columns, which can be indexed from 0..numberOfColumns()-1.
	 * 
	 * @return The height of the society.
	 */
	public int numberOfColumns() {
		return Col;
	}

	/**
	 * Place a new cell in the society. Precondition: row and col are in range.
	 * 
	 * @param row
	 *            The row to grow the cell.
	 * @param col
	 *            The column to grow the cell.
	 */
	public void growCellAt(int row, int col) {
		array[row][col] = "O";
	}

	/**
	 * Return true if there is a cell at the given row and column. Return false
	 * if there is none at the specified location. * @param row The row to
	 * check. * @param col The column to check. * @return True if there is a
	 * cell at the given row or false if there is not
	 */
	public boolean cellAt(int row, int col) {
		if (array[row][col].equals("O")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return one big string of cells to represent the current state of the
	 * society of cells (see output below where '.' represents an empty space
	 * and 'O' is a live cell. There is no need to test toString. Simply use it
	 * to visually inspect if needed. Here is one sample output from toString:
	 * GameOfLife society = new GameOfLife(4, 14); society.growCellAt(1, 2);
	 * society.growCellAt(2, 3); society.growCellAt(3, 4); *
	 * System.out.println(society.toString());
	 */
	// Output
	// ..............
	// ..O...........
	// ...O..........
	// ....O.........
	/**
	 * @return A textual representation of this society of // cells.
	 */
	@Override
	public String toString() {
		String finalstring = "";

		for (int i = 0; i < Row; i++) {
			for (int j = 0; j < Col; j++) {
				finalstring += array[i][j];
			}
			finalstring += "\n";
		}

		return finalstring;
	}

	// "Under construction"; }
	/**
	 * Count the neighbors around the given location. Use wraparound. A cell in
	 * row 0 has neighbors in the last row if a cell is in the same column, or
	 * the column to the left or right. In this example, cell 0,5 has two
	 * neighbors in the last row, cell 2,8 has four neighbors, cell 2,0 has four
	 * neighbors, cell 1,0 has three neighbors. The cell at 3,8 has 3 neighbors.
	 * * The potential location for a cell at 4,8 would have three neighbors.
	 */
	// .....O..O
	// O........
	// O.......O
	// O.......O
	// ....O.O..
	/**
	 * The return values should always be in the range of 0 through 8.
	 * 
	 * @return The number of neighbors around any cell using wrap around.
	 */
	public int neighborCount(int row, int col) {
		int count = 0;
		for (int i = row - 1; i < row + 2; i++) {
			for (int j = col - 1; j < col + 2; j++) {
				int reRow = i;
				int reCol = j;
				if (i == -1) {
					if (j == -1) {
						reRow = Row - 1;
						reCol = Col - 1;
					} else if (j > Col - 1) {
						reRow = Row - 1;
						reCol = 0;
					} else {
						reRow = Row - 1;
					}
				} else if (i > Row - 1) {
					if (j == -1) {
						reRow = 0;
						reCol = Col - 1;
					} else if (j > Col - 1) {
						reRow = 0;
						reCol = 0;
					} else {
						reRow = 0;
					}
				} else if (j == -1) {
					reCol = Col - 1;
				} else if (j > Col - 1) {
					reCol = 0;
				}
				if (cellAt(reRow, reCol) == true
						&& (reRow != row || reCol != col)) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Update the state to represent the next society. * Typically, some cells
	 * will die off while others are born.
	 */
	public void update() {
		String[][] another = new String[Row][Col];
		for (int i = 0; i < Row; i++) {
			for (int j = 0; j < Col; j++) {
				another[i][j] = ".";
			}
		}
		for (int i = 0; i < Row; i++) {
			for (int j = 0; j < Col; j++) {
				if (array[i][j].equals(".")) {
					if (this.neighborCount(i, j) == 3) {
						another[i][j] = "O";
					}
				} else {
					if (this.neighborCount(i, j) == 3
							|| this.neighborCount(i, j) == 2) {
						another[i][j] = "O";
					}
				}

			}
		}
		// give arrangement back
		for (int i = 0; i < Row; i++) {
			for (int j = 0; j < Col; j++) {
				array[i][j] = another[i][j];
			}
		}
	}
}
