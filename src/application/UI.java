package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

/**
 * The class UI is the user interface of the chess game and it has the purpose
 * of visually presenting the application information, through the terminal
 * 
 * @author João Victor
 */
public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	/**
	 * clean the terminal screen
	 */

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * It will allow the program to read a chess position entered by the user
	 * 
	 * @return chess position entered by the user
	 */
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String typedPosition = sc.nextLine();
			char column = typedPosition.charAt(0);
			int row = Integer.parseInt(typedPosition.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}

	/**
	 * It will print the board through the {@link #printBoard(ChessPiece[][])}
	 * method, the indicator of the pieces captured through the
	 * {@link #printCapturedPieces(List)} method and if there has not been a
	 * checkmate (and this will be checked using
	 * {@link chess.ChessMatch#getCheckMate()}) it will print respectively through
	 * the {@link chess.ChessMatch#getTurn()} and
	 * {@link chess.ChessMatch#getCheck()} methods, a message that will say which
	 * player is the current turn and also warn if there is a check. If a checkmate
	 * has occurred, it will print a congratulatory message to the winner.
	 * 
	 * @param chessMatch a chess match
	 * @param captured   list of captured chess pieces
	 */
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		if (!chessMatch.getCheckMate()) {
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
			if (chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		} else {
			System.out.println("CHECKMATE!");
			System.out.println("Winner: " + chessMatch.getCurrentPlayer());
		}
	}

	/**
	 * prints on the terminal screen, the matrix of chess pieces passed as method
	 * parameter. For each position on the board, a chess piece is printed.
	 * 
	 * @param piecesMatrix chess pieces matrix
	 */
	public static void printBoard(ChessPiece[][] piecesMatrix) {
		for (int row = 0; row < piecesMatrix.length; row++) {
			System.out.print((8 - row) + " ");
			for (int column = 0; column < piecesMatrix.length; column++) {
				printPiece(piecesMatrix[row][column], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	/**
	 * prints on the terminal screen, the matrix of chess pieces passed as method
	 * parameter, in addition to indication of the possible movements of the
	 * movement's source piece.
	 * 
	 * @param piecesMatrix  chess pieces matrix
	 * @param possibleMoves possible movements of a piece
	 */
	public static void printBoard(ChessPiece[][] piecesMatrix, boolean[][] possibleMoves) {
		for (int row = 0; row < piecesMatrix.length; row++) {
			System.out.print((8 - row) + " ");
			for (int column = 0; column < piecesMatrix.length; column++) {
				printPiece(piecesMatrix[row][column], possibleMoves[row][column]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	/**
	 * prints a chess piece. If a board position does not contain a chess piece
	 * (that is, the piece is null), then a visual representation for that situation
	 * is printed.
	 * 
	 * @param chessPiece chess piece
	 * @param background a boolean that if true will paint the background of a chess
	 *                   position
	 */
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_PURPLE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	/**
	 * Prints the black and white pieces that were captured in the game. For that,
	 * this method receives as parameter the list of captured pieces of the game,
	 * and then from this list, two new lists are created, the one of captured white
	 * pieces and the one of captured black pieces. So these lists are printed when
	 * they are converted to Strings by the {@link java.util.Arrays#toString()}
	 * method
	 * 
	 * @param capturedPieces captured pieces list
	 */

	private static void printCapturedPieces(List<ChessPiece> capturedPieces) {
		List<ChessPiece> whitePieces = capturedPieces.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		List<ChessPiece> blackPieces = capturedPieces.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());

		System.out.println("Captured pieces:");
		System.out.print("White:");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(whitePieces.toArray())); // will print the white pieces list
		System.out.print(ANSI_RESET);
		System.out.print("Black:");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(blackPieces.toArray())); // will print the black pieces list
		System.out.print(ANSI_RESET);
	}
}
