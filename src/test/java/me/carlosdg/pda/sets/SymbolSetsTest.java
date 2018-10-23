package me.carlosdg.pda.sets;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;

import me.carlosdg.pda.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.pda.sets.exceptions.SymbolNotFoundInSetException;
import me.carlosdg.pda.symbols.State;

public class SymbolSetsTest {

	@Test
	public void shouldContainAllGivenStrings() throws DuplicatedStringInSetException, SymbolNotFoundInSetException {
		Collection<String> symbolRepresentations = Arrays.asList("q0", "q1", "q2");
		StateSet stateSet = new StateSet(symbolRepresentations);

		assertThat(stateSet.getSymbol("q0")).isEqualTo(new State("q0"));
		assertThat(stateSet.getSymbol("q1")).isEqualTo(new State("q1"));
		assertThat(stateSet.getSymbol("q2")).isEqualTo(new State("q2"));
	}

	@Test
	public void shouldThrowWithOneDuplicateString() {
		assertThatThrownBy(() -> new StateSet(Arrays.asList("q0", "q0")))
				.isInstanceOf(DuplicatedStringInSetException.class).hasMessageContaining("q0");
	}

	@Test
	public void shouldThrowWithOneDuplicateOnALargeSet() {
		assertThatThrownBy(() -> new StateSet(Arrays.asList("q0", "q1", "q2", "q3", "q0", "q4", "q5")))
				.isInstanceOf(DuplicatedStringInSetException.class).hasMessageContaining("q0");
	}

	@Test
	public void shouldThrowWhenAskingForAStringRepresentingASymbolNotInTheSet() throws DuplicatedStringInSetException {
		StateSet set = new StateSet(Arrays.asList("q0", "q1", "q2", "q3", "q6", "q4", "q5"));

		assertThatThrownBy(() -> set.getSymbol("q")).isInstanceOf(SymbolNotFoundInSetException.class)
				.hasMessageContaining("q");
	}
}
