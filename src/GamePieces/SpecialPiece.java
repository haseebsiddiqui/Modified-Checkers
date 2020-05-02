package GamePieces;

public class SpecialPiece extends GamePiece {
	private char symbol = 'S';

	public SpecialPiece() {
		super('S');
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
