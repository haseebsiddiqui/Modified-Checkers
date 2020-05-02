package GamePieces;

public class BlackPieceNormal extends GamePiece {
	private char symbol = 'b';

	public BlackPieceNormal() {
		super('b');
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
