package me.carlosdg.pda.sets;

import java.util.Collection;

import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.sets.exceptions.DuplicatedStringInSetException;

/**
 * Input alphabet class
 *
 * @author Carlos Domínguez García
 */
public class InputAlphabet extends SymbolSet<InputAlphabetSymbol> {

	public InputAlphabet(Collection<String> symbolRepresentations) throws DuplicatedStringInSetException {
		super(symbolRepresentations);
	}

	@Override
	protected InputAlphabetSymbol newSymbol(String representation) {
		return new InputAlphabetSymbol(representation);
	}

}
