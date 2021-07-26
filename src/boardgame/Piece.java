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
	
	/**
	 * determines the possible movements of a piece which will consist of an array of boolean values, where each
	 * boolean value will contain a possible move. In other words, the matrix will have a true value for the 
	 * positions that a piece can move, and a false value for all the other positions in the matrix. This method
	 * will be abstract, as the possible moves of a generic piece are not known, only of a specific type of chess
	 * piece, such as king or rook.
	 */
	public abstract boolean[][] possibleMoves();
	
	/**
	 * determines whether it is possible for a piece to move to a specific position. This method will use the 
	 * position of the board matrix passed as a method parameter is passed as a reference to a position of the 
	 * boolean matrix returned by the {@link #possibleMoves()} method. If the position of the boolean matrix 
	 * contains true value, then this method will return true value, indicating that movement to the board 
	 * position passed as parameter is possible for a given piece.
	 * @param position a board position
	 * @return a Boolean value that will indicate whether it is possible for the piece to move to the referenced
	 * position. True if it's possible, and false for the opposite
	 */
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	/**
	 * check if there is at least one possible move for the piece. This method is useful to notice if a piece is
	 * locked. This way, this function will check if there is at least one position of the matrix returned by the
	 * {@link #possibleMoves()} method, which contains true value, then it means that there are possible 
	 * movements that can be performed by a given piece. By traversing all the positions of this matrix until 
	 * find one that contains true value, and verifying that all those positions have false value, then it will
	 * indicate that there is no possible move for this piece.
	 * @return a Boolean value that will indicate whether a piece can perform a move or not. If it can then the 
	 * boolean value will match true, if it can't then it will match false
	 */
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] matrix = possibleMoves();	
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(matrix[i][j]) { // --------------> indicates that (matrix[i][j] == true)
					return matrix[i][j];
				}
			}
		}
		return false;
	}
	
}
