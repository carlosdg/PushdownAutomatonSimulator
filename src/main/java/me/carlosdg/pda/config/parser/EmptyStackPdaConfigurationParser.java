package me.carlosdg.pda.config.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.carlosdg.pda.config.PdaConfiguration;
import me.carlosdg.pda.sets.InputAlphabet;
import me.carlosdg.pda.sets.StackAlphabet;
import me.carlosdg.pda.sets.StateSet;
import me.carlosdg.pda.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.pda.sets.exceptions.SymbolNotFoundInSetException;
import me.carlosdg.pda.symbols.AlphabetSymbol;
import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.symbols.State;
import me.carlosdg.pda.transition_function.TransitionFunction;

/**
 * Singleton class representing the parser that given the PDA configuration,
 * parses it and returns the instance of the simulator from the parsed elements
 *
 * @author Carlos Domínguez García
 */
public class EmptyStackPdaConfigurationParser {

	/** Cache variable for the singleton instance */
	private static EmptyStackPdaConfigurationParser singletonInstance = new EmptyStackPdaConfigurationParser();

	/** Entry point for getting the singleton instance of this parser */
	public static EmptyStackPdaConfigurationParser parser() {
		return singletonInstance;
	}

	/** Set of states */
	private StateSet stateSet;
	/** Input alphabet */
	private InputAlphabet inputAlphabet;
	/** Stack alphabet */
	private StackAlphabet stackAlphabet;
	/** Initial state */
	private State initialState;
	/** Initial symbol at the top of the stack */
	private StackAlphabetSymbol initialStackTop;
	/** Transition function */
	private TransitionFunction transitionFunction;

	/**
	 * Private constructor to make sure that outside this class, instances can only
	 * be created via the static entry point
	 */
	private EmptyStackPdaConfigurationParser() {
	}

	/**
	 * Parses the given raw PDA configuration to create all the elements that the
	 * PDA needs. Throws if there is any error like duplicated elements in the sets
	 * or there is any symbol in a transition that is not part of any set
	 */
	public void parse(PdaConfiguration configuration)
			throws DuplicatedStringInSetException, SymbolNotFoundInSetException {
		// Parse the sets making sure that there are no duplicates
		// And check that the starting state and stack top belong to their respective
		// set
		stateSet = new StateSet(configuration.getStateNames());
		inputAlphabet = new InputAlphabet(configuration.getInputAlphabetSymbolNames());
		stackAlphabet = new StackAlphabet(configuration.getStackAlphabetSymbolNames());
		initialState = stateSet.getSymbol(configuration.getStartingStateName());
		initialStackTop = stackAlphabet.getSymbol(configuration.getStartingStackTopSymbolName());

		// Parse the transitions
		transitionFunction = new TransitionFunction();
		for (List<String> rawTransition : configuration.getTransitions()) {
			parseAndAddTransition(transitionFunction, rawTransition);
		}

		// TODO: create and return a simulator instance from the parsed elements
	}

	/**
	 * Given the transition function and the raw representation of a transition,
	 * this method parses the transition and adds it to the transition map if there
	 * are no problems. Throws if there are not enough elements in the raw list or
	 * if there is any symbol that doesn't belong to its corresponding set
	 */
	private void parseAndAddTransition(TransitionFunction transitionFunction, List<String> rawTransition)
			throws SymbolNotFoundInSetException, IllegalArgumentException {
		try {
			State initialState = stateSet.getSymbol(rawTransition.get(0));
			Optional<InputAlphabetSymbol> inputAlphabetSymbol = parseMaybeInputAlphabetSymbol(rawTransition.get(1));
			StackAlphabetSymbol topOfStack = stackAlphabet.getSymbol(rawTransition.get(2));
			State nextState = stateSet.getSymbol(rawTransition.get(3));
			List<StackAlphabetSymbol> stackSymbolsToPush = new ArrayList<>();

			for (int i = 4; i < rawTransition.size(); ++i) {
				stackSymbolsToPush.add(stackAlphabet.getSymbol(rawTransition.get(i)));
			}

			transitionFunction.put(initialState, topOfStack, inputAlphabetSymbol, nextState, stackSymbolsToPush);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"Not enough elements in the transition (" + String.join(", ", rawTransition) + ")");
		}
	}

	/**
	 * Given a string, returns an empty optional if it is the representation of the
	 * empty string or an optional of the string object represented by the given
	 * string. Throws if the given string doesn't represent any symbol of the input
	 * alphabet
	 */
	private Optional<InputAlphabetSymbol> parseMaybeInputAlphabetSymbol(String maybeInputAlphabetRepresentation)
			throws SymbolNotFoundInSetException {
		if (maybeInputAlphabetRepresentation.equals(AlphabetSymbol.EMPTY_STRING_REPR)) {
			return Optional.empty();
		}

		return Optional.of(inputAlphabet.getSymbol(maybeInputAlphabetRepresentation));
	}

}
