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

		// Notify spy of the new iteration
		maybeSpy.ifPresent(
				spy -> spy.newIteration(currentState, inputTape, stack, transitionsRepresentation(currentState)));

		// If the stack is empty -> no more transitions
		if (stack.isEmpty()) {
			// Input is accepted if the tape is all consumed
			isInputAccepted = inputTape.isEmpty();
			// Notify the spy that we reached a "leaf node", a point with no transitions
			if (maybeSpy.isPresent()) {
				maybeSpy.get().pathFinished(isInputAccepted);
			}
		} else {
			// Pop the top of the stack to perform the transitions
			StackAlphabetSymbol stackTop = stack.pop();
			Set<StateStackSymbolsPair> transitionResults;
			boolean noTransitions = true; // Flag to know if there are no transitions

			// Transitions consuming the input symbol
			if (!inputTape.isEmpty()) {
				Optional<InputAlphabetSymbol> maybeInputSymbol = Optional.of(inputTape.consumeInput());
				transitionResults = transitionFunction.get(currentState, stackTop, maybeInputSymbol);
				noTransitions = noTransitions && transitionResults.size() == 0;
				isInputAccepted = exploreTransitions(transitionResults);
				inputTape.revertConsumption(); // Restore the taken input symbol for the following transitions
			}

			// Epsilon moves (transitions consuming no input)
			if (!isInputAccepted) {
				transitionResults = transitionFunction.getEpsilonMoves(currentState, stackTop);
				noTransitions = noTransitions && transitionResults.size() == 0;
				isInputAccepted = exploreTransitions(transitionResults);
			}

			// If there were no transitions notify the spy that we reached a "leaf node"
			if (noTransitions && maybeSpy.isPresent()) {
				maybeSpy.get().pathFinished(isInputAccepted);
			}

			// Restore the stack top to leave the stack as the caller gave it to us
			stack.push(stackTop);
		}

		// Remove the pushed elements to leave the stack as the caller expects
		stack.pop(stackSymbolsToPush.size());
		return isInputAccepted;
	}

	/**
	 * Runs recursiveAccept on every given transition result until any accepts the
	 * input. Returns true if any path accepted the string, false otherwise
	 *
	 * @param transitionResults Transition results to be run
	 * @return Whether the input string is accepted by any of the paths or not
	 */
	private boolean exploreTransitions(Set<StateStackSymbolsPair> transitionResults) {
		boolean isInputAccepted = false;

		for (StateStackSymbolsPair pair : transitionResults) {
			if (recursiveAccepts(pair.getState(), pair.getSymbols())) {
				isInputAccepted = true;
				break;
			}
		}

		return isInputAccepted;
	}

	// TODO: remove this method and instead give an object representing the
	// transitions to the spies
	/**
	 * Returns the string representation of all the transitions for the current
	 * state, top of the stack and input symbol (including epsilon moves)
	 */
	private String transitionsRepresentation(State currentState) {
		StringBuilder builder = new StringBuilder();
		StackAlphabetSymbol stackTop = stack.peek();
		String stackTopRepr = stackTop == null ? Word.EMPTY_STRING_REPR : stackTop.getRepresentation();
		Optional<InputAlphabetSymbol> maybeInputSymbol;
		if (inputTape.isEmpty()) {
			maybeInputSymbol = Optional.empty();
		} else {
			maybeInputSymbol = Optional.of(inputTape.peek());
		}

		if (maybeInputSymbol.isPresent()) {
			Set<StateStackSymbolsPair> transitionsConsumingInput = transitionFunction.get(currentState, stackTop,
					maybeInputSymbol);

			builder.append("𝛿(" + currentState + ", " + maybeInputSymbol.get() + ", " + stackTopRepr + ") = { ");
			for (StateStackSymbolsPair pair : transitionsConsumingInput) {
				builder.append("(" + pair.getState() + ", ");
				if (pair.getSymbols().isEmpty()) {
					builder.append(Word.EMPTY_STRING_REPR + " ");
				}
				for (StackAlphabetSymbol stackSymbol : pair.getSymbols()) {
					builder.append(stackSymbol + " ");
				}
				builder.deleteCharAt(builder.length() - 1);
				builder.append("),");
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append(" }\t");
		}

		Set<StateStackSymbolsPair> emptyMoves = transitionFunction.getEpsilonMoves(currentState, stackTop);
		builder.append("𝛿(" + currentState + ", " + Word.EMPTY_STRING_REPR + ", " + stackTopRepr + ") = { ");
		for (StateStackSymbolsPair pair : emptyMoves) {
			builder.append("(" + pair.getState() + ", ");
			if (pair.getSymbols().isEmpty()) {
				builder.append(Word.EMPTY_STRING_REPR + " ");
			}
			for (StackAlphabetSymbol stackSymbol : pair.getSymbols()) {
				builder.append(stackSymbol + " ");
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append("),");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(" } ");

		return builder.toString();
	}

}
