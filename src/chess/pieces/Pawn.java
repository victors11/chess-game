package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

/**
 * This child class of the class ChessPiece represents a Pawn piece. This class
 * will contain the Pawn movement logic.
 * 
 * @author João Victor
 */
public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	/**
	 * creates the pawn chess piece, which is associated with a board and has a
	 * color besides having an dependency with a chess match
	 * 
	 * @param board      chessboard
	 * @param color      piece color
	 * @param chessMatch chess match
	 */
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	/**
	 * @return the symbol that will represent the pawn piece
	 */
	@Override
	public String toString() {
		return "P";
	}

	/**
	 * Implements pawn chess piece move logic. This implementation occurs by marking
	 * the matrix positions with true value. These marked positions consist of the
	 * possible moves that can be performed by the rook. thus, for white pawns the
	 * marked positions will be above or diagonally, and for black pawns the marked
	 * positions will be below or also diagonally. Taking a white pawn as an
	 * example, when checking the position above the pawn exists using
	 * {@link boardgame.Board#positionExists(Position)} and if there is no piece
	 * using {@link boardgame.Board#thereIsAPiece(Position)} then it will be marked
	 * as true. If the same pawn has not moved yet in the game when verifying using
	 * {@link chess.ChessPiece#getMoveCount()} and in the second position above the
	 * pawn there is no piece, then that second position will also be marked as
	 * true. Now, the position northeast and northwest of the pawn will only be
	 * marked as true if these two positions contain an opponent's piece, and this
	 * is analyzed, through the
	 * {@link chess.ChessPiece#isThereOpponentPiece(Position)} method. Black pawns
	 * follow the same rules of movement, with the exception that they move down
	 * only. Added to that, the pawn will have one possible additional move with the
	 * use of the en passant special move. Then it is checked, if the piece that is
	 * on the left or right side of the pawn is an opponent pawn using the
	 * {@link chess.ChessMatch#getEnPassantVulnerable()} method. If the opponent
	 * pawn is vulnerable to en passant, then the pawn will be able to perform the
	 * special move
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			// above one
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			// above two
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getBoard().positionExists(p)
					&& !getBoard().thereIsAPiece(p) && getMoveCount() == 0) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			// northwest
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			// northeast
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}

			// special move en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					matrix[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);

				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					matrix[right.getRow() - 1][right.getColumn()] = true;
				}
			}

		} else {
			// above one
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			// above two
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getBoard().positionExists(p)
					&& !getBoard().thereIsAPiece(p) && getMoveCount() == 0) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			// northwest
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			// northeast
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}

			// special move en passant black
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);

				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					matrix[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					matrix[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}

		return matrix;
	}
}
