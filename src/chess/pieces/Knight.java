package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 * This child class of the class ChessPiece represents a Knight piece. This
 * class will contain the Knight movement logic.
 * 
 * @author João Victor
 */
public class Knight extends ChessPiece {

	/**
	 * creates the knight chess piece, which is associated with a board and has a
	 * color
	 * 
	 * @param board chessboard
	 * @param color piece color
	 */
	public Knight(Board board, Color color) {
		super(board, color);
	}

	/**
	 * @return the symbol that will represent the knight piece
	 */
	@Override
	public String toString() {
		return "H";  // H of Horseman
	}

	/**
	 * determines whether a knight can move to the position referred to as the
	 * method parameter. This method will check if in a position there is a piece,
	 * and if there is, it will verify if it is an opponent piece through the
	 * {@link chess.ChessPiece#getColor()} method, which will indicate whether this
	 * piece has a different color. These checks occur, because a knight can only
	 * move to a position that doesn't contain any piece, or a position that
	 * contains a opponent, a piece of different color of the knight
	 * 
	 * @param position a position
	 * @return a boolean value that if true indicates that the knight can move to
	 *         the indicated position.
	 */
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	/**
	 * Implements knight chess piece move logic. This implementation occurs by
	 * marking the matrix positions with true value. These marked positions consist
	 * of the possible moves that can be performed by the knight. Thus, positions
	 * are marked above, left, right and below of the knight. At the end of this
	 * method, the boolean matrix that will contain the marked positions will be
	 * returned. In the case of the knights, they can only move two positions
	 * vertically and one position horizontally or two positions horizontally and
	 * one position vertically. Thus, a knight can have a maximum of 8 possible
	 * target positions and each of these positions is verified through
	 * {@link #canMove(Position)} method and if the method indicates that it is
	 * possible to perform the move, the position in question will be marked as true
	 * by the boolean matrix
	 * 
	 * @return a boolean matrix that will indicate the possible moves of the knight
	 *         chess piece
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// 1
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// 2
		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// 3
		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// 4
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// 5
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// 6
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// 7
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// 8
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		return matrix;
	}
}
