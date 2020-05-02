package main;

import java.util.Scanner;

public class xxxUI implements UserInput<xxxGame> {
	public static void main(String args[]) {
		Scanner boardTypeScanner = new Scanner(System.in);
		System.out.println("Type a board type or press enter to use default.");
		System.out.println("Current types: demo, demo-end-game, demo-special, demo-block, demo-king, demo-end-board");
		String inputtedBoard = boardTypeScanner.nextLine();
		xxxUI checkersGame = new xxxUI(inputtedBoard);
		checkersGame.getActualGame().onStart();
		boolean gameEnded = false;
		PlacePieceAction nextMove;
		while (!checkersGame.getActualGame().isEnd()) {
			// print the current game board
			System.out.println("Printing current game board");
			checkersGame.getActualGame().getGameBoard().printBoard();
			// get the next move from the user
			nextMove = checkersGame.getUserNextMove(checkersGame.getActualGame());
			// print the next move
			System.out.println(nextMove);
			// perform the next move
			checkersGame.getActualGame().performAction(nextMove);
		}
		System.out.println(checkersGame.getActualGame().displayWinner());
		System.out.println("\nFinal game board");
		checkersGame.getActualGame().getGameBoard().printBoard();
	}

	private xxxGame actualGame;
	private String desiredBoard;

	public xxxUI(String desiredBoard) {
		actualGame = new xxxGame('w', 'b', 8, 8, desiredBoard);
	}

	public xxxGame getActualGame() {
		return actualGame;
	}

	public String getDesiredBoard() {
		return desiredBoard;
	}

	public PlacePieceAction getUserNextMove(xxxGame game) {
		Scanner actionScanner = new Scanner(System.in);
		boolean validMove = false;
		PlacePieceAction currentMove = null;
		int rowToMoveTo = 0;
		int colToMoveTo = 0;
		int pieceRow = 0;
		int pieceCol = 0;
		while (!validMove) {
			System.out.println("Turn: " + actualGame.getTurnSymbol());
			System.out.println("Type 4 numbers separated by spaces in the order:");
			System.out.println("inital row, initial column, desired row, desired column");
			String input = actionScanner.nextLine();
			String[] numbers = input.split(" ");
			pieceRow = Integer.parseInt(numbers[0]);
			pieceCol = Integer.parseInt(numbers[1]);
			rowToMoveTo = Integer.parseInt(numbers[2]);
			colToMoveTo = Integer.parseInt(numbers[3]);
			// Create an instance of the corresponding action
			currentMove = new PlacePieceAction(game.getTurnSymbol(), rowToMoveTo, colToMoveTo, pieceRow, pieceCol);
			// check if its a valid move
			validMove = currentMove.isValid(game);
			if (!validMove) {
				System.out.println("Invalid move - try again");
			}
		}
		// return the move
		return currentMove;
	}

	public void setDesiredBoard(String desiredBoard) {
		this.desiredBoard = desiredBoard;
	}
}
