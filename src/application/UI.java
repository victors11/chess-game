package application;

import chess.ChessPiece;

/**
 * The class UI is the user interface of the chess game and it has the purpose of visually presenting the 
 * application information, through the terminal
 * @author João Victor
 */

public class UI {
	
	/**
	 * prints on the terminal screen, the matrix of chess pieces passed as method parameter. For each position on
	 * the board, a chess piece is printed.
	 * @param piecesMatrix chess pieces matrix
	 */
	
	public static void printBoard(ChessPiece[][] piecesMatrix) {
		for(int row = 0; row < piecesMatrix.length; row++) {
			System.out.print((8 - row) + " ");
			for(int column = 0; column < piecesMatrix.length; column++) {
				printPiece(piecesMatrix[row][column]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	/**
	 * prints a chess piece. If a board position does not contain a chess piece (that is, the piece is null),
	 * then a visual representation for that situation is printed.
	 * @param chessPiece chess piece
	 */
	
	private static void printPiece(ChessPiece chessPiece) {
		if(chessPiece == null) {
			System.out.print("-");
		}
		else {
			System.out.print(chessPiece);
		}
		System.out.print(" ");
	}

}
