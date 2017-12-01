import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeTest {
	@Test
	public void testConstructorAndGetters() {
		GameOfLife society = new GameOfLife(5, 8);
		assertEquals(5, society.numberOfRows());
		assertEquals(8, society.numberOfColumns());
		for (int r = 0; r < society.numberOfRows(); r++)
			for (int c = 0; c < society.numberOfColumns(); c++)
				assertFalse(society.cellAt(r, c));
	}

	@Test
	public void testGrowCellAtAndCellAt() {
		GameOfLife society = new GameOfLife(4, 4);
		society.growCellAt(1, 1);
		society.growCellAt(2, 2);
		society.growCellAt(3, 3);

		assertTrue(society.cellAt(1, 1));
		assertTrue(society.cellAt(2, 2));
		assertTrue(society.cellAt(3, 3));
	}

	@Test
	public void testNeighborsWrapping() {
		GameOfLife society = new GameOfLife(10, 16);
		society.growCellAt(3, 3);
		society.growCellAt(3, 4);
		society.growCellAt(3, 5);
		assertEquals(0, society.neighborCount(2, 1));
		assertEquals(1, society.neighborCount(2, 2));
		assertEquals(2, society.neighborCount(2, 3));
		assertEquals(3, society.neighborCount(2, 4));
	}

	@Test
	public void testToString() {
		GameOfLife society = new GameOfLife(3, 3);
		society.growCellAt(1, 0);
		society.growCellAt(1, 1);
		society.growCellAt(1, 2);
		society.update();
		String resultString = "OOO"+"\nOOO"+"\nOOO"+"\n";
		assertEquals(resultString, society.toString());
	}

	@Test
	public void testMainMethod() {
		GameOfLife society = new GameOfLife(3, 3);
		society.growCellAt(1, 0);
		society.growCellAt(1, 1);
		society.growCellAt(1, 2);
		assertTrue(society.cellAt(1, 0));
		assertTrue(society.cellAt(1, 1));
		assertTrue(society.cellAt(1, 2));
		assertFalse(society.cellAt(0, 0));
		assertFalse(society.cellAt(2, 1));
		assertFalse(society.cellAt(2, 0));
		society.update();
		assertTrue(society.cellAt(1, 0));
		assertTrue(society.cellAt(1, 1));
		assertTrue(society.cellAt(1, 2));
		assertTrue(society.cellAt(0, 0));
		assertTrue(society.cellAt(2, 1));
		assertTrue(society.cellAt(2, 0));
	}

}
