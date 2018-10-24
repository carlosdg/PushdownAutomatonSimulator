package me.carlosdg.pda.simulator;

import java.util.Optional;
import java.util.Set;

import me.carlosdg.pda.definition.EmptyStackPdaDefinition;
import me.carlosdg.pda.simulator.input_tape.InputTape;
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

	/** Create the simulator from the PDA definition elements */
	public EmptyStackPdaSimulator(EmptyStackPdaDefinition pdaDefinition) {
		transitionFunction = pdaDefinition.getTransitionFunction();
		initialState = pdaDefinition.getInitialState();
		stack = new PdaStack(pdaDefinition.getStackAlphabet(), pdaDefinition.getInitialStackTop());
		inputTape = new InputTape(pdaDefinition.getInputAlphabet());
	}

	/** Returns whether the given input word is accepted by the automaton or not */
	public boolean accepts(Word inputWord) {
		stack.reset();
		inputTape.setInput(inputWord);

		return recursiveAccepts(initialState, inputTape, stack);
	}

	/**
	 * Recursive definition of the automaton behavior to accept or reject the word
	 * in the input tape
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
