package main;

import GamePieces.BlackPieceKing;
import GamePieces.BlackPieceNormal;
import GamePieces.GamePiece;
import GamePieces.SpecialPiece;
import GamePieces.WhitePieceKing;
import GamePieces.WhitePieceNormal;

public class xxxGame extends Game {
	public static int getRandomIntegerBetweenRange(double min, double max) {
		int x = (int) ((int) (Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	/*
	 * given a number of rows and number of columns, returns a 2D array of GamePiece
	 * of size numRows and numCols and is filled with EMPTY GamePiece objects
	 */
	public static GamePiece[][] setUpEmptyArray(int numRows, int numCols) {
		GamePiece[][] emptyBoard = new GamePiece[numRows][numCols];
		for (int row = 0; row < emptyBoard.length; row++) {
			for (int col = 0; col < emptyBoard[row].length; col++) {
				emptyBoard[row][col] = new GamePiece(GamePiece.getEmpty());
			}
		}
		return emptyBoard;
	}

	private String boardType;
	private GameBoard gameBoard = new GameBoard(setUpEmptyArray(0, 0));
	private char notTurnSymbol;
	private int numCols;
	private int numRows;
	private char turnSymbol;

	public xxxGame(char turnSymbol, char notTurnSymbol, int numRows, int numCols, String boardType) {
		this.turnSymbol = turnSymbol;
		this.notTurnSymbol = notTurnSymbol;
		this.numRows = numRows;
		this.numCols = numCols;
		this.gameBoard.setBoard(setUpCheckers(setUpEmptyArray(numRows, numCols), boardType).getBoard());
		this.boardType = boardType;
	}

	/*
	 * swaps the value of turnSymbol and notTurnSymbol
	 */
	public void changeTurn() {
		char temp;
		temp = turnSymbol;
		turnSymbol = notTurnSymbol;
		notTurnSymbol = temp;
	}

	/*
	 * returns a string indicating the winner of the game
	 */
	public String displayWinner() {
		GamePiece[][] board = gameBoard.getBoard();
		int whiteCounter = 0;
		int blackCounter = 0;
		int whiteKingCounter = 0;
		int blackKingCounter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].toString().equals("w")) {
					whiteCounter++;
				}
				if (board[i][j].toString().equals("W")) {
					whiteKingCounter++;
				}
				if (board[i][j].toString().equals("b")) {
					blackCounter++;
				}
				if (board[i][j].toString().equals("B")) {
					blackKingCounter++;
				}
			}
		}
		if (whiteCounter + whiteKingCounter == 0) {
			return "Team black has won because team white has no pieces left.";
		}
		if (blackCounter + blackKingCounter == 0) {
			return "Team white has won because team black has no pieces left.";
		}
		return "The game is a tie.";
	}

	/*
	 * returns the baord type
	 */
	public String getBoardType() {
		return boardType;
	}

	/*
	 * returns the current GameBoard
	 */
	public GameBoard getGameBoard() {
		return gameBoard;
	}

	/*
	 * returns the current notTurnSymbol
	 */
	public char getNotTurnSymbol() {
		return notTurnSymbol;
	}

	/*
	 * returns a char indicating the current turn
	 */
	public char getTurnSymbol() {
		return turnSymbol;
	}

	/*
	 * returns a boolean indicating whether the game has ended
	 */
	@Override
	public boolean isEnd() {
		GamePiece[][] board = gameBoard.getBoard();
		int whiteCounter = 0;
		int blackCounter = 0;
		int whiteKingCounter = 0;
		int blackKingCounter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].toString().equals("w")) {
					whiteCounter++;
				}
				if (board[i][j].toString().equals("W")) {
					whiteKingCounter++;
				}
				if (board[i][j].toString().equals("b")) {
					blackCounter++;
				}
				if (board[i][j].toString().equals("B")) {
					blackKingCounter++;
				}
			}
		}
		if (whiteCounter + whiteKingCounter == 0) {
			return true;
		}
		if (blackCounter + blackKingCounter == 0) {
			return true;
		}
		return false;
	}

	@Override
	protected void onEnd() {
		System.out.println();
		System.out.println("GAME OVER");
		System.out.println("Thank you for playing.");
		System.out.println("Results:");
		System.out.println();
	}

	@Override
	protected void onPerformAction(Action action) {
		super.onPerformAction(action);
	}

	@Override
	protected void onStart() {
		System.out.println(" –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––– ");
		System.out.println(" Modified Checkers");
		System.out.println(" Authors: Haseebullah Siddiqui and Ben Salis");
		System.out.println("                                                                        ");
		System.out.println(" Rules and Information:");
		System.out.println(" w stands for white and b stands for black.");
		System.out.println(" w always goes first");
		System.out.println(" Capital letters stand for king pieces.");
		System.out.println(" Normal pieces can only move diagonally forward, but king ");
		System.out.println(" pieces can move forward and backward");
		System.out.println(" To jump over an opponent piece, select its coordinates.");
		System.out.println(" You can only jump if there is an empty space across a piece.");
		System.out.println(" Special pieces allow a piece to become a king without going to");
		System.out.println(" the other side.");
		System.out.println(" No double or triple jumping allowed.");
		System.out.println(" –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––– ");
		System.out.println();
	}

	@Override
	public void performAction(Action action) {
		super.performAction(action);
	}

	public void removePieceOnBoard(int row, int column) {
		GamePiece empty = new GamePiece('-');
		gameBoard.setPiece(row, column, empty);
	}

	/*
	 * given a row, column, and a GamePiece, sets the piece at the row and column to
	 * the given GamePiece
	 */
	public void setPieceOnBoard(int row, int column, GamePiece somePiece) {
		gameBoard.setPiece(row, column, somePiece);
	}

	public GameBoard setUpCheckers(GamePiece[][] someBoard, String boardType) {
		WhitePieceNormal whitePiece = new WhitePieceNormal();
		WhitePieceKing whiteKing = new WhitePieceKing();
		BlackPieceNormal blackPiece = new BlackPieceNormal();
		BlackPieceKing blackKing = new BlackPieceKing();
		SpecialPiece specialPiece = new SpecialPiece();
		GameBoard newBoard = new GameBoard(someBoard);
		if (boardType.equals("demo")) {
			System.out.println("Board has been set to demo.");
			newBoard.setPiece(3, 3, blackPiece);
			newBoard.setPiece(4, 4, whitePiece);
		} else if (boardType.equals("demo-end-game")) {
			System.out.println("Board has been set to demo-end-game.");
			newBoard.setPiece(1, 5, blackPiece);
			newBoard.setPiece(2, 6, whitePiece);
		} else if (boardType.equals("demo-special")) {
			System.out.println("Board has been set to demo-special.");
			newBoard.setPiece(1, 5, blackPiece);
			newBoard.setPiece(3, 7, whitePiece);
			newBoard.setPiece(2, 6, specialPiece);
		} else if (boardType.equals("demo-block")) {
			System.out.println("Board has been set to demo-block.");
			newBoard.setPiece(1, 2, blackPiece);
			newBoard.setPiece(2, 3, blackPiece);
			newBoard.setPiece(3, 4, whitePiece);
		} else if (boardType.equals("demo-king")) {
			System.out.println("Board has been set to demo-king.");
			newBoard.setPiece(1, 5, blackKing);
			newBoard.setPiece(3, 5, blackKing);
			newBoard.setPiece(2, 6, whiteKing);
			newBoard.setPiece(2, 4, whiteKing);
		} else if (boardType.equals("demo-end-board")) {
			System.out.println("Board has been set to demo-end-board.");
			newBoard.setPiece(1, 0, whitePiece);
			newBoard.setPiece(6, 7, blackPiece);
		} else {
			System.out.println("Board has been set to default.");
			newBoard.setPiece(7, 0, whitePiece);
			newBoard.setPiece(7, 2, whitePiece);
			newBoard.setPiece(7, 4, whitePiece);
			newBoard.setPiece(7, 6, whitePiece);
			newBoard.setPiece(6, 1, whitePiece);
			newBoard.setPiece(6, 3, whitePiece);
			newBoard.setPiece(6, 5, whitePiece);
			newBoard.setPiece(6, 7, whitePiece);
			newBoard.setPiece(5, 0, whitePiece);
			newBoard.setPiece(5, 2, whitePiece);
			newBoard.setPiece(5, 4, whitePiece);
			newBoard.setPiece(5, 6, whitePiece);
			newBoard.setPiece(0, 1, blackPiece);
			newBoard.setPiece(0, 3, blackPiece);
			newBoard.setPiece(0, 5, blackPiece);
			newBoard.setPiece(0, 7, blackPiece);
			newBoard.setPiece(1, 0, blackPiece);
			newBoard.setPiece(1, 2, blackPiece);
			newBoard.setPiece(1, 4, blackPiece);
			newBoard.setPiece(1, 6, blackPiece);
			newBoard.setPiece(2, 1, blackPiece);
			newBoard.setPiece(2, 3, blackPiece);
			newBoard.setPiece(2, 5, blackPiece);
			newBoard.setPiece(2, 7, blackPiece);
			newBoard.setPiece(getRandomIntegerBetweenRange(3, 4), getRandomIntegerBetweenRange(0, 7), specialPiece);
		}
		return newBoard;
	}

	@Override
	public String toString() {
		return null;
	}
}
