package main;

/**
 * A typical way to "run" a game would be to:
 *
 * SomeGame g = new SomeGame(); g.start();
 */
public abstract class Game {
	/**
	 * Changed so that we can manually end games easier.
	 */
	public final void end() {
		onEnd();
	}

	/**
	 * return true if the Game is over
	 */
	public abstract boolean isEnd();

	/**
	 * This is the method to override for game specific end logic.
	 */
	protected void onEnd() {
	}

	/**
	 * This is the method to override if you need to do something different than the
	 * default update for the Action.
	 */
	protected void onPerformAction(Action action) {
		action.update(this);
	}

	/**
	 * This is the method to override for game specific start logic.
	 */
	protected void onStart() {
	}

	/**
	 * Performs an action on the game
	 */
	public void performAction(Action action) {
		onPerformAction(action);
		if (isEnd()) {
			end();
		}
	}

	/**
	 * Marked as final to prevent overriding. Subclasses must put start logic in
	 * their onStart method.
	 */
	public final void start() {
		onStart();
	}

	/**
	 * It is expected that the Game return a status or String representation of its
	 * current state
	 */
	public abstract String toString();
}