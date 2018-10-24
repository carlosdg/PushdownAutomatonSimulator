package me.carlosdg.pda.simulator.transition_function;

import java.util.Objects;
import java.util.Optional;

import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.symbols.State;

/**
 * A helper class used to combine the three different keys needed for the
 * transition map into a single key so we avoid using nested maps
 *
 * @author Carlos Domínguez García
 */
class TransitionFunctionKey {

	/** Current PDA state */
	private State state;
	/** Symbol at the top of the stack */
	private StackAlphabetSymbol stackSymbol;
	/** Symbol from the input tape of the empty string */
	private Optional<InputAlphabetSymbol> optionalInputSymbol;

	/**
	 * Creates an instance of this key
	 *
	 * @param state               Current PDA state
	 * @param stackSymbol         Symbol at the top of the stack
	 * @param optionalInputSymbol Symbol from the input tape of the empty string
	 */
	public TransitionFunctionKey(State state, StackAlphabetSymbol stackSymbol,
			Optional<InputAlphabetSymbol> optionalInputSymbol) {
		this.state = state;
		this.stackSymbol = stackSymbol;
		this.optionalInputSymbol = optionalInputSymbol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(optionalInputSymbol, stackSymbol, state);
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
		TransitionFunctionKey other = (TransitionFunctionKey) obj;
		return Objects.equals(optionalInputSymbol, other.optionalInputSymbol)
				&& Objects.equals(stackSymbol, other.stackSymbol) && Objects.equals(state, other.state);
	}

}
