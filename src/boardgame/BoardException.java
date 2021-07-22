package boardgame;

/**
 * This class represents the custom exception propagated by the programa in errors related to the board
 * @author João Victor
 */

public class BoardException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BoardException(String msg) {
		super(msg);
	}

}
