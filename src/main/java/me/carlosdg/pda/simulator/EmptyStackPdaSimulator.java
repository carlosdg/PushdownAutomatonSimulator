package me.carlosdg.pda.simulator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import me.carlosdg.pda.definition.EmptyStackPdaDefinition;
import me.carlosdg.pda.sets.StateSet;
import me.carlosdg.pda.simulator.input_tape.InputTape;
import me.carlosdg.pda.simulator.stack.PdaStack;
import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.symbols.State;
import me.carlosdg.pda.transition_function.StateStackSymbolsPair;
import me.carlosdg.pda.transition_function.TransitionFunction;

/**
 * Pushdown Automaton Simulator
 *
 * @author Carlos Domínguez García
 */
public class EmptyStackPdaSimulator {

	/** Transition function */
	private TransitionFunction transitionFunction;
	/** Set of states */
	private StateSet stateSet;
	/** Current state */
	private State initialState;
	/** Current state */
	private State currentState;
	/** Stack */
	private PdaStack stack;
	/** Input tape */
	private InputTape inputTape;

	/** Create the simulator from the PDA me.carlosdg.pda.definition elements */
	public EmptyStackPdaSimulator(EmptyStackPdaDefinition pdaDefinition) {
		transitionFunction = pdaDefinition.getTransitionFunction();
		stateSet = pdaDefinition.getSetOfStates();
		initialState = pdaDefinition.getInitialState();
		stack = new PdaStack(pdaDefinition.getStackAlphabet(), pdaDefinition.getInitialStackTop());
		inputTape = new InputTape(pdaDefinition.getInputAlphabet());
	}

	/** Returns whether the given input word is accepted by the automaton or not */
	public boolean accepts(List<InputAlphabetSymbol> inputWord) {
		currentState = initialState;
		stack.reset();
		inputTape.setInput(inputWord);

		return recursiveAccepts(currentState, inputTape, stack);
	}

	/**
	 * Recursive me.carlosdg.pda.definition of the automaton behavior to accept or
	 * reject the word in the input tape
	 */
	private boolean recursiveAccepts(State currentState, InputTape inputTape, PdaStack stack) {
		// Check accept condition
		if (stack.empty() && inputTape.empty()) {
			return true;
		}

		// Check non acceptance because empty stack or tape
		if (stack.empty() || inputTape.empty()) {
			return false;
		}

		// Get stack symbol. And first we'll try with the epsilon-moves
		StackAlphabetSymbol stackTop = stack.pop();
		Optional<InputAlphabetSymbol> inputSymbol = Optional.empty();

		// Transitions with empty string
		Set<StateStackSymbolsPair> possibleTransitions = transitionFunction.get(currentState, stackTop, inputSymbol);

		// Recurse
		for (StateStackSymbolsPair pair : possibleTransitions) {
			PdaStack tempStack = new PdaStack(stack);
			tempStack.push(pair.getSymbols());
			if (recursiveAccepts(pair.getState(), new InputTape(inputTape), tempStack)) {
				return true;
			}
		}

		// Transitions with input symbol
		inputSymbol = Optional.of(inputTape.nextSymbol());
		possibleTransitions = transitionFunction.get(currentState, stackTop, inputSymbol);

		// Recurse
		for (StateStackSymbolsPair pair : possibleTransitions) {
			PdaStack tempStack = new PdaStack(stack);
			tempStack.push(pair.getSymbols());
			if (recursiveAccepts(pair.getState(), new InputTape(inputTape), tempStack)) {
				return true;
			}
		}

		// Not accepted because there are no transitions left
		return false;
	}

}
