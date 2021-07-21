package chess;

import boardgame.Board;

/**
 * This class represent a chess match and will contain the logic of the game rules. So we have that class
 * ChessMatch will be the heart of the chess system.
 * @author Jo�o Victor
 */

public class ChessMatch {
	
	private Board board;
	
	/**
	 * The class ChessMatch determines that the board it contains must have 8 rows and 8 columns 
	 */
	public ChessMatch() {
		board = new Board(8, 8);
	}
	
	/**
	 * returns an matrix of chess pieces. For this, this method converts each piece of type Piece of Matrix of 
	 * pieces beloging to the Board into a piece of type ChessPiece.
	 * @return a ChessPiece matrix
	 */
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] chessPiecesMatrix = new ChessPiece[board.getRows()][board.getColumns()];
		for(int row = 0; row < board.getRows(); row++) {
			for(int column = 0; column < board.getColumns(); column++) {
				chessPiecesMatrix[row][column] = (ChessPiece) board.piece(row, column);
			}
		}
		return chessPiecesMatrix;
	}
	
	
}
