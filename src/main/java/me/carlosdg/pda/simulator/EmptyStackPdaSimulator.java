package me.carlosdg.pda.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import me.carlosdg.pda.definition.EmptyStackPdaDefinition;
import me.carlosdg.pda.simulator.input_tape.InputTape;
import me.carlosdg.pda.simulator.spies.PdaExecutionSpy;
import me.carlosdg.pda.simulator.stack.PdaStack;
import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.symbols.State;
import me.carlosdg.pda.transition_function.StateStackSymbolsPair;
import me.carlosdg.pda.transition_function.TransitionFunction;
import me.carlosdg.pda.word.Word;

/**
 * Pushdown Automaton Simulator
 *
 * @author Carlos Domínguez García
 */
public class EmptyStackPdaSimulator {

	/** Transition function */
	private TransitionFunction transitionFunction;
	/** Current state */
	private State initialState;
	/** Stack */
	private PdaStack stack;
	/** Input tape */
	private InputTape inputTape;
	/** Object used to log to the caller the progress of the algorithm */
	private Optional<PdaExecutionSpy> maybeSpy;

	/** Create the simulator from the PDA definition elements */
	public EmptyStackPdaSimulator(EmptyStackPdaDefinition pdaDefinition) {
		transitionFunction = pdaDefinition.getTransitionFunction();
		initialState = pdaDefinition.getInitialState();
		stack = new PdaStack(pdaDefinition.getStackAlphabet(), pdaDefinition.getInitialStackTop());
		inputTape = new InputTape(pdaDefinition.getInputAlphabet());
	}

	/** Returns whether the given input word is accepted by the automaton or not */
	public boolean accepts(Word inputWord, Optional<PdaExecutionSpy> maybeSpy) {
		stack.reset();
		inputTape.setInput(inputWord);
		this.maybeSpy = maybeSpy;

		return recursiveAccepts(initialState, new ArrayList<>());
	}

	/**
	 * Recursive algorithm to know if the word in the input tape is accepted by the
	 * automaton or not
	 *
	 * @param currentState       Current state of the automaton
	 * @param stackSymbolsToPush List of stack symbols to push in the current
	 *                           iteration. This is needed so the function handles
	 *                           the push of these symbols and at the end, before
	 *                           returning, it makes sure that the stack is in the
	 *                           same state as before calling the function
	 * @return Whether the string in the input tape is accepted or not
	 */
	private boolean recursiveAccepts(State currentState, List<StackAlphabetSymbol> stackSymbolsToPush) {
		boolean isInputAccepted = false;
		stack.push(stackSymbolsToPush);

		// If the stack is empty -> no more possible transitions
		// If the input has been read -> string accepted.
		// Else -> we need to see if there are applicable epsilon moves
		if (stack.empty()) {
			isInputAccepted = inputTape.empty();
		} else {
			// Get the top of the stack
			StackAlphabetSymbol stackTop = stack.pop();

			// Transitions with input symbol
			if (!isInputAccepted && !inputTape.empty()) {
				// Get the input symbol, run all the transitions and return the input symbol
				// to leave the tape as the caller gave it to us
				InputAlphabetSymbol inputSymbol = inputTape.nextSymbol();
				isInputAccepted = runAllPossibleTransitions(currentState, stackTop, Optional.of(inputSymbol));
				inputTape.revertNextSymbol();
			}

			// Transitions with epsilon-moves
			if (!isInputAccepted) {
				isInputAccepted = runAllPossibleTransitions(currentState, stackTop, Optional.empty());
			}

			// Restore the stack top to leave the stack as the caller gave it to us
			stack.push(stackTop);
		}

		// Remove the pushed elements to leave the stack as the caller expects
		stack.pop(stackSymbolsToPush.size());
		return isInputAccepted;
	}

	/**
	 * Get all possible transitions for the given input and runs recursiveAccept on
	 * everyone until any accepts the input. Returns true if any path accepted the
	 * string, false otherwise
	 *
	 * @param currentState Current PDA state
	 * @param stackTop     Top of the stack
	 * @param inputSymbol  Optional input symbol. If empty is given, the epsilon
	 *                     moves are used
	 * @return Whether the input string is accepted by any of the paths or not
	 */
	private boolean runAllPossibleTransitions(State currentState, StackAlphabetSymbol stackTop,
			Optional<InputAlphabetSymbol> inputSymbol) {

		Set<StateStackSymbolsPair> possibleTransitions = transitionFunction.get(currentState, stackTop, inputSymbol);

		for (StateStackSymbolsPair pair : possibleTransitions) {
			if (recursiveAccepts(pair.getState(), pair.getSymbols())) {
				return true;
			}
		}

		return false;
	}

}
