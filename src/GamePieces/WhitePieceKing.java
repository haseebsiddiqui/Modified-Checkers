package GamePieces;

public class WhitePieceKing extends GamePiece {
	private char symbol = 'W';

	public WhitePieceKing() {
		super('W');
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
