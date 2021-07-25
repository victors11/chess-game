package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

/**
 * This class represent a chess match and will contain the logic of the game rules. So we have that class
 * ChessMatch will be the heart of the chess system.
 * @author João Victor
 */

public class ChessMatch {
	
	private Board board;
	
	/**
	 * Creates a board of 8 rows and 8 columns and puts the chess pieces on the board using the 
	 * {@link #initialSetup()} method 
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
	 * contains all the logic of validating and moving a piece, in a chess move. Two chess coordinate positions 
	 * are passed as method parameters, then converted to board matrix position. Then a validation of the origin
	 * position is performed using the {@link #validateSourcePosition(Position)} method, and then the logic of 
	 * moving and capturing the pieces by the {@link #makeMove(Position, Position)} method is performed
	 * @param sourcePosition source position
	 * @param targetPosition targe position 
	 * @return a captured piece at the end of movement
	 */
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	/**
	 * contains the piece move logic of the performChessMove method. A piece p is dropped from the source of a 
	 * move, and the captured piece is dropped from the target of the move. So the piece p is placed on the move
	 * destination. Then the captured part is returned with the end of the method execution.
	 * @param source source position
	 * @param target target position 
	 * @return a captured piece at end of movement
	 */
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}

	/**
	 * validates if an origin position of a movement, there was a piece. Using the 
	 * {@link boardgame.Board#thereIsAPiece(Position)} If the position that was passed as the method parameter 
	 * does not contain a piece, then a ChessException will be thrown
	 * @param sourcePosition source position of a movement 
	 */
	private void validateSourcePosition(Position sourcePosition) {
		if(!board.thereIsAPiece(sourcePosition)) {
			throw new ChessException("There is no piece on source position");
		}
	}
	
	/**
	 * Put a chess piece on the board using the chess coordinate system. For this, it makes use of the 
	 * {@link boardgame.Board#placePiece(Piece, Position)} method, when passing as parameters for this method a
	 * chess piece and a chess coordinate position converted to a board matrix position
	 * @param column column 
	 * @param row row
	 * @param piece chess piece
	 */
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	/**
	 * this method will be responsible for starting the chess game, placing the pieces on the board through the
	 * method {@link #placeNewPiece(char, int, ChessPiece)}
	 */
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
