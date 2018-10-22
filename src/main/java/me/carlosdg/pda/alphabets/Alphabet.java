package me.carlosdg.pda.alphabets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import me.carlosdg.pda.alphabets.exceptions.DuplicatedStringInAlphabetException;
import me.carlosdg.pda.alphabets.exceptions.SymbolNotFoundInAlphabetException;
import me.carlosdg.pda.symbols.Symbol;

/**
 * Base class for alphabets. It is used as a set of symbols and a verifier to
 * know if a given string represents a symbol in the alphabet or not
 *
 * @author Carlos Domínguez García
 */
abstract class Alphabet {
	/** Map from string representation to symbols represented by the string */
	private Map<String, Symbol> alphabet = new HashMap<>();

	/**
	 * Creates an alphabet from the given collection of strings that represent
	 * symbols. Throws if there is any duplicate string
	 *
	 * @param symbolRepresentations Collection of strings that represent symbols.
	 * @throws DuplicatedStringInAlphabetException If there is any duplicated string
	 */
	public Alphabet(Collection<String> symbolRepresentations) throws DuplicatedStringInAlphabetException {
		for (String repr : symbolRepresentations) {
			if (alphabet.containsKey(repr)) {
				throw new DuplicatedStringInAlphabetException(repr);
			}

			alphabet.put(repr, newSymbol(repr));
		}
	}

	/**
	 * Returns a symbol object represented by the given string if it belongs to this
	 * alphabet or throws otherwise
	 *
	 * @param repr String representation of the symbol to get
	 * @return Symbol object represented by the given string
	 * @throws SymbolNotFoundInAlphabetException If the given string doesn't
	 *                                           represent any symbol in this
	 *                                           alphabet
	 */
	public Symbol getSymbol(String repr) throws SymbolNotFoundInAlphabetException {
		Symbol symbol = alphabet.get(repr);
		if (symbol == null) {
			throw new SymbolNotFoundInAlphabetException(repr);
		}
		return symbol;
	}

	// /**
	// * Returns an empty optional if the given string is the representation of the
	// * empty string. Otherwise returns an optional with the symbol object if it
	// * belongs to this alphabet, otherwise an exception is thrown
	// *
	// * @param representation String to construct the symbol from
	// * @return An optional with the symbol object or empty if the given
	// * representation is the one of the empty string
	// * @throws SymbolNotFoundInAlphabetException If the given representation is
	// * neither the empty string nor a
	// * symbol that belongs to this
	// * alphabet
	// */
	// public Optional<Symbol> parseRepresentation(String repr) throws
	// SymbolNotFoundInAlphabetException {
	// if (repr.equals(Symbol.EMPTY_STRING_REPR)) {
	// return Optional.empty();
	// }
	//
	// return Optional.of(getSymbol(repr));
	// }

	/** Returns an instance of the symbols of this alphabet */
	abstract protected Symbol newSymbol(String representation);

}