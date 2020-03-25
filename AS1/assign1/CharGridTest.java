// Test cases for CharGrid -- a few basic tests are provided.
package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharGridTest {
	
	@Test
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
	}


	@Test
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}

	@Test
	public void testCharArea3() {
		char[][] grid = new char[][] {
				{'a', 'a', 'a'},
				{'a', 'a', 'a'},
				{'a', 'a', 'a'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(9, cg.charArea('a'));
		assertEquals(0, cg.charArea('?'));
	}


	@Test
	public void testCharArea4() {
		char[][] grid = new char[][] {
				{'a', 'b', 'c', 'D'},
				{'b', 'B', 'c', 'D'},
				{'c', 'c', 'c', 'd'},
				{'d', 'd', 'D', 'd'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea('-'));
		assertEquals(1, cg.charArea('a'));
		assertEquals(4, cg.charArea('b'));
		assertEquals(9, cg.charArea('c'));
		assertEquals(8, cg.charArea('d'));
		assertEquals(8, cg.charArea('D'));
	}

	@Test
	public void testCharArea5() {
		char[][] grid = new char[][] {
				{'a', 'b', 'c', 'd', 'z', 'a', 'o', 'd', 'b'},
				{'a', 'b', 'c', 'd', 'z', 'a', 'o', 'd', 'b'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea('\"'));
		assertEquals(2, cg.charArea('o'));
		assertEquals(12, cg.charArea('a'));
		assertEquals(16, cg.charArea('b'));
		assertEquals(10, cg.charArea('d'));
	}

	@Test
	public void testCharArea6() {
		char[][] grid = new char[][] {
				{'a'},
				{'b'},
				{'c'},
				{'d'},
				{'z'},
				{'a'},
				{'o'},
				{'d'},
				{'b'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea('\"'));
		assertEquals(1, cg.charArea('o'));
		assertEquals(6, cg.charArea('a'));
		assertEquals(8, cg.charArea('b'));
		assertEquals(5, cg.charArea('d'));
	}

	@Test
	public void testCharArea7() {
		char[][] grid = new char[][] {
				{'a'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea('o'));
		assertEquals(1, cg.charArea('a'));
	}

	@Test
	public void testCharArea8() {
		char[][] grid = new char[][] {
				{'a', 'o', 'c', 'd'},
				{'7', 'i', 'c', 'd'},
				{'1', 'c', '1', '7'},
				{'d', 'o', 'a', 'd'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(1, cg.charArea('i'));
		assertEquals(3, cg.charArea('1'));
		assertEquals(6, cg.charArea('c'));
		assertEquals(8, cg.charArea('7'));
		assertEquals(12, cg.charArea('a'));
		assertEquals(16, cg.charArea('d'));
	}

	@Test
	public void testCountPlus1() {
		char[][] grid = new char[][] {
				{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', 'p', ' ', ' ', ' ', ' ', 'x', ' '},
				{'p', 'p', 'p', 'p', 'p', ' ', 'x', 'x', 'x'},
				{' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
				{' ', ' ', 'p', ' ', 'y', 'y', 'y', ' ', ' '},
				{'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
				{' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' '},
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(2, cg.countPlus());
	}

	@Test
	public void testCountPlus2() {
		char[][] grid = new char[][] {
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' '},
				{' ', ' ', 'p', ' ', ' ', ' ', 'x', 'x', 'x'},
				{' ', 'p', 'p', 'p', ' ', ' ', ' ', 'x', ' '},
				{' ', ' ', 'p', ' ', ' ', 'y', ' ', 'z', ' '},
				{' ', ' ', ' ', ' ', 'y', 'y', 'y', 'z', ' '},
				{'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
				{' ', ' ', 'x', 'x', ' ', ' ', ' ', 'z', ' '},
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(3, cg.countPlus());
	}

	@Test
	public void testCountPlus3() {
		char[][] grid = new char[][] {
				{' ', ' ', '1', '1', ' ', ' '},
				{' ', ' ', '1', '1', ' ', ' '},
				{'1', '1', '1', '1', '1', '1'},
				{' ', ' ', '1', '1', ' ', ' '},
				{' ', ' ', '1', '1', ' ', ' '}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.countPlus());
	}

	@Test
	public void testCountPlus4() {
		char[][] grid = new char[][] {
				{' ', ' ', '1', '1', '1', ' '},
				{' ', ' ', '1', '1', '1', ' '},
				{'1', '1', '1', '1', '1', ' '},
				{' ', ' ', '1', '1', '1', ' '},
				{' ', ' ', '1', '1', '1', ' '}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(1, cg.countPlus());
	}

	@Test
	public void testCountPlus5() {
		char[][] grid = new char[][] {
				{'A', 'A', 'A', 'A', 'A'},
				{'A', 'A', 'A', 'A', 'A'},
				{'A', 'A', 'A', 'A', 'A'},
				{'A', 'A', 'A', 'A', 'A'},
				{'A', 'A', 'A', 'A', 'A'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(1, cg.countPlus());
	}

	@Test
	public void testCountPlus6() {
		char[][] grid = new char[][] {
				{'0', 'A', '0'},
				{'A', 'A', 'A'},
				{'0', 'A', '0'},
				{'0', '0', '0'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(1, cg.countPlus());
	}

	@Test
	public void testCountPlus7() {
		char[][] grid = new char[][] {
				{' ', '1', ' ', ' ', '5', ' '},
				{'1', '1', '1', '2', '5', '5'},
				{' ', '1', '2', '2', '2', '5'},
				{' ', '3', ' ', '2', '4', ' '},
				{'3', '3', '3', '4', '4', '4'},
				{' ', '3', ' ', ' ', '4', ' '}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(4, cg.countPlus());
	}


	
}
