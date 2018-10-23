package me.carlosdg.pda.sets;

import java.util.Collection;

import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.pda.symbols.AlphabetSymbol;

/**
 * Input alphabet class
 *
 * @author Carlos Domínguez García
 */
public class InputAlphabet extends SymbolSet<AlphabetSymbol> {

	public InputAlphabet(Collection<String> symbolRepresentations) throws DuplicatedStringInSetException {
		super(symbolRepresentations);
	}

	@Override
	protected AlphabetSymbol newSymbol(String representation) {
		return new InputAlphabetSymbol(representation);
	}

}
