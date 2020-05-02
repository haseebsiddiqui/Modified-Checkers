package main;

import GamePieces.GamePiece;

public class PlacePieceAction implements Action<xxxGame> {
	private int column;
	private int pieceCol;
	private int pieceRow;
	private char player;
	private int row;

	public PlacePieceAction(char player, int row, int column, int pieceRow, int pieceCol) {
		this.player = player;
		this.row = row;
		this.column = column;
		this.pieceRow = pieceRow;
		this.pieceCol = pieceCol;
	}

	// returns the uppercase version of a piece's symbol
	public char convertToKing(GamePiece somePiece) {
		return Character.toUpperCase(somePiece.getSymbol());
	}

	// getColumn() returns the desired column
	public int getColumn() {
		return column;
	}

	// getPieceCol() returns the initial row
	public int getPieceCol() {
		return pieceCol;
	}

	// getPieceRow() returns the initial row
	public int getPieceRow() {
		return pieceRow;
	}

	public char getPlayer() {
		return player;
	}

	// getRow() returns the desired row
	public int getRow() {
		return row;
	}

	@Override
	public boolean isValid(xxxGame game) {
		GamePiece currentPiece = game.getGameBoard().getPiece(getRow(), getColumn());
		GamePiece initialPiece = game.getGameBoard().getPiece(getPieceRow(), getPieceCol());
		if (!game.getGameBoard().isInBounds(getRow(), getColumn())) {
			return false;
		}
		if (game.getTurnSymbol() == 'w') {
			if (initialPiece.getSymbol() != 'w' && initialPiece.getSymbol() != 'W') {
				return false;
			}
			if (getRow() != getPieceRow() - 1 && initialPiece.getSymbol() == 'w') {
				return false;
			}
			if (getColumn() != getPieceCol() + 1 && getColumn() != getPieceCol() - 1) {
				return false;
			}
			if (currentPiece.getSymbol() == 'W') {
				if (getRow() != getPieceRow() + 1 && getRow() != getPieceRow() + 1) {
					return false;
				}
				if (getColumn() != getPieceCol() + 1 && getColumn() != getPieceCol() - 1) {
					return false;
				}
			}
		}
		if (game.getTurnSymbol() == 'b') {
			if (initialPiece.getSymbol() != 'b' && initialPiece.getSymbol() != 'B') {
				return false;
			}
			if (getRow() != getPieceRow() + 1 && initialPiece.getSymbol() == 'b') {
				return false;
			}
			if (getColumn() != getPieceCol() + 1 && getColumn() != getPieceCol() - 1) {
				return false;
			}
			if (currentPiece.getSymbol() == 'B') {
				if (getRow() != getPieceRow() + 1 && getRow() != getPieceRow() + 1) {
					return false;
				}
				if (getColumn() != getPieceCol() + 1 && getColumn() != getPieceCol() - 1) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * returns string with player and coordinates
	 */
	@Override
	public String toString() {
		return "Selected player: " + player + " Selected coordinates: (" + getRow() + "," + getColumn() + ")";
	}

	/*
	 * updates the game and moves pieces if necessary
	 */
	@Override
	public void update(xxxGame game) {
		int firstRow = 0;
		int lastRow = 7;
		GamePiece pieceAtDesiredLocation = game.getGameBoard().getPiece(getRow(), getColumn());
		// initial piece
		GamePiece piece = game.getGameBoard().getPiece(getPieceRow(), getPieceCol());
		GamePiece kingPiece = new GamePiece(convertToKing(piece));
		// jumping for kings
		if ((game.getGameBoard().getPiece(getPieceRow(), getPieceCol()).getSymbol() == 'W'
				|| game.getGameBoard().getPiece(getPieceRow(), getPieceCol()).getSymbol() == 'B')
				&& pieceAtDesiredLocation.getSymbol() != 'S' && pieceAtDesiredLocation.getSymbol() != '-') {
			if (getPieceCol() < getColumn() && getPieceRow() > getRow()) {
				// check for blocked piece
				if (game.getGameBoard().getPiece(getRow() - 1, getColumn() + 1).getSymbol() != '-'
						&& game.getGameBoard().getPiece(getRow() - 1, getColumn() + 1).getSymbol() != 'S') {
					game.changeTurn();
					System.out.println("Blocked...");
					return;
				}
				game.setPieceOnBoard(getRow() - 1, getColumn() + 1, piece);
				game.removePieceOnBoard(getPieceRow(), getPieceCol());
				game.removePieceOnBoard(getRow(), getColumn());
			}
			if (getPieceCol() > getColumn() && getPieceRow() > getRow()) {
				if (game.getGameBoard().getPiece(getRow() - 1, getColumn() - 1).getSymbol() != '-'
						&& game.getGameBoard().getPiece(getRow() - 1, getColumn() - 1).getSymbol() != 'S') {
					System.out.println("Blocked...");
					game.changeTurn();
					return;
				}
				game.setPieceOnBoard(getRow() - 1, getColumn() - 1, piece);
				game.removePieceOnBoard(getPieceRow(), getPieceCol());
				game.removePieceOnBoard(getRow(), getColumn());
			}
			if (getPieceCol() > getColumn() && getPieceRow() < getRow()) {
				if (game.getGameBoard().getPiece(getRow() + 1, getColumn() - 1).getSymbol() != '-'
						&& game.getGameBoard().getPiece(getRow() + 1, getColumn() - 1).getSymbol() != 'S') {
					System.out.println("Blocked...");
					game.changeTurn();
					return;
				}
				game.setPieceOnBoard(getRow() + 1, getColumn() - 1, piece);
				game.removePieceOnBoard(getPieceRow(), getPieceCol());
				game.removePieceOnBoard(getRow(), getColumn());
			}
			if (getPieceCol() < getColumn() && getPieceRow() < getRow()) {
				if (game.getGameBoard().getPiece(getRow() + 1, getColumn() + 1).getSymbol() != '-'
						&& game.getGameBoard().getPiece(getRow() + 1, getColumn() + 1).getSymbol() != 'S') {
					System.out.println("Blocked...");
					game.changeTurn();
					return;
				}
				game.setPieceOnBoard(getRow() + 1, getColumn() + 1, piece);
				game.removePieceOnBoard(getPieceRow(), getPieceCol());
				game.removePieceOnBoard(getRow(), getColumn());
			}
			return;
		}
		// jumping for w piece
		if (game.getTurnSymbol() == 'w' && game.getNotTurnSymbol() == 'b' && pieceAtDesiredLocation.getSymbol() != 'S'
				&& pieceAtDesiredLocation.getSymbol() != '-') {
			if (getPieceCol() < getColumn()) {
				// check for blocked piece
				if (game.getGameBoard().getPiece(getRow() - 1, getColumn() + 1).getSymbol() != '-'
						&& game.getGameBoard().getPiece(getRow() - 1, getColumn() + 1).getSymbol() != 'S') {
					System.out.println("Blocked...");
					game.changeTurn();
					return;
				}
				game.setPieceOnBoard(getRow() - 1, getColumn() + 1, piece);
				game.removePieceOnBoard(getPieceRow(), getPieceCol());
				game.removePieceOnBoard(getRow(), getColumn());
			}
			if (getPieceCol() > getColumn()) {
				if (game.getGameBoard().getPiece(getRow() - 1, getColumn() - 1).getSymbol() != '-'
						&& game.getGameBoard().getPiece(getRow() - 1, getColumn() - 1).getSymbol() != 'S') {
					System.out.println("Blocked...");
					game.changeTurn();
					return;
				}
				game.setPieceOnBoard(getRow() - 1, getColumn() - 1, piece);
				game.removePieceOnBoard(getPieceRow(), getPieceCol());
				game.removePieceOnBoard(getRow(), getColumn());
			}
			// jumping for b piece
		} else if (game.getTurnSymbol() == 'b' && game.getNotTurnSymbol() == 'w'
				&& pieceAtDesiredLocation.getSymbol() != 'S' && pieceAtDesiredLocation.getSymbol() != '-') {
			if (getPieceCol() > getColumn()) {
				if (game.getGameBoard().getPiece(getRow() + 1, getColumn() - 1).getSymbol() != '-'
						&& game.getGameBoard().getPiece(getRow() + 1, getColumn() - 1).getSymbol() != 'S') {
					System.out.println("Blocked...");
					game.changeTurn();
					return;
				}
				game.setPieceOnBoard(getRow() + 1, getColumn() - 1, piece);
				game.removePieceOnBoard(getPieceRow(), getPieceCol());
				game.removePieceOnBoard(getRow(), getColumn());
			}
			if (getPieceCol() < getColumn()) {
				if (game.getGameBoard().getPiece(getRow() + 1, getColumn() + 1).getSymbol() != '-'
						&& game.getGameBoard().getPiece(getRow() + 1, getColumn() + 1).getSymbol() != 'S') {
					System.out.println("Blocked...");
					game.changeTurn();
					return;
				}
				game.setPieceOnBoard(getRow() + 1, getColumn() + 1, piece);
				game.removePieceOnBoard(getPieceRow(), getPieceCol());
				game.removePieceOnBoard(getRow(), getColumn());
			}
		} else {
			// otherwise don't jump and simply set piece there
			game.setPieceOnBoard(getRow(), getColumn(), piece);
			game.removePieceOnBoard(getPieceRow(), getPieceCol());
		}
		// convert to kings
		if (game.getTurnSymbol() == 'w' && getRow() == firstRow && getPlayer() != kingPiece.getSymbol()) {
			game.setPieceOnBoard(getRow(), getColumn(), kingPiece);
		}
		if (game.getTurnSymbol() == 'b' && getRow() == lastRow && getPlayer() != kingPiece.getSymbol()) {
			game.setPieceOnBoard(getRow(), getColumn(), kingPiece);
		}
		// convert to king piece
		if (pieceAtDesiredLocation.getSymbol() == 'S') {
			System.out.println("Special piece! Your piece has become a king.");
			game.setPieceOnBoard(getRow(), getColumn(), kingPiece);
		}
		game.changeTurn();
	}
}
