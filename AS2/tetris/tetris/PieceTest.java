package tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4;
	private Piece s, sRotated, sq, stick;

	@Before
	public void setUp() throws Exception {
		Piece.getPieces();
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		sq = new Piece(Piece.SQUARE_STR);
		stick = new Piece(Piece.STICK_STR);
	}
	
	// Here are some sample tests to get you started
	
	@Test
	public void testSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		
		// Now try with some other piece, made a different way
		Piece l = new Piece(Piece.STICK_STR);
		assertEquals(1, l.getWidth());
		assertEquals(4, l.getHeight());

		Piece s = new Piece(Piece.S1_STR);
        assertEquals(3, s.getWidth());
        assertEquals(2, s.getHeight());

        s = s.computeNextRotation();
        assertEquals(2, s.getWidth());
        assertEquals(3, s.getHeight());

	}


    @Test
    public void testCustomSize() {
        //Test non-existing pieces
        s = new Piece("0 0  1 1  2 2  3 3");
        assertEquals(4, s.getWidth());
        assertEquals(4, s.getHeight());

        s = s.computeNextRotation();
        assertEquals(4, s.getWidth());
        assertEquals(4, s.getHeight());

        //Test non-existing "wrong" pieces
        s = new Piece("1 1  2 1  3 1  4 1");
        assertEquals(5, s.getWidth());
        assertEquals(2, s.getHeight());

        //computes "wrong" width, because the piece is not placed correctly
        s = s.computeNextRotation();
        assertEquals(1, s.getWidth());
        assertEquals(5, s.getHeight());


        //piece with a form of   ``|   (rotated L)
        s = new Piece("0 3  1 3  2 3  3 3  4 3  4 2  4 1  4 0");
        assertEquals(5, s.getWidth());
        assertEquals(4, s.getHeight());

        s = s.computeNextRotation();
        assertEquals(4, s.getWidth());
        assertEquals(5, s.getHeight());

    }

	// Test the skirt returned by a few pieces
	@Test
	public void testSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));

        //Test non-existing pieces
        Piece s = new Piece("0 0  1 1  2 2  3 3");
        assertTrue(Arrays.equals(new int[] {0, 1, 2, 3}, s.getSkirt()));
        s = s.computeNextRotation();
        assertTrue(Arrays.equals(new int[] {3, 2, 1, 0}, s.getSkirt()));
        s = s.computeNextRotation();
        assertTrue(Arrays.equals(new int[] {0, 1, 2, 3}, s.getSkirt()));


        //piece with a form of   ``|   (rotated L)
        s = new Piece("0 3  1 3  2 3  3 3  4 3  4 2  4 1  4 0");
        assertTrue(Arrays.equals(new int[] {3, 3, 3, 3, 0}, s.getSkirt()));
        s = s.computeNextRotation();
        assertTrue(Arrays.equals(new int[] {0, 4, 4, 4}, s.getSkirt()));
	}


	// Test the rotation methods
	@Test
	public void testRotationAndEquals() {
		assertTrue(Piece.getPieces()[Piece.SQUARE]
                .equals(Piece.getPieces()[Piece.SQUARE].fastRotation()));
		assertFalse(Piece.getPieces()[Piece.STICK]
                .equals(Piece.getPieces()[Piece.STICK].fastRotation()));
		assertTrue(Piece.getPieces()[Piece.STICK]
                .equals(Piece.getPieces()[Piece.STICK].fastRotation().fastRotation()));
        assertTrue(Piece.getPieces()[Piece.S1].computeNextRotation()
                .equals(Piece.getPieces()[Piece.S1].fastRotation()));
        assertTrue(Piece.getPieces()[Piece.L2].computeNextRotation().computeNextRotation()
                .equals(Piece.getPieces()[Piece.L2].fastRotation().fastRotation()));
        assertFalse(Piece.getPieces()[Piece.L2].computeNextRotation().computeNextRotation().computeNextRotation()
                .equals(Piece.getPieces()[Piece.L2].fastRotation().fastRotation()));
	}
	
	
}
