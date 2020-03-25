// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

package assign1;

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int minRow = Integer.MAX_VALUE, maxRow = -1;
		int minCol = Integer.MAX_VALUE, maxCol = -1;
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				if(grid[row][col] == ch){
					if(row < minRow) minRow = row;
					if(col < minCol) minCol = col;
					if(row > maxRow) maxRow = row;
					if(col > maxCol) maxCol = col;
				}
			}
		}
		if(maxRow == -1 || maxCol == -1) return 0;
		return (maxRow - minRow + 1) * (maxCol - minCol + 1);
	}



	//Checks if there is a cross of length 1 around passed coordinates
	private boolean checkForCross(int row, int col, char curChar, int deviation){
		return (grid[row][col - deviation + 1] == curChar &&
				grid[row][col + deviation - 1] == curChar &&
				grid[row - deviation + 1][col] == curChar &&
				grid[row + deviation - 1][col] == curChar);
	}

	//Checks if there is at least one 'curChar' around the coordinates
	private boolean containsChar(int row, int col, char curChar, int deviation){
		return (grid[row][col - deviation + 1] == curChar ||
				grid[row][col + deviation - 1] == curChar ||
				grid[row - deviation + 1][col] == curChar ||
				grid[row + deviation - 1][col] == curChar);
	}

	//Checks if there is a "+" on the passed point
	private boolean checkSinglePoint(int row, int col, char ch){
		int curDeviation  = 2;
		boolean leftBoundCheck, rightBoundCheck, upBoundCheck, downBoundCheck;
		while(true){
			upBoundCheck = (row - curDeviation < 0);
			downBoundCheck = (row + curDeviation == grid.length);
			rightBoundCheck = (col + curDeviation == grid[0].length);
			leftBoundCheck = (col - curDeviation < 0);

			if(!checkForCross(row, col, ch, curDeviation)) {
				return (!containsChar(row, col, ch, curDeviation));
			} else if(leftBoundCheck || rightBoundCheck || upBoundCheck || downBoundCheck){
				return ((leftBoundCheck || grid[row][col - curDeviation] != ch)
						&& (rightBoundCheck || grid[row][col + curDeviation] != ch)
						&& (upBoundCheck || grid[row - curDeviation][col] != ch)
						&& (downBoundCheck || grid[row + curDeviation][col] != ch));
			}
			curDeviation++;
		}
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int result = 0;
		char curChar;
		for(int row = 1; row < grid.length - 1; row++){
			for(int col = 1; col < grid[0].length - 1; col++){
				curChar = grid[row][col];
				if(checkForCross(row, col, curChar, 0) &&
						checkSinglePoint(row, col, curChar)) result++;
			}
		}
		return result;
	}
	
}
