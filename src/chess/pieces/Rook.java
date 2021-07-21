package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

/**
 * This child class of the class ChessPiece represents a Rook piece. This class will contain the Rook movement 
 * logic.
 * @author João Victor
 */

public class Rook extends ChessPiece {

	/**
	 * creates the king chess piece, which is associated with a board and has a color
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
}
