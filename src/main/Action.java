package main;

/**
 * An Action is something that a player can perform
 */
public interface Action<G extends Game> {
	/**
	 * Returns true if this Action is valid to perform on the given state of the
	 * game
	 */
	public boolean isValid(G game);

	/**
	 * Mutates state of the game according to the properties of this Action
	 */
	public void update(G game);
}
