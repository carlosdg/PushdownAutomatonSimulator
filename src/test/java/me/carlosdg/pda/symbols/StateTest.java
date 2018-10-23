package me.carlosdg.pda.symbols;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class StateTest {

	@Test
	public void shouldBeConstructedFromAStringRepresentation() {
		String repr = "q0";
		State state = new State(repr);
		assertThat(state.getRepresentation()).isEqualTo(repr);
	}

	@Test
	public void shouldBeEqualToAReferenceToItself() {
		String repr = "q0";
		State state = new State(repr);
		assertThat(state).isEqualTo(state);
	}

	@Test
	public void shouldBeEqualToAnotherStateWithTheSameStringRepresentation() {
		String repr = "q0";
		State s0 = new State(repr);
		State s1 = new State(repr);
		assertThat(s0).isEqualTo(s1);
	}

	@Test
	public void shouldBeDifferentToAnotherStateWithDifferentStringRepresentation() {
		State s0 = new State("p");
		State s1 = new State("q");
		assertThat(s0).isNotEqualTo(s1);
	}

	@Test
	public void shouldBeDifferentToAInputAlphabetSymbolWithTheSameStringRepresentation() {
		Symbol s0 = new State("p");
		Symbol s1 = new InputAlphabetSymbol("p");
		assertThat(s0).isNotEqualTo(s1);
	}

	@Test
	public void shouldBeDifferentToAStackAlphabetSymbolWithTheSameStringRepresentation() {
		Symbol s0 = new State("p");
		Symbol s1 = new StackAlphabetSymbol("p");
		assertThat(s0).isNotEqualTo(s1);
	}

	@Test
	public void shouldBeDifferentToAInputAlphabetSymbolWithDifferentStringRepresentation() {
		Symbol s0 = new State("p");
		Symbol s1 = new InputAlphabetSymbol("q");
		assertThat(s0).isNotEqualTo(s1);
	}

	@Test
	public void shouldBeDifferentToAStackAlphabetSymbolWithDifferentStringRepresentation() {
		Symbol s0 = new State("p");
		Symbol s1 = new StackAlphabetSymbol("q");
		assertThat(s0).isNotEqualTo(s1);
	}

}
