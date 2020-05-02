package main;

import GamePieces.GamePiece;

/*
 * a class representing a game board with the board being a 2D array of GamePiece
 */
public class GameBoard {
	private GamePiece[][] board;

	public GameBoard(GamePiece[][] board) {
		this.board = board;
	}

	public GamePiece[][] getBoard() {
		return board;
	}

	/*
	 * given a row and a column, return the GamePiece at that row and column
	 */
	public GamePiece getPiece(int row, int column) {
		return board[row][column];
	}

	/*
	 * returns a boolean indicating whether the board has any empty spaces
	 */
	public boolean hasEmptySpace() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].isEmpty() == true) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * given two integers representing a row and a column, returns a boolean
	 * indicating whether the coordinates are in the boundaries of the array
	 */
	public boolean isInBounds(int row, int column) {
		int boardHeight = board.length;
		int boardWidth = board[0].length;
		if (row < 0 || column < 0 || row >= boardHeight || column > boardWidth) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * prints out the game board
	 */
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			System.out.print("Row: " + i + " | ");
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("        ––––––––––––––––");
		System.out.println("Column:  0 1 2 3 4 5 6 7");
	}

	/*
	 * given a 2D array of GamePiece, set the board to the 2D array
	 */
	public void setBoard(GamePiece[][] board) {
		this.board = board;
	}

	/*
	 * given a row, column, and GamePiece, set the board's coordinates at that row
	 * and column to that piece
	 */
	public void setPiece(int row, int column, GamePiece somePiece) {
		this.board[row][column] = somePiece;
	}

	/*
	 * returns a string version of the gameboard
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				buffer.append(board[i][j] + " ");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
