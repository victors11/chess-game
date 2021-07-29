package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

/**
 * is the child class of the Piece class and will contain more properties common
 * to all chess piece types. The child classes of this class will be precisely
 * the ones that will represent each type of game piece.
 * 
 * @author João Victor
 */
public abstract class ChessPiece extends Piece {

	private Color color;
	private int moveCount;

	/**
	 * creates a chess piece, which is associated with a board and has a color
	 * 
	 * @param board chessboard
	 * @param color piece color
	 */
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	protected void increaseMoveCount() {
		moveCount++;
	}
	
	protected void decreaseMoveCount() {
		moveCount--;
	}

	/**
	 * returns the position of the chess piece in chess coordinate format. Thus, it
	 * converts the position type through the static method
	 * {@link chess.ChessPosition#fromPosition(Position)}
	 * 
	 * @return a chess coordinate position
	 */
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	/**
	 * This method is intended to check if there is an opposing piece in a desired
	 * position. For this, a chess piece contained in the desired position will be
	 * returned by upcasting the return of the
	 * {@link boardgame.Board#piece(Position)} method. Thus, if the returned piece
	 * is not null (which indicates that there is a chess piece in the referred
	 * position) and the returned piece has a different color then the method will
	 * return true, if one of the conditions is false then false will be returned
	 * 
	 * @param position a position
	 * @return A Boolean value that will indicate whether or not there is an
	 *         opposing piece in the position passed as a parameter. The Boolean
	 *         value will be true if there is, and false if there isn't.
	 */
	public boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}

}
