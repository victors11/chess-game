package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 * This child class of the class ChessPiece represents a Bishop piece. This
 * class will contain the Bishop movement logic.
 * 
 * @author João Victor
 */
public class Bishop extends ChessPiece {

	/**
	 * creates the bishop chess piece, which is associated with a board and has a
	 * color
	 * 
	 * @param board chessboard
	 * @param color piece color
	 */
	public Bishop(Board board, Color color) {
		super(board, color);
	}

	/**
	 * @return the symbol that will represent the bishop piece
	 */
	@Override
	public String toString() {
		return "B";
	}

	/**
	 * Implements bishop chess piece move logic. This implementation occurs by
	 * marking the matrix positions with true value. These marked positions consist
	 * of the possible moves that can be performed by the bishop. Thus, positions
	 * are marked northwest, northeast, southwest and southeast of the bishop. At
	 * the end of this method, the boolean matrix that will contain the marked
	 * positions will be returned. To mark the positions northwest the bishop, for
	 * example, the following logic will be applied: From the first position
	 * northwest the bishop the analysis will take place. As long as the positions
	 * diagonally northwest the bishop exist within the board when verifying using
	 * {@link boardgame.Board#positionExists(Position)} and do not contain a piece
	 * when checking using {@link boardgame.Board#thereIsAPiece(Position)} those
	 * positions will be marked with true value. When this repetition ends, it will
	 * be tested if there is one more position northwest, and when verifying if that
	 * possible position contains an opponent piece using
	 * {@link chess.ChessPiece#isThereOpponentPiece(Position)}, if these conditions
	 * are met, then this position will also be marked as true. This same logic
	 * occurs for the other diagonals in an adapted way
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// logic to mark positions diagonally northwest the bishop as possible moves 
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions diagonally northeast the bishop as possible moves 
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions diagonally southwest the bishop as possible moves 
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions diagonally southeast the bishop as possible moves 
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
