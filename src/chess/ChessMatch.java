package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

/**
 * This class represent a chess match and will contain the logic of the game
 * rules. So we have that class ChessMatch will be the heart of the chess
 * system.
 * 
 * @author Jo�o Victor
 */

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;

	/**
	 * Creates a board of 8 rows and 8 columns and puts the chess pieces on the
	 * board using the {@link #initialSetup()} method. In addition, a chess match
	 * begins with turn 1, and in this first turn, the one that will carry out the
	 * movement, will be the player with the white pieces
	 */
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * returns an matrix of chess pieces. For this, this method converts each piece
	 * of type Piece of Matrix of pieces beloging to the Board into a piece of type
	 * ChessPiece.
	 * 
	 * @return a ChessPiece matrix
	 */

	public ChessPiece[][] getPieces() {
		ChessPiece[][] chessPiecesMatrix = new ChessPiece[board.getRows()][board.getColumns()];
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				chessPiecesMatrix[row][column] = (ChessPiece) board.piece(row, column);
			}
		}
		return chessPiecesMatrix;
	}

	/**
	 * It will capture an source position that will be entered by the user, which
	 * will be of a ChessPosition type. Then this position will be converted to a
	 * Position by the {@link chess.ChessPosition#toPosition()} method, and then
	 * that position will be validated by the
	 * {@link #validateSourcePosition(Position)} method. After these procedures, the
	 * Boolean of possible movements of the piece located in this position will be
	 * returned through the {@link boardgame.Piece#possibleMoves()} method
	 * 
	 * @param sourcePosition source position
	 * @return a boolean matrix that will indicate the possible target positions of
	 *         a chess move
	 */

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	/**
	 * contains all the logic of validating and moving a piece, in a chess move. Two
	 * chess coordinate positions are passed as method parameters, then converted to
	 * board matrix position. Then a validation of the origin position is performed
	 * using the {@link #validateSourcePosition(Position)} method, and then the
	 * logic of moving and capturing the pieces by the
	 * {@link #makeMove(Position, Position)} method is performed. When the movement
	 * is finalized, the turn is changed by the {@link #nextTurn()} method, and
	 * then, the captured piece is returned
	 * 
	 * @param sourcePosition source position
	 * @param targetPosition targe position
	 * @return a captured piece at the end of movement
	 */

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	/**
	 * contains the piece move logic of the performChessMove method. A piece p is
	 * dropped from the source of a move, and the captured piece is dropped from the
	 * target of the move. So the piece p is placed on the target move. Then the
	 * captured piece is returned with the end of the method execution.
	 * 
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
	 * Validates a source position of the movement after verification of compliance
	 * of 3 possible conditions. If one of these conditions is fulfilled, then this
	 * will mean that the position selected as the source position of the movement
	 * is invalid. The 3 conditions are: IF there is no piece in the source position
	 * and this will be checked by the
	 * {@link boardgame.Board#thereIsAPiece(Position)} method. IF the source
	 * position does not contain a color piece allowed by the current turn (that is,
	 * if the piece does not belong to the player who will carry out the movement in
	 * this turn). IF the source position contains a piece that has no possible
	 * movements. A move in a chess game can only occur from an source position,
	 * that contains a piece that has possible moves
	 * 
	 * @param sourcePosition source position of a movement
	 */

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	/**
	 * It will check if the target position is valid, in relation to the source
	 * position of a movement. From the source position where a piece is located,
	 * the possible movements of that piece will be traced. If the target position
	 * is not part of that set of positions where piece movements are allowed, then
	 * a ChessException will be thrown. To determine if the target position of the
	 * part of the origin position returned by the
	 * {@link boardgame.Board#piece(Position)} method is part of this matrix of
	 * possible positions to perform a movement, the
	 * {@link boardgame.Piece#possibleMove(Position)} method is used
	 * 
	 * 
	 * @param sourcePosition source position of a movement
	 * @param targetPosition target position of a movement
	 */

	private void validateTargetPosition(Position sourcePosition, Position targetPosition) {
		if (!board.piece(sourcePosition).possibleMove(targetPosition)) {
			throw new ChessException("The chosen piece can't move to the target position");
		}
	}

	/**
	 * will change the turn match. When the turn is changed, the next player will be
	 * the one to make a move, that is, when the current turn is the turn of the
	 * white pieces, the next turn will be the turn of black pieces. When the
	 * current turn is the turn of the black pieces, the next, will be that of the
	 * white pieces
	 */

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	/**
	 * Put a chess piece on the board using the chess coordinate system. For this,
	 * it makes use of the {@link boardgame.Board#placePiece(Piece, Position)}
	 * method, when passing as parameters for this method a chess piece and a chess
	 * coordinate position converted to a board matrix position
	 * 
	 * @param column column
	 * @param row    row
	 * @param piece  chess piece
	 */

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	/**
	 * this method will be responsible for starting the chess game, placing the
	 * pieces on the board through the method
	 * {@link #placeNewPiece(char, int, ChessPiece)}
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
