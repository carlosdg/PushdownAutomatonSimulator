package me.carlosdg.PushdownAutomataSimulator.pda;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PdaConfigurationBuilderTest {

	private PdaConfigurationBuilder uut;

	@Before
	public void init() {
		uut = new PdaConfigurationBuilder();
	}

	@Test
	public void shouldHaveOnlyTheStateWhenBuiltFromAState() {
		uut.addStateName("q0");
		PdaConfiguration config = uut.build();

		assertThat(config.getStateNames()).containsOnly("q0");
		assertThat(config.getAcceptingStateNames()).isEmpty();
		assertThat(config.getTransitions()).isEmpty();
		assertThat(config.getStackAlphabetSymbolNames()).isEmpty();
		assertThat(config.getInputAlphabetSymbolNames()).isEmpty();
		assertThat(config.getStartingStackTopSymbolName()).isEmpty();
		assertThat(config.getStartingStateName()).isEmpty();
	}

	@Test
	public void shouldHaveOnlyTheStateWhenBuiltFromStates() {
		uut.addStateName("q0");
		uut.addStateName("q1");
		PdaConfiguration config = uut.build();

		assertThat(config.getStateNames()).containsOnly("q0", "q1");
		assertThat(config.getAcceptingStateNames()).isEmpty();
		assertThat(config.getTransitions()).isEmpty();
		assertThat(config.getStackAlphabetSymbolNames()).isEmpty();
		assertThat(config.getInputAlphabetSymbolNames()).isEmpty();
		assertThat(config.getStartingStackTopSymbolName()).isEmpty();
		assertThat(config.getStartingStateName()).isEmpty();
	}

	@Test
	public void shouldThrowWhenBuildingWithSomeNullState() {
		uut.addStateName(null);
		assertThatIllegalArgumentException().isThrownBy(() -> {
			uut.build();
		});
	}

	@Test
	public void shouldThrowWhenBuildingWithSomeNullTransition() {
		uut.addTransition(null);
		assertThatIllegalArgumentException().isThrownBy(() -> {
			uut.build();
		});
	}

	@Test
	public void shouldThrowWhenBuildingWithSomeNullTransitionElement() {
		uut.addTransition(Arrays.asList("q0", null, "S", "q1", "A", "B"));
		assertThatIllegalArgumentException().isThrownBy(() -> {
			uut.build();
		});
	}

	@Test
	public void shouldBuildWithOneTransition() {
		List<String> transition = Arrays.asList("q0", "a", "S", "q1", "A", "B");
		uut.addTransition(transition);
		PdaConfiguration config = uut.build();

		assertThat(config.getTransitions()).containsOnly(transition);
	}

}
