package main;

public abstract interface UserInput<G extends Game> {
	public abstract <T extends Action<G>> T getUserNextMove(G game);
}
