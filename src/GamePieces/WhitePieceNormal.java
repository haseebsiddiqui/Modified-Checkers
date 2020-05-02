package GamePieces;

public class WhitePieceNormal extends GamePiece {
	private char symbol = 'w';

	public WhitePieceNormal() {
		super('w');
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
