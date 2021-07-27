package boardgame;

/**
 * this is an auxiliary class, responsible for representing the position of a
 * piece on the board.
 * 
 * @author João Victor
 */

public class Position {

	private int row;
	private int column;

	/**
	 * generates a position that contains a row and a column (the representation of
	 * rows and columns are given by integers)
	 * 
	 * @param row    position row
	 * @param column position column
	 */

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * updates the values (row, column) of a position
	 * 
	 * @param row    a row
	 * @param column a column
	 */

	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Outputs a string showing the attributes of a position in row, column format.
	 * 
	 * @return a string that shows a position's row and column
	 */

	@Override
	public String toString() {
		return row + ", " + column;
	}
}
