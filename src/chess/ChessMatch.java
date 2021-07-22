package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

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
		initialSetup();
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
	
	/**
	 * this method will be responsible for starting the chess game, placing the pieces on the board
	 */
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
	}
}