package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

/**
 * This child class of the class ChessPiece represents a King piece. This class
 * will contain the king movement logic.
 * 
 * @author João Victor
 */
public class King extends ChessPiece {

	private ChessMatch chessMatch;

	/**
	 * creates the king chess piece, which is associated with a board and has a
	 * color
	 * 
	 * @param board chessboard
	 * @param color piece color
	 */
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	/**
	 * @return the symbol that will represent the king piece
	 */
	@Override
	public String toString() {
		return "K";
	}

	/**
	 * determines whether the king can move to the position referred to as the
	 * method parameter. This method will check if in a position there is a piece,
	 * and if there is, it will verify if it is an opponent piece through the
	 * {@link chess.ChessPiece#getColor()} method, which will indicate whether this
	 * piece has a different color. These checks occur, because a king can only move
	 * to a position that doesn't contain any piece, or a position that contains a
	 * opponent, a piece of different color of the king
	 * 
	 * @param position a position
	 * @return a boolean value that if true indicates that the king can move to the
	 *         indicated position.
	 */
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	/**
	 * this method tests whether in a given position there is a rook suitable for
	 * the castling special move. initially the
	 * {@link boardgame.Board#piece(Position)} method returns a possible chess piece
	 * located in the position that was passed as a parameter. Then it is analyzed,
	 * if a chess piece was really returned, if the returned chess piece is a rook,
	 * if this rook is the same color as the king, and if this rook has not moved
	 * yet, if all the conditions are met, this will mean that the rook will be able
	 * to castling
	 * 
	 * @param position a position
	 * @return a boolean that, if true, will indicate that in a position there is a
	 *         rook suitable for the castling special move.
	 */
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	/**
	 * Implements king chess piece move logic. This implementation occurs by marking
	 * the matrix positions with true value. These marked positions consist of the
	 * possible moves that can be performed by the king. Thus, positions are marked
	 * above, left, right and below of the king. At the end of this method, the
	 * boolean matrix that will contain the marked positions will be returned. In
	 * the case of the king, only positions around that piece are possible target
	 * positions. Then each of the positions around the king are checked by the
	 * {@link #canMove(Position)} method, and if the method indicates that it is
	 * possible to perform the move, the position in question will be marked as true
	 * by the boolean matrix. Furthermore, the king will have one more possible
	 * additional move with the use of the castling special move. For this special
	 * move to be performed, at first it is necessary that the king hasn't performed
	 * any moves and that the king isn't in check, this last condition can be
	 * realized using the {@link chess.ChessMatch#getCheck()} method. Then, it is
	 * checked if the positions between the king and the rook on the king side are
	 * vacant, if they are and by checking using the
	 * {@link #testRookCastling(Position)} method if the rook on the king side is
	 * suitable for the castling move, then the small castling move can be performed
	 * and the two-column position towards that rook will be marked as true. there
	 * is also the possibility of making the big castling movement that follows the
	 * same logic as the small one, but with the rook on the queen's side.
	 * 
	 * @return a boolean matrix that will indicate the possible moves of the king
	 *         chess piece
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// Above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// northwest
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// northeast
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// south-west
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// south-east
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matrix[p.getRow()][p.getColumn()] = true;
		}

		// special move castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// small castling (castling king side rook)
			Position kingSideRookPos = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(kingSideRookPos)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					matrix[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			// big castling (castling queen side rook)
			Position queenSideRookPos = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(queenSideRookPos)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					matrix[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return matrix;
	}
}
