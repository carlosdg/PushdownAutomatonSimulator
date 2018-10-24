package me.carlosdg.pda.symbols;

import me.carlosdg.pda.word.Word;

/**
 * Abstract base class to represent stack and input symbols
 *
 * @author Carlos Domínguez García
 */
public abstract class AlphabetSymbol extends Symbol {
	/**
	 * Creates a new symbol given its string representation
	 *
	 * @param representation String representation of the symbol to create
	 * @throws IllegalArgumentException If the given representation is the one of
	 *                                  the empty string
	 */
	public AlphabetSymbol(String representation) throws IllegalArgumentException {
		super(representation);
		if (representation.equals(Word.EMPTY_STRING_REPR)) {
			throw new IllegalArgumentException(
					"Cannot create a symbol from the same string representation as the empty string ("
							+ Word.EMPTY_STRING_REPR + ")");
		}
	}
}