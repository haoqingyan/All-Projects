import static org.junit.Assert.*;

import org.junit.Test;

public class RecursionFunTest {

	RecursionFun rf = new RecursionFun();

	@Test
	public void test() {
		int[] x = { 1, 5, 7, 2, 3, 4 };
		int[] a = { 2, 4, 6 };
		assertEquals(1, rf.factorial(0));
		assertEquals(1, rf.factorial(1));
		assertEquals(2, rf.factorial(2));
		assertEquals(6, rf.factorial(3));
		assertEquals(1, rf.GCD(7, 5));
		assertEquals(4, rf.GCD(8, 4));
		assertEquals(7, rf.GCD(14, 21));
		assertEquals(1.0, rf.addReciprocals(1), 0.001);
		assertEquals(1.5, rf.addReciprocals(2), 0.001);
		assertEquals(1, rf.fibonacci(1));
		assertEquals(1, rf.fibonacci(2));
		assertEquals(2, rf.fibonacci(3));
		assertEquals(8, rf.fibonacci(6));
		assertEquals("hel*lo", rf.pairStar("hello"));
		assertTrue(rf.nestParen("((()))"));
		assertFalse(rf.nestParen("((())"));
		assertEquals(0, rf.sumArray(x, 5, 0));
		assertEquals(22, rf.sumArray(x, 0, 5));
		rf.reverseArray(a);
		assertEquals(6, a[0]);
		assertEquals(4, a[1]);
		assertEquals(2, rf.arrayRange(new int[] { 1, 2, 3 }));
		assertEquals(2, rf.arrayRange(new int[] { 3, 2, 1 }));
		assertEquals(3, rf.arrayRange(new int[] { -3, -2, -5, -4 }));
		String[] strs = { "Aaa", "Ccc", "Ddd", "Fff", "Hhh", "Ttt" };
		assertEquals(0, rf.binarySearch("Aaa", strs));
		assertEquals(2, rf.binarySearch("Ddd", strs));
		assertEquals(-1, rf.binarySearch("Not Here", strs));
		assertEquals("999", rf.intWithCommas(999));
		assertEquals("1,234", rf.intWithCommas(1234));
	}
}