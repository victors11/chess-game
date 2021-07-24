package chess;

/**
 * This class represents the custom exception propagated by the programa in errors related to the chess layer
 * @author João Victor
 */

public class ChessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ChessException(String msg) {
		super(msg);
	}

}
