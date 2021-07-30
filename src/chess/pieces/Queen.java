package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 * This child class of the class ChessPiece represents a Queen piece. This class
 * will contain the Queen movement logic.
 * 
 * @author João Victor
 */
public class Queen extends ChessPiece {

	/**
	 * creates the queen chess piece, which is associated with a board and has a
	 * color
	 * 
	 * @param board chessboard
	 * @param color piece color
	 */
	public Queen(Board board, Color color) {
		super(board, color);
	}

	/**
	 * @return the symbol that will represent the queen piece
	 */
	@Override
	public String toString() {
		return "Q";
	}

	/**
	 * Implements queen chess piece move logic. This implementation occurs by
	 * marking the matrix positions with true value. These marked positions consist
	 * of the possible moves that can be performed by the queen. Thus, positions are
	 * marked above, left, right, below, northwest, northeast, southwest and
	 * southeast of the queen. In short, the possible moves of a queen, consists of
	 * a mixture of the possible moves of a bishop with the possible moves of a
	 * rook.
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// logic to mark positions above the queen as possible moves
		p.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions to the left of the queen as possible moves
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions to the right of the queen as possible moves
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions below the queen as possible moves
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions diagonally northwest the queen as possible moves
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions diagonally northeast the queen as possible moves
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions diagonally southwest the queen as possible moves
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions diagonally southeast the queen as possible moves
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		return matrix;
	}

}
