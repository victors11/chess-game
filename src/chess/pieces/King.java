package chess.pieces;

import boardgame.Board;
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
	 * Implements king chess piece move logic
	 * 
	 * @return a boolean matrix that will indicate the possible moves of the king
	 *         chess piece
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return matrix;
	}
}
