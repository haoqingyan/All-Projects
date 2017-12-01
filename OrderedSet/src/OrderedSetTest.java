import static org.junit.Assert.*;

import org.junit.Test;

public class OrderedSetTest {
	@Test
	public void testInsertSizeAndTostringinorder() {
		OrderedSet<String> bst = new OrderedSet<String>();
		assertEquals("", bst.toStringInorder());
		assertTrue(bst.insert("d"));
		assertTrue(bst.insert("b"));
		assertTrue(bst.insert("e"));
		assertFalse(bst.insert("e"));
		assertTrue(bst.insert("a"));
		assertEquals(4, bst.size());
		assertEquals("a b d e", bst.toStringInorder());
		assertTrue(bst.contains("a"));
		assertFalse(bst.contains("z"));
		assertEquals("e", bst.max());
		assertEquals(2, bst.nodesAtLevel(1));
		assertTrue(bst.insert("z"));
		assertEquals("a b d e z", bst.toStringInorder());
	}

	@Test
	public void testSubSet1() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(12);
		bst.insert(75);
		bst.insert(65);
		bst.insert(90);
		assertEquals("12 25 50 65 75 90", bst.toStringInorder());
		OrderedSet<Integer> temp = new OrderedSet<Integer>();
		temp.insert(12);
		temp.insert(35);
		temp.insert(75);
		temp.insert(105);
		assertEquals("12 75", bst.intersection(temp).toStringInorder());
		assertEquals("12 25 35 50 65 75 90 105", bst.union(temp)
				.toStringInorder());
		assertEquals("12 25", bst.subset(1, 49).toStringInorder());
		assertEquals("25 50 65", bst.subset(25, 75).toStringInorder());
		assertEquals("", bst.subset(12, 12).toStringInorder()); // 12 < 12 is
																// false
	}

	@Test
	public void struc() {
		OrderedSet<String> temp1 = new OrderedSet<String>();
		temp1.insert("a");
		temp1.insert("b");
		temp1.insert("c");
		temp1.insert("d");
		OrderedSet<String> temp2 = new OrderedSet<String>();
		temp2.insert("e");
		temp2.insert("f");
		temp2.insert("g");
		temp2.insert("h");
		assertTrue(temp1.sameStructure(temp2));
		temp2.insert("z");
		assertFalse(temp1.sameStructure(temp2));
	}

	@Test
	public void remove() {
		OrderedSet<String> temp1 = new OrderedSet<String>();
		temp1.insert("a");
		temp1.insert("b");
		temp1.insert("c");
		temp1.insert("d");
		OrderedSet<String> temp2 = new OrderedSet<String>();
		temp2.insert("b");
		temp2.insert("c");
		temp2.insert("d");
		temp2.insert("e");
		assertTrue(temp1.remove("a"));
		assertEquals("b c d", temp1.toStringInorder());
		assertEquals("b c d", temp1.intersection(temp2).toStringInorder());
		assertFalse(temp1.remove("f"));
		assertTrue(temp1.insert("f"));
		assertEquals("b c d f", temp1.toStringInorder());
		assertEquals("b c d", temp1.intersection(temp2).toStringInorder());

	}

	@Test
	public void level() {
		OrderedSet<Integer> temp1 = new OrderedSet<Integer>();
		assertEquals(0, temp1.nodesAtLevel(2));
		temp1.max();
		temp1.insert(10);
		temp1.insert(11);
		temp1.insert(9);
		temp1.insert(12);
		assertEquals(0, temp1.nodesAtLevel(3));
	}

	@Test
	public void struc2() {
		OrderedSet<Integer> temp1 = new OrderedSet<Integer>();
		temp1.insert(10);
		temp1.insert(11);
		temp1.insert(9);
		OrderedSet<Integer> temp2 = new OrderedSet<Integer>();
		temp2.insert(10);
		temp2.insert(9);
		temp2.insert(8);
		assertFalse(temp1.sameStructure(temp2));
		assertFalse(temp2.sameStructure(temp1));

	}

	@Test
	public void testSubSet() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(12);
		bst.insert(75);
		bst.insert(65);
		bst.insert(90);
		assertEquals("12 25 50 65 75 90", bst.toStringInorder());
		assertEquals("12 25", bst.subset(1, 49).toStringInorder());
		assertEquals("25 50 65", bst.subset(25, 75).toStringInorder());
		assertEquals("", bst.subset(12, 12).toStringInorder()); // 12 < 12 is
																// false }
		OrderedSet<Integer> A = new OrderedSet<Integer>();
		A.insert(1);
		OrderedSet<Integer> B = new OrderedSet<Integer>();
		B.insert(1);
		assertEquals("1", A.intersection(B).toStringInorder());
		assertEquals("1", A.union(B).toStringInorder());
		assertTrue(A.sameStructure(B));
		A.remove(1);
		assertEquals("", A.toStringInorder());
		assertFalse(A.sameStructure(B));
		B.remove(1);
		assertTrue(A.sameStructure(B));
		assertEquals("", A.intersection(B).toStringInorder());
		assertEquals("", A.union(B).toStringInorder());
		B.insert(1);
		assertEquals(B.toStringInorder(), A.union(B).toStringInorder());
		B.remove(1);
		A.insert(1);
		assertEquals(A.toStringInorder(), A.union(B).toStringInorder());
		assertTrue(A.remove(1));
		assertFalse(A.remove(10));
	}

	@Test
	public void same() {
		OrderedSet<String> temp1 = new OrderedSet<String>();
		temp1.insert("1");
		temp1.insert("2");
		temp1.insert("3");
		temp1.insert("4");
		OrderedSet<String> temp2 = new OrderedSet<String>();
		temp2.insert("5");
		temp2.insert("6");
		temp2.insert("7");
		temp2.insert("8");
		assertTrue(temp1.sameStructure(temp2));
		temp2.insert("9");
		assertFalse(temp1.sameStructure(temp2));
		OrderedSet<Integer> temp3 = new OrderedSet<Integer>();
		temp3.insert(10);
		temp3.insert(11);
		temp3.insert(9);
		OrderedSet<Integer> temp4 = new OrderedSet<Integer>();
		temp4.insert(10);
		temp4.insert(9);
		temp4.insert(8);
		assertFalse(temp3.sameStructure(temp4));
		assertFalse(temp4.sameStructure(temp3));

	}
}
