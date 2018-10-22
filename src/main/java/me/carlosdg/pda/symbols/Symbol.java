package me.carlosdg.pda.symbols;

/**
 * Abstract base class to represent stack and input symbols
 *
 * @author Carlos Domínguez García
 */
public abstract class Symbol {
	/**
	 * String representation of the Empty string/word. This is not really a symbol
	 * but a string/word with no symbols. However, to represent it in the screen we
	 * need this reserved token
	 */
	public static final String EMPTY_STRING_REPR = ".";

	/**
	 * A string representation of this symbol to know how do we represent this
	 * symbol in the screen. And how does the user represent the symbol to input it
	 * to the application
	 */
	private String representation;

	/**
	 * Creates a new symbol given its string representation
	 *
	 * @param representation String representation of the symbol to create
	 * @throws IllegalArgumentException If the given representation is the one of
	 *                                  the empty string
	 */
	public Symbol(String representation) throws IllegalArgumentException {
		if (representation.equals(Symbol.EMPTY_STRING_REPR)) {
			throw new IllegalArgumentException(
					"Cannot create a symbol from the same string representation as the empty string ("
							+ Symbol.EMPTY_STRING_REPR + ")");
		}
		setRepresentation(representation);
	}

	/** Returns the string representation of this symbol */
	public String getRepresentation() {
		return representation;
	}

	@Override
	public String toString() {
		return getRepresentation();
	}

	/** Sets the string representation of this symbol */
	protected void setRepresentation(String representation) {
		this.representation = representation;
	}
}