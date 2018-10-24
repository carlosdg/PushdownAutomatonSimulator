package me.carlosdg.pda.simulator.spies;

import me.carlosdg.pda.simulator.input_tape.InputTape;
import me.carlosdg.pda.simulator.stack.PdaStack;
import me.carlosdg.pda.symbols.State;

/**
 * Spy that logs to the console the state of the automaton each iteration
 *
 * @author Carlos Domínguez García
 */
public class PdaExecutionConsoleLogger implements PdaExecutionSpy {

	@Override
	public void pathFinished(boolean accepted) {
		if (!accepted) {
			System.out.println("~~");
		}
	}

	@Override
	public void newIteration(State currentState, InputTape inputTape, PdaStack stack) {
		System.out.println(currentState + "\t" + inputTape + "\t" + stack);
	}

}
