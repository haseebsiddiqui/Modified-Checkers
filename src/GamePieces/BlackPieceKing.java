package GamePieces;

public class BlackPieceKing extends GamePiece {
	private char symbol = 'B';

	public BlackPieceKing() {
		super('B');
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
