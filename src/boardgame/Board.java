package boardgame;

/**
 * This class represents the board of a chess game. That's why it has a number of rows and columns, and it's also
 * associated with multiple pieces that are arranged in a matrix form on the board. We have that the matrix of 
 * pieces, consists of the regions of the board, where the pieces are placed
 * 
 * @author João Victor
 */

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	/**
	 * Create a board that has a number of rows, a number of columns and finally an matrix of pieces. Only the
	 * number of rows and columns are passed as constructor parameters, as the matrix is instantiated within the
	 * constructor. If a number of rows or number of columns less than 1 is passed as a parameter to create a
	 * board, then the constructor will throw a BoardException, because a board must have at least one row and 
	 * one column
	 * 
	 * @param rows number of rows
	 * @param columns number of columns 
	 */
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	/**
	 * This method returns a piece belonging to the piece matrix associated with the board given a row and a 
	 * column of the board. Using the {@link #positionExists(Position)} method, if the row and column passed as
	 * the method's parameter refer to a position that doesn't exist, then the method will throw a 
	 * BoardException, because it is not possible to get a piece from a position on the board that does not 
	 * exist
	 * @param row one of the rows of the board 
	 * @param column one of the columns of the board
	 * @return a piece given a row and a column of the board
	 */
	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	/**
	 * This method returns a piece belonging to the piece matrix associated with the board given a board 
	 * position. Using the {@link #positionExists(Position)} method, if the position passed as the method 
	 * parameter does not exist on the board, then the method will throw a BoardException, because it is not 
	 * possible to get a piece from a position on the board that does not exist
	 * @param position one of the board positions
	 * @return a piece given a position of the board
	 */
	
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	/**
	 * Put a piece in a position of the pieces matrix belonging to the board. To perform this procedure, the
	 * method receives as a parameter, a position and a piece. When placing the piece in a matrix position, 
	 * the piece position attribute is also changed, to the position referred as the method's argument. Using 
	 * the {@link #thereIsAPiece(Position)} method, if in the position being passed as a method parameter there
	 * is already a piece, then the method will throw a BoardException, because it is not possible to place a 
	 * piece in a position on the board, which already contains a piece
	 * @param piece piece
	 * @param position board position
	 */
	
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("There already is a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	/**
	 * Removes a piece from the board located in the position on the board that was passed as the method 
	 * parameter. Thus, the attribute position of the removed piece will be null and the position of the matrix
	 * of pieces belonging to the board will also contain a null value, since the piece is no longer positioned 
	 * on the board. After that, the removed piece will be returned. Using the {@link #thereIsAPiece(Position)}
	 * method, if in the position passed as the method parameter, there is no part, then a BoardException will 
	 * be thrown, because it is not possible to remove a piece from a position that does not contain a piece.
	 * @param position board position
	 * @return the piece of the board that was removed
	 */
	
	public Piece removePiece(Position position) {
		if(!thereIsAPiece(position)) {
			return null;
		}
		Piece removedPiece = piece(position);
		removedPiece.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return removedPiece;
	}
	
	/**
	 * check if a certain position exists on the board or not given a row and a column
	 * @param row row
	 * @param column column 
	 * @return a boolean that will indicate whether or not a position exists on the board. Returns True for 
	 * exists, and false for doesn't exist.
	 */
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	
	/**
	 * check if a certain position exists on the board or not given a position
	 * @param position position
	 * @return a boolean that will indicate whether or not a position exists on the board. Returns True for 
	 * exists, and false for doesn't exist.
	 */
	
	public boolean positionExists(Position position) {
		return position.getRow() >= 0 && position.getRow() < rows && position.getColumn() >= 0
			&& position.getRow() < columns;
	}
	
	/**
	 * checks whether in a position on the board, there is or is not a piece. Using the 
	 * {@link #positionExists(Position)} method, if the position passed as parameter does not exist on the 
	 * board, then the method will throw a BoardException, because it is not possible to check whether or not 
	 * there is a piece if the position does not exist.
	 * @param position position 
	 * @return a boolean that will indicate whether or not there is a piece in a position on the board. Returns
	 * True if it exists, and false if it doesn't
	 */
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
	
}
