package me.carlosdg.pda.transition_function;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.symbols.State;

/**
 * Representation of PDA non-deterministic transition function with
 * epsilon-moves
 *
 * @author Carlos Domínguez García
 */
public class TransitionFunction {

	/**
	 * Transition function as a map. Given the current state, a input symbol from
	 * the input tape (or the empty string because this transition function
	 * considers epsilon-moves) and the symbol at the top of the stack, the function
	 * (the map) returns the next state and 0 or more stack symbols to push to the
	 * stack
	 */
	private Map<TransitionFunctionKey, Set<StateStackSymbolsPair>> map = new HashMap<>();

	/**
	 * Returns the value of the transition function associated to the given input
	 *
	 * @param currentState Current PDA state
	 * @param stackTop     Stack symbol at the top of the PDA's stack
	 * @param inputSymbol  A symbol from the input tape or the empty string
	 *                     (represented as Optional.empty)
	 * @return A set of the pairs (next state, 0 or more stack symbols to push to
	 *         the stack) associated with the given input. Note that a set of values
	 *         are associated to an input because this transition function is
	 *         non-deterministic
	 * @throws IllegalArgumentException If the given input doesn't map to any value
	 */
	public Set<StateStackSymbolsPair> get(State currentState, StackAlphabetSymbol stackTop,
			Optional<InputAlphabetSymbol> inputSymbol) throws IllegalArgumentException {
		TransitionFunctionKey key = new TransitionFunctionKey(currentState, stackTop, inputSymbol);
		Set<StateStackSymbolsPair> valueSet = map.get(key);

		if (valueSet == null) {
			throw new IllegalArgumentException(
					"Unknown transition: " + currentState + " " + stackTop + " " + inputSymbol);
		}

		return valueSet;
	}

	/**
	 * Updates the transition map
	 *
	 * @param state       State where the PDA has to be for consider this transition
	 * @param stackSymbol Stack symbol to be at the top of the stack
	 * @param inputSymbol Symbol from the input tape
	 * @param newElement  Element to map to the given input
	 */
	public void put(State state, StackAlphabetSymbol stackSymbol, Optional<InputAlphabetSymbol> inputSymbol,
			StateStackSymbolsPair newElement) {
		TransitionFunctionKey key = new TransitionFunctionKey(state, stackSymbol, inputSymbol);
		Set<StateStackSymbolsPair> valueSet = map.get(key);

		if (valueSet == null) {
			valueSet = new HashSet<>();
			map.put(key, valueSet);
		}

		valueSet.add(newElement);
	}

	/**
	 * Convenient overload to hide the construction of the State-StackSymbols pair
	 *
	 * @param state                State where the PDA has to be for consider this
	 *                             transition
	 * @param stackSymbol          Stack symbol to be at the top of the stack
	 * @param inputSymbol          Symbol from the input tape
	 * @param nextState            Next state to be mapped
	 * @param symbolsToPushToStack The list of 0 or more stack symbols to be pushed
	 *                             to the stack
	 */
	public void put(State state, StackAlphabetSymbol stackSymbol, Optional<InputAlphabetSymbol> inputSymbol,
			State nextState, List<StackAlphabetSymbol> symbolsToPushToStack) {
		put(state, stackSymbol, inputSymbol, new StateStackSymbolsPair(nextState, symbolsToPushToStack));
	}

}