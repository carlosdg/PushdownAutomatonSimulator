package me.carlosdg.pda.alphabets;

import java.util.Collection;

import me.carlosdg.pda.alphabets.exceptions.DuplicatedStringInAlphabetException;
import me.carlosdg.pda.symbols.InputSymbol;
import me.carlosdg.pda.symbols.Symbol;

/**
 * Input alphabet class
 *
 * @author Carlos Domínguez García
 */
public class InputAlphabet extends Alphabet {

	public InputAlphabet(Collection<String> symbolRepresentations) throws DuplicatedStringInAlphabetException {
		super(symbolRepresentations);
	}

	@Override
	protected Symbol newSymbol(String representation) {
		return new InputSymbol(representation);
	}

}
