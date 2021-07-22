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
	 * constructor.
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
	 * column of the board
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
	 * This method returns a piece belonging to the piece matrix associated with the board given a row and a 
	 * column of the board
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
	 * the piece position attribute is also changed, to the position referred as the method's argument.
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
	 * checks whether in a position on the board, there is or is not a piece
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
