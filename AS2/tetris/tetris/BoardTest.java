package tetris;

import static org.junit.Assert.*;

import org.junit.*;

public class BoardTest {
	Board b;
	Piece pyr1, pyr2, pyr3, pyr4, s, sRotated;

	// This shows how to build things in setUp() to re-use
	// across tests.
	
	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.
	@Before
	public void setUp() throws Exception {
		b = new Board(3, 6);

		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		
		b.place(pyr1, 0, 0);

	}
	
	// Check the basic width/height/max after the one placement
	@Test
	public void test1() {
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));

		assertEquals(3, b.getWidth());
		assertEquals(6, b.getHeight());
	}
	
	// Place sRotated into the board, then check some measures
	@Test
	public void test2() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
	}
	
	// Make  more tests, by putting together longer series of 
	// place, clearRows, undo, place ... checking a few col/row/max
	// numbers that the board looks right after the operations.


	@Test
	public void test3() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		b.commit();
		b.clearRows();
		b.commit();
		result = b.place(Piece.getPieces()[Piece.STICK], 0, 1);
		assertEquals(Board.PLACE_OK, result);

		assertEquals(3, b.clearRows());

		assertEquals(2, b.getColumnHeight(0));
		assertEquals(1, b.getColumnHeight(1));
		assertEquals(0, b.getColumnHeight(2));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(2, b.getMaxHeight());
	}


	@Test
	public void test4() {
		b.commit();

		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		b.commit();

		result = b.place(Piece.getPieces()[Piece.L2].fastRotation().fastRotation(), 0, 2);
		assertEquals(Board.PLACE_OK, result);

		assertEquals(2, b.clearRows());

		assertEquals(2, b.getRowWidth(0));
		assertEquals(2, b.getRowWidth(1));
		assertEquals(2, b.getRowWidth(2));
		assertEquals(0, b.getRowWidth(3));

		assertEquals(3, b.getColumnHeight(0));
		assertEquals(3, b.getColumnHeight(1));
		assertEquals(1, b.getColumnHeight(2));
		assertEquals(3, b.getMaxHeight());

		b.commit();

		result = b.place(Piece.getPieces()[Piece.L1].fastRotation().fastRotation(), 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(2, b.clearRows());

		assertEquals(2, b.getRowWidth(0));
		assertEquals(2, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
		assertEquals(0, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getColumnHeight(2));
		assertEquals(2, b.getMaxHeight());
	}



	@Test
	public void testDrop() {
		b.commit();

		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		b.commit();

		result = b.dropHeight(Piece.getPieces()[Piece.L2].fastRotation().fastRotation(), 0);
		assertEquals(2, result);

		result = b.dropHeight(Piece.getPieces()[Piece.STICK], 0);
		assertEquals(1, result);

		result = b.place(Piece.getPieces()[Piece.STICK], 0, 1);
		assertEquals(Board.PLACE_OK, result);

		b.clearRows();
		b.commit();

		result = b.dropHeight(Piece.getPieces()[Piece.L1].fastRotation().fastRotation(), 1);
		assertEquals(0, result);

		result = b.dropHeight(Piece.getPieces()[Piece.SQUARE], 0);
		assertEquals(2, result);

		result = b.dropHeight(Piece.getPieces()[Piece.STICK].fastRotation(), 2);
		assertEquals(b.PLACE_OUT_BOUNDS, result);
	}


	@Test
	public void testGetGridAndUndo() {
		b.commit();

		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		b.commit();

		result = b.place(Piece.getPieces()[Piece.STICK], 0, 1);
		assertEquals(Board.PLACE_OK, result);

		assertTrue(b.getGrid(0, 2));

		b.clearRows();

		assertTrue(b.getGrid(0, 1));
		assertFalse(b.getGrid(1, 1));
		assertFalse(b.getGrid(2, 0));

		b.undo();

		assertFalse(b.getGrid(0, 1));
		assertTrue(b.getGrid(1, 1));
		assertTrue(b.getGrid(2, 0));

		result = b.place(Piece.getPieces()[Piece.L1].fastRotation().fastRotation(), 1, 3);
		assertEquals(Board.PLACE_OK, result);

		assertTrue(b.getGrid(2, 5));
		assertTrue(b.getGrid(1, 5));
		//out of bounds check
		assertTrue(b.getGrid(3, 3));

		b.undo();

		assertFalse(b.getGrid(2, 5));
		assertFalse(b.getGrid(1, 5));
	}
}
