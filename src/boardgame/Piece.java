package boardgame;

/**
 * Class Piece is the root of the hierarchy of classes that represent the pieces of a chess game. A piece is
 * associated with a board and a simple matrix position (which is why this attribute of this class is protected)
 * 
 * @author João Victor
 */

public abstract class Piece {
	
	protected Position position;
	private Board board;
	
	/**
	 * Creates a piece that belongs to a board but is not yet placed on that board. That's why the constructor
	 * of a Piece has only a board as a parameter, since the position of a newly created piece is null
	 * @param board chessboard
	 */
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
}
