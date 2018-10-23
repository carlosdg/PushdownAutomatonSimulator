package me.carlosdg.pda.sets.exceptions;

/**
 * Exception for when a duplicated string is found wanting to be part of an
 * alphabet
 *
 * @author Carlos Domínguez García
 */
public class DuplicatedStringInSetException extends Exception {
	private static final long serialVersionUID = -6985536630123145670L;

	/**
	 * Creates an instance of this exception
	 *
	 * @param duplicatedString Duplicated string found that caused the exception
	 */
	public DuplicatedStringInSetException(String duplicatedString) {
		super("Found at least the following duplicated string in alphabet: " + duplicatedString);
	}
}
