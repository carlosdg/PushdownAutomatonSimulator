package me.carlosdg.pda.sets;

import java.util.Collection;

import me.carlosdg.pda.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.pda.symbols.State;

/**
 * Set of states class
 * 
 * @author Carlos Domínguez García
 */
public class StateSet extends SymbolSet<State> {

	public StateSet(Collection<String> symbolRepresentations) throws DuplicatedStringInSetException {
		super(symbolRepresentations);
	}

	@Override
	protected State newSymbol(String representation) {
		return new State(representation);
	}

}
