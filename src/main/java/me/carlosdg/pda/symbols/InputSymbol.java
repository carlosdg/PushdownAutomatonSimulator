package me.carlosdg.pda.symbols;

import java.util.Optional;

/**
 * Class to represent input symbols
 *
 * @author Carlos Domínguez García
 */
public class InputSymbol extends Symbol {
	/**
	 * Returns an empty optional if the given string is the representation of the
	 * empty string. Otherwise returns an optional with the symbol object
	 *
	 * @param representation String to construct the symbol from
	 * @return An optional with the symbol object or empty if the given
	 *         representation is the one of the empty string
	 */
	public static Optional<Symbol> from(String representation) {
		if (representation.equals(Symbol.EMPTY_STRING_REPR)) {
			return Optional.empty();
		}

		return Optional.of(new InputSymbol(representation));
	}

	/** @see me.carlosdg.pda.symbols.Symbol */
	public InputSymbol(String representation) {
		super(representation);
	}
}
