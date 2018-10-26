package me.carlosdg.pda.simulator.spies;

import me.carlosdg.pda.simulator.input_tape.InputTape;
import me.carlosdg.pda.simulator.stack.PdaStack;
import me.carlosdg.pda.symbols.State;

/**
 * Interface for defining "spy" objects whose methods will be called on certain
 * parts of the PDA algorithm
 *
 * @author Carlos Domínguez García
 */
public interface PdaExecutionSpy {
	/** Called when the current exploration path has finished */
	public void pathFinished(boolean accepted);

	/** Called when a new iteration is going to be run */
	public void newIteration(State currentState, InputTape inputTape, PdaStack stack, String transitionRepresentation);
}
