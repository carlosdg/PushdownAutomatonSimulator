package me.carlosdg.pda.transition_function;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.symbols.StackAlphabetSymbol;
import me.carlosdg.pda.symbols.State;
import me.carlosdg.pda.transition_function.StateStackSymbolsPair;
import me.carlosdg.pda.transition_function.TransitionFunction;

public class TransitionFunctionTest {

	@Test
	public void shouldReturnTheTransitionDefined() {
		TransitionFunction transitionFunction = new TransitionFunction();

		// Inputs
		State s0 = new State("q0");
		Optional<InputAlphabetSymbol> inputSymbol = Optional.of(new InputAlphabetSymbol("a"));
		StackAlphabetSymbol stackAlphabetSymbol = new StackAlphabetSymbol("S");

		// Outputs
		State s1 = new State("q1");
		List<StackAlphabetSymbol> symbolsToPush = Arrays.asList(new StackAlphabetSymbol("A"),
				new StackAlphabetSymbol("B"));

		// Put into the map
		transitionFunction.put(s0, stackAlphabetSymbol, inputSymbol, s1, symbolsToPush);

		// Expected result
		StateStackSymbolsPair expectedResult = new StateStackSymbolsPair(new State("q1"),
				Arrays.asList(new StackAlphabetSymbol("A"), new StackAlphabetSymbol("B")));

		Set<StateStackSymbolsPair> result = transitionFunction.get(new State("q0"), new StackAlphabetSymbol("S"),
				Optional.of(new InputAlphabetSymbol("a")));

		assertThat(result).containsExactly(expectedResult);
	}

	@Test
	public void shouldReturnEmptySetWithUnkownTransitionInput() {
		TransitionFunction transitionFunction = new TransitionFunction();

		// Inputs
		State s0 = new State("q0");
		Optional<InputAlphabetSymbol> inputSymbol = Optional.of(new InputAlphabetSymbol("a"));
		StackAlphabetSymbol stackAlphabetSymbol = new StackAlphabetSymbol("S");

		assertThat(transitionFunction.get(s0, stackAlphabetSymbol, inputSymbol)).isEmpty();
	}

}
