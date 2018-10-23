package me.carlosdg.pda.alphabets;

import java.util.Collection;

import me.carlosdg.pda.alphabets.exceptions.DuplicatedStringInAlphabetException;
import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.symbols.AlphabetSymbol;

/**
 * Stack alphabet class
 *
 * @author Carlos Domínguez García
 */
public class StackAlphabet extends Alphabet {

	public StackAlphabet(Collection<String> symbolRepresentations) throws DuplicatedStringInAlphabetException {
		super(symbolRepresentations);
	}

	@Override
	protected AlphabetSymbol newSymbol(String representation) {
		return new StackAlphabetSymbol(representation);
	}

}
