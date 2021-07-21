package chess;

import boardgame.Board;
import boardgame.Piece;

/**
 * is the child class of the Piece class and will contain more properties common to all chess piece types. 
 * The child classes of this class will be precisely the ones that will represent each type of game piece. 
 * @author João Victor
 */

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;
	
	/**
	 * 
	 * @param board
	 * @param color
	 */

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	
	
}
