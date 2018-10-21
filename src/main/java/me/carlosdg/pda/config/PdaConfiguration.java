package me.carlosdg.pda.config;

import java.util.List;

import me.carlosdg.pda.utils.Checks;

/**
 * PdaConfiguration is a data structure that represents the raw definition of a
 * Pushdown Automaton. It is used as an intermediary data structure between PDA
 * configuration input readers and the PdaSimulator class. This data structure
 * merely represents the content of the PDA configuration, it does not implement
 * any logic except checking that all the parameters are not null.
 *
 * @author Carlos Domínguez García
 */
public class PdaConfiguration {
	private List<String> stateNames;
	private List<String> inputAlphabetSymbolNames;
	private List<String> stackAlphabetSymbolNames;
	private String startingStateName;
	private String startingStackTopSymbolName;
	private List<String> acceptingStateNames;
	private List<List<String>> transitions;

	/**
	 * Construct a PdaConfiguration instance. Note that this object does not
	 * implement any logic, it only acts as a dummy container to represent the raw
	 * configuration read from the user.
	 *
	 * @param stateNames                 List of state names
	 * @param inputAlphabetSymbolNames   List of input alphabet symbol names
	 * @param stackAlphabetSymbolNames   List of stack alphabet symbol names
	 * @param startingStateName          Starting state name
	 * @param startingStackTopSymbolName Starting stack alphabet symbol name
	 * @param acceptingStateNames        List of accepting state names
	 * @param transitions                List of transitions
	 * @throws IllegalArgumentException If there is any null value
	 */
	public PdaConfiguration(List<String> stateNames, List<String> inputAlphabetSymbolNames,
			List<String> stackAlphabetSymbolNames, String startingStateName, String startingStackTopSymbolName,
			List<String> acceptingStateNames, List<List<String>> transitions) throws IllegalArgumentException {
		setStateNames(stateNames);
		setInputAlphabetSymbolNames(inputAlphabetSymbolNames);
		setStackAlphabetSymbolNames(stackAlphabetSymbolNames);
		setStartingStateName(startingStateName);
		setStartingStackTopSymbolName(startingStackTopSymbolName);
		setAcceptingStateNames(acceptingStateNames);
		setTransitions(transitions);
	}

	// Getters

	public List<String> getStateNames() {
		return stateNames;
	}

	public List<String> getInputAlphabetSymbolNames() {
		return inputAlphabetSymbolNames;
	}

	public List<String> getStackAlphabetSymbolNames() {
		return stackAlphabetSymbolNames;
	}

	public String getStartingStateName() {
		return startingStateName;
	}

	public String getStartingStackTopSymbolName() {
		return startingStackTopSymbolName;
	}

	public List<String> getAcceptingStateNames() {
		return acceptingStateNames;
	}

	public List<List<String>> getTransitions() {
		return transitions;
	}

	// Setters

	private void setStateNames(List<String> stateNames) throws IllegalArgumentException {
		Checks.throwIfAnyNullElement(stateNames, "Invalid null state name");
		this.stateNames = stateNames;
	}

	private void setInputAlphabetSymbolNames(List<String> inputAlphabetSymbolNames) throws IllegalArgumentException {
		Checks.throwIfAnyNullElement(inputAlphabetSymbolNames, "Invalid null input alphabet symbol name");
		this.inputAlphabetSymbolNames = inputAlphabetSymbolNames;
	}

	private void setStackAlphabetSymbolNames(List<String> stackAlphabetSymbolNames) throws IllegalArgumentException {
		Checks.throwIfAnyNullElement(stackAlphabetSymbolNames, "Invalid null stack alphabet symbol name");
		this.stackAlphabetSymbolNames = stackAlphabetSymbolNames;
	}

	private void setStartingStateName(String startingStateName) throws IllegalArgumentException {
		Checks.throwIfNull(startingStateName, "Invalid null starting state name");
		this.startingStateName = startingStateName;
	}

	private void setStartingStackTopSymbolName(String startingStackTopSymbolName) throws IllegalArgumentException {
		Checks.throwIfNull(startingStackTopSymbolName, "Invalid null starting stack top name");
		this.startingStackTopSymbolName = startingStackTopSymbolName;
	}

	private void setAcceptingStateNames(List<String> acceptingStateNames) throws IllegalArgumentException {
		Checks.throwIfAnyNullElement(acceptingStateNames, "Invalid list of accepting state names");
		this.acceptingStateNames = acceptingStateNames;
	}

	private void setTransitions(List<List<String>> transitions) throws IllegalArgumentException {
		Checks.throwIfAnyNullElement(transitions,
				"Invalid list of transitions, either the list is null or there are is at least one null transition");
		this.transitions = transitions;
	}

}
