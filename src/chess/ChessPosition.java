package chess;

import boardgame.Position;

/**
 * This is an auxiliary class, responsible for representing a chess position,
 * that is, the coordinate system of a chess game. The class Position that
 * represented a position on the normal board was a matrix position, that is, a
 * position that was accessed by the indices of the dimensions of the array.
 * However, the chess position is accessed firstly by a column, which is
 * represented by a letter (from left to right, from a to h) and secondly by a
 * row, which are represented by numbers (from bottom to top, from 1 to 8 )
 * 
 * @author João Victor
 */
public class ChessPosition {

	private char column;
	private int row;

	/**
	 * generates a position that contains a column and a row (the representation of
	 * the columns is given by the letters a through h. The representation of the
	 * rows is given by integers, but from 8 to 1 in top-down perspective)
	 * 
	 * @param column chess position column
	 * @param row    chess position row
	 */
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	/**
	 * Converts a ChessPosition to a Position. For example, we have that a
	 * ChessPosition a8 (column, row) would correspond to a Position 0, 0 (row,
	 * column). A ChessPosition a7 (column, row) would correspond to a Position 1, 0
	 * (row, column). We then perceive a relationship between the row of the matrix
	 * and the row in chess: The row matrix corresponds to (8 - chess row).
	 * Likewise, there is a relationship between the matrix column and the chess
	 * column. ChessPosition b8 corresponds to Position 0, 1. So we conclude that by
	 * subtracting the unicode code of the ChessPosition Column from the unicode
	 * code of the character a, we will obtain the index of the column of the
	 * matrix.
	 * 
	 * @return a ChessPosition converted to Position
	 */
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}

	/**
	 * Converts a Position to ChessPosition. It will perform the exact opposite
	 * procedure from toPosition. The column ChessPosition will be determined by
	 * ('a' + column Position) and the row ChessPosition will be determined by (8 -
	 * row Position)
	 * 
	 * @param position board position
	 * @return a Position converted to ChessPosition
	 */
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
	}

	/**
	 * Outputs a string that shows the position of a piece in the chess coordinate
	 * system
	 * 
	 * @return a string that shows the position of a piece in the chess coordinate
	 *         system. Example: "a1", "h8"
	 */
	@Override
	public String toString() {
		return "" + column + row;
	}

}
