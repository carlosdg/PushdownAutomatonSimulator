package me.carlosdg.pda.sets;

import java.util.Collection;

import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.sets.exceptions.DuplicatedStringInSetException;

/**
 * Stack alphabet class
 *
 * @author Carlos Domínguez García
 */
public class StackAlphabet extends SymbolSet<StackAlphabetSymbol> {

	public StackAlphabet(Collection<String> symbolRepresentations) throws DuplicatedStringInSetException {
		super(symbolRepresentations);
	}

	@Override
	protected StackAlphabetSymbol newSymbol(String representation) {
		return new StackAlphabetSymbol(representation);
	}

}
