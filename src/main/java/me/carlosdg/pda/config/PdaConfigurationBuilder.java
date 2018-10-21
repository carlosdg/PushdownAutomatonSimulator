package me.carlosdg.pda.config;

import java.util.ArrayList;
import java.util.List;

/**
 * PdaConfiguration builder. Because PdaConfiguration requires too many
 * arguments to pass to the constructor this builder aims to make the
 * construction of PdaConfiguration instances easier to code. This is done by
 * providing a fluid interface hiding the construction of the objects needed for
 * the PdaConfiguration construction
 *
 * @author Carlos Domínguez García
 */
public class PdaConfigurationBuilder {
	private List<String> stateNames = new ArrayList<>();
	private List<String> inputAlphabetSymbolNames = new ArrayList<>();
	private List<String> stackAlphabetSymbolNames = new ArrayList<>();
	private String startingStateName = "";
	private String startingStackTopSymbolName = "";
	private List<String> acceptingStateNames = new ArrayList<>();
	private List<List<String>> transitions = new ArrayList<>();

	/**
	 * Add a state name to the list of states
	 */
	public PdaConfigurationBuilder addStateName(String newName) {
		stateNames.add(newName);
		return this;
	}

	/**
	 * Add an input alphabet symbol name
	 */
	public PdaConfigurationBuilder addInputAlphabetSymbolName(String newName) {
		inputAlphabetSymbolNames.add(newName);
		return this;
	}

	/**
	 * Add a stack alphabet symbol name
	 */
	public PdaConfigurationBuilder addStackAlphabetSymbolName(String newName) {
		stackAlphabetSymbolNames.add(newName);
		return this;
	}

	/**
	 * Set the starting state name
	 */
	public PdaConfigurationBuilder setStartingStateName(String newName) {
		startingStateName = newName;
		return this;
	}

	/**
	 * Set the starting stack top symbol name
	 */
	public PdaConfigurationBuilder setStartingStackTopSymbolName(String newName) {
		startingStackTopSymbolName = newName;
		return this;
	}

	/**
	 * Add an accepting state name
	 */
	public PdaConfigurationBuilder addAcceptingStateName(String newName) {
		acceptingStateNames.add(newName);
		return this;
	}

	/**
	 * Add a transition
	 */
	public PdaConfigurationBuilder addTransition(List<String> newTransition) {
		transitions.add(newTransition);
		return this;
	}

	/**
	 * Returns the PdaConfiguration instance built
	 */
	public PdaConfiguration build() {
		return new PdaConfiguration(stateNames, inputAlphabetSymbolNames, stackAlphabetSymbolNames, startingStateName,
				startingStackTopSymbolName, acceptingStateNames, transitions);
	}

}
