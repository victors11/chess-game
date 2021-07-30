package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 * This child class of the class ChessPiece represents a Rook piece. This class
 * will contain the Rook movement logic.
 * 
 * @author João Victor
 */
public class Rook extends ChessPiece {

	/**
	 * creates the rook chess piece, which is associated with a board and has a
	 * color
	 * 
	 * @param board chessboard
	 * @param color piece color
	 */
	public Rook(Board board, Color color) {
		super(board, color);
	}

	/**
	 * @return the symbol that will represent the rook piece
	 */
	@Override
	public String toString() {
		return "R";
	}

	/**
	 * Implements rook chess piece move logic. This implementation occurs by marking
	 * the matrix positions with true value. These marked positions consist of the
	 * possible moves that can be performed by the rook. Thus, positions are marked
	 * above, left, right and below of the rook. At the end of this method, the
	 * boolean matrix that will contain the marked positions will be returned. To
	 * mark the positions above the rook, for example, the following logic will be
	 * applied: From the first position above the rook the analysis will take place.
	 * As long as the positions above the rook exist within the board when verifying
	 * using {@link boardgame.Board#positionExists(Position)} and do not contain a
	 * piece when checking using {@link boardgame.Board#thereIsAPiece(Position)}
	 * those positions will be marked with true value. When this repetition ends, it
	 * will be tested if there is one more position above, and when verifying if
	 * that possible position contains an opponent piece using
	 * {@link chess.ChessPiece#isThereOpponentPiece(Position)}, if these conditions
	 * are met, then this position will also be marked as true. This same logic
	 * occurs for the other directions in an adapted way
	 * 
	 * @return a boolean matrix that will indicate the possible moves of the rook
	 *         chess piece
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// logic to mark positions above the rook as possible moves 
		p.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions to the left of the rook as possible moves 
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions to the right of the rook as possible moves 
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// logic to mark positions below the rook as possible moves 
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		return matrix;
	}
}
