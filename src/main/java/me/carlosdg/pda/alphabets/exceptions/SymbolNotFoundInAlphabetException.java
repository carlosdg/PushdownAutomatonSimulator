package me.carlosdg.pda.alphabets.exceptions;

/**
 * Exception for when a given string doesn't represent any symbol of the target
 * alphabet
 *
 * @author Carlos Domínguez García
 */
public class SymbolNotFoundInAlphabetException extends Exception {
	private static final long serialVersionUID = 7250455007229293610L;

	/**
	 * Creates an instance of this exception
	 *
	 * @param stringReprOfNotFoundSymbol String representation of the symbol that
	 *                                   wasn't found in the alphabet
	 */
	public SymbolNotFoundInAlphabetException(String stringReprOfNotFoundSymbol) {
		super("AlphabetSymbol in alphabet not found: " + stringReprOfNotFoundSymbol);
	}
}
