package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 * This child class of the class ChessPiece represents a King piece. This class
 * will contain the king movement logic.
 * 
 * @author João Victor
 */
public class King extends ChessPiece {

	/**
	 * creates the king chess piece, which is associated with a board and has a
	 * color
	 * 
	 * @param board chessboard
	 * @param color piece color
	 */
	public King(Board board, Color color) {
		super(board, color);
	}

	/**
	 * @return the symbol that will represent the king piece
	 */
	@Override
	public String toString() {
		return "K";
	}

	/**
	 * determines whether the king can move to the position referred to as the
	 * method parameter. This method will check if in a position there is a piece,
	 * and if there is, it will verify if it is an opponent piece through the
	 * {@link chess.ChessPiece#getColor()} method, which will indicate whether this
	 * piece has a different color. These checks occur, because a king can only move
	 * to a position that doesn't contain any piece, or a position that contains a
	 * opponent, a piece of different color of the king
	 * 
	 * @param position a position
	 * @return a boolean value that if true indicates that the king can move to the
	 *         indicated position.
	 */
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	/**
	 * Implements king chess piece move logic. This implementation occurs by marking
	 * the matrix positions with true value. These marked positions consist of the
	 * possible moves that can be performed by the king. Thus, positions are marked
	 * above, left, right and below of the king. At the end of this method, the
	 * boolean matrix that will contain the marked positions will be returned. In
	 * the case of the king, only positions around that piece are possible target
	 * positions. Then each of the positions around the king are checked by the
	 * {@link #canMove(Position)} method, and if the method indicates that it is
	 * possible to perform the move, the position in question will be marked as true
	 * by the boolean matrix
	 * 
	 * @return a boolean matrix that will indicate the possible moves of the king
	 *         chess piece
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// Above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// northwest
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// northeast
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// south-west
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// south-east
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		return matrix;
	}
}
