// Board.java
package tetris;

import java.util.Arrays;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;

	//max column height
	private int maxHeight;
	private int xMaxHeight;

	//width of every row
	private int[] widths;
	private int[] xWidths;
	//height of every row
	private int[] heights;
	private int[] xHeights;
	private boolean[][] grid;
	private boolean[][] xGrid;
	private boolean DEBUG = true;
	boolean committed;
	
	
	// Here a few trivial methods are provided:
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		xGrid = new boolean[width][height];
		committed = true;
		maxHeight = 0;
		widths = new int[height];
		heights = new int[width];
		xWidths = new int[height];
		xHeights = new int[width];

	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {
		return maxHeight;
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		if (!DEBUG) return;
		int curMaxHeight = 0;
		int[] curWidths = new int[getHeight()];
		int[] curHeights = new int[getWidth()];
		for (int j = 0; j < getWidth(); j++){
			for(int k = 0; k < getHeight(); k++){
				if(grid[j][k]){
					curWidths[k]++;
					curHeights[j] = k + 1;

				}
			}
			if(curHeights[j] > curMaxHeight) curMaxHeight = curHeights[j];
		}
		if(curMaxHeight != maxHeight)
			throw new RuntimeException("maxHeight incorrect");
		if(!Arrays.equals(curWidths, widths))
			throw new RuntimeException("widths incorrect");
		if(!Arrays.equals(curHeights, heights))
			throw new RuntimeException("heights incorrect");
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		if(x + piece.getWidth() - 1 >= getWidth() || x < 0) return PLACE_OUT_BOUNDS;
		int[] skirt = piece.getSkirt();
		int max = 0;
		for(int i = 0; i < skirt.length; i++) {
			max = Math.max(max, Math.max(0, heights[x + i] - skirt[i]));
		}
		if(max + piece.getHeight() - 1 >= getHeight()) return PLACE_OUT_BOUNDS;
		return max;
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		return heights[x];
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		 return widths[y];
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		if(x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) return true;
		return grid[x][y];
	}
	
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
			
		int result = dropHeight(piece, x);
		if(y + piece.getHeight() - 1 >= getHeight() || x < 0 || y < 0
				|| x + piece.getWidth() - 1 >= getWidth()) return PLACE_OUT_BOUNDS;
		if(result > y) return PLACE_BAD;

		for(int i = 0; i < getWidth(); i++){
			System.arraycopy(grid[i], 0, xGrid[i], 0, grid[i].length);
		}
		System.arraycopy(heights, 0, xHeights, 0, heights.length);
		System.arraycopy(widths, 0, xWidths, 0, widths.length);

		xMaxHeight = getMaxHeight();
		committed = false;

		TPoint[] body = piece.getBody();
		for(int i = 0; i < body.length; i++){

			grid[x + body[i].x][y + body[i].y] = true;
			widths[y + body[i].y]++;
			if(heights[x + body[i].x] < y + body[i].y + 1) heights[x + body[i].x] = y + body[i].y + 1;
			if(y + body[i].y + 1 > maxHeight) maxHeight = y + body[i].y + 1;
		}
		return PLACE_OK;
	}
	
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
		if(committed){
			committed = false;
			return 0;
		}
		int rowsCleared = 0;
		int i = 0;
		while(i + rowsCleared < getMaxHeight()){
			if(getRowWidth(i + rowsCleared) == getWidth()){
				rowsCleared++;
				continue;
			}
			if(rowsCleared != 0){
				for(int j = 0; j < getWidth(); j++){
					grid[j][i] = grid[j][i + rowsCleared];
				}
				widths[i] = widths[i + rowsCleared];
				widths[i + rowsCleared] = 0;
			}
			i++;
		}
		for (int j = 0; j < rowsCleared; j++){
			widths[getMaxHeight() - 1 - j] = 0;
			for(int k = 0; k < getWidth(); k++){
				grid[k][getMaxHeight() - j - 1] = false;
			}
		}
		maxHeight -= rowsCleared;
		for(int j = 0; j < getWidth(); j++){
			heights[j] -= rowsCleared;
			while(heights[j] > 0 && !grid[j][heights[j] - 1]) heights[j]--;
		}
		committed = false;
		sanityCheck();
		return rowsCleared;
	}



	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		if(committed) return;
		for(int i = 0; i < getWidth(); i++){
			System.arraycopy(xGrid[i], 0, grid[i], 0, grid[i].length);
		}
		System.arraycopy(xHeights, 0, heights, 0, heights.length);
		System.arraycopy(xWidths, 0, widths, 0, widths.length);

		maxHeight = xMaxHeight;
		commit();
	}
	
	
	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		committed = true;
	}


	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
}


