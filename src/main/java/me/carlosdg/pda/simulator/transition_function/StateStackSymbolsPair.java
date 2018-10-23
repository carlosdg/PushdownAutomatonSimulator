package me.carlosdg.pda.simulator.transition_function;

import java.util.List;
import java.util.Objects;

import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.symbols.State;

/**
 * Helper class to combine the next state and list of stack symbols into a
 * 2-uple for representing the output of the transition function
 *
 * @author Carlos Domínguez García
 */
public class StateStackSymbolsPair {

	/**
	 * Next state where the PDA will be after a transition with this 2-uple as
	 * output
	 */
	private State state;

	/**
	 * 0 or more stack symbols to push to the stack after a transition with this
	 * 2-uple as output
	 */
	private List<StackAlphabetSymbol> symbols;

	/**
	 * Creates an instance of this 2-uple to hold a state and 0 or more stack
	 * symbols
	 *
	 * @param state   PDA state
	 * @param symbols List of 0 or more stack symbols
	 */
	public StateStackSymbolsPair(State state, List<StackAlphabetSymbol> symbols) {
		this.state = state;
		this.symbols = symbols;
	}

	/** @return the state */
	public State getState() {
		return state;
	}

	/** @return the symbols */
	public List<StackAlphabetSymbol> getSymbols() {
		return symbols;
	}

	@Override
	public int hashCode() {
		return Objects.hash(state, symbols);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		StateStackSymbolsPair other = (StateStackSymbolsPair) obj;
		return Objects.equals(state, other.state) && Objects.equals(symbols, other.symbols);
	}

}
