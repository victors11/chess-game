package application;

import chess.ChessMatch;

/**
 * This class is responsible for running the application.
 * @author Jo�o Victor
 */

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		UI.printBoard(chessMatch.getPieces());
		
	}

}
