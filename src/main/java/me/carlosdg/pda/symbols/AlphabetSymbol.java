package me.carlosdg.pda.symbols;

/**
 * Abstract base class to represent stack and input symbols
 *
 * @author Carlos Domínguez García
 */
public abstract class AlphabetSymbol extends Symbol {
	/**
	 * String representation of the Empty string/word. This is not really a symbol
	 * but a string/word with no symbols. However, to represent it in the screen we
	 * need this reserved token
	 */
	public static final String EMPTY_STRING_REPR = ".";

	/**
	 * Creates a new symbol given its string representation
	 *
	 * @param representation String representation of the symbol to create
	 * @throws IllegalArgumentException If the given representation is the one of
	 *                                  the empty string
	 */
	public AlphabetSymbol(String representation) throws IllegalArgumentException {
		super(representation);
		if (representation.equals(AlphabetSymbol.EMPTY_STRING_REPR)) {
			throw new IllegalArgumentException(
					"Cannot create a symbol from the same string representation as the empty string ("
							+ AlphabetSymbol.EMPTY_STRING_REPR + ")");
		}
	}
}