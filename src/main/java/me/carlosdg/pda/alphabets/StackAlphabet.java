package me.carlosdg.pda.alphabets;

import java.util.Collection;

import me.carlosdg.pda.alphabets.exceptions.DuplicatedStringInAlphabetException;
import me.carlosdg.pda.symbols.StackSymbol;
import me.carlosdg.pda.symbols.Symbol;

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
	protected Symbol newSymbol(String representation) {
		return new StackSymbol(representation);
	}

}
