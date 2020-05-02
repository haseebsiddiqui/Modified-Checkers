package GamePieces;

/*
 * a class representing a game piece, with the game piece being a character
 */
public class GamePiece {
	private static final char EMPTY = '-';

	public static char getEmpty() {
		return EMPTY;
	}

	private char symbol;

	public GamePiece(char symbol) {
		this.symbol = symbol;
	}

	/*
	 * given an object, casts it to a GamePiece and returns a boolean if the two
	 * GamePiece symbols are equal
	 */
	@Override
	public boolean equals(Object obj) {
		GamePiece compare = (GamePiece) obj;
		if (this.symbol == compare.symbol) {
			return true;
		} else {
			return false;
		}
	}

	public char getSymbol() {
		return symbol;
	}

	/*
	 * returns a boolean indicating whether a GamePiece is empty ('E')
	 */
	public boolean isEmpty() {
		if (this.getSymbol() == getEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * returns a string with the GamePiece value
	 */
	@Override
	public String toString() {
		return Character.toString(symbol);
	}
}
