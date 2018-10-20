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
		this.uut = new PdaConfigurationBuilder();
	}

	@Test
	public void shouldHaveOnlyTheStateWhenBuiltFromAState() {
		this.uut.addStateName("q0");
		PdaConfiguration config = this.uut.build();
		
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
		this.uut.addStateName("q0");
		this.uut.addStateName("q1");
		PdaConfiguration config = this.uut.build();
		
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
		this.uut.addStateName(null);
		assertThatIllegalArgumentException().isThrownBy(() -> { this.uut.build(); });
	}
	
	@Test
	public void shouldThrowWhenBuildingWithSomeNullTransition() {
		this.uut.addTransition(null);
		assertThatIllegalArgumentException().isThrownBy(() -> { this.uut.build(); });
	}
	
	@Test
	public void shouldThrowWhenBuildingWithSomeNullTransitionElement() {
		this.uut.addTransition(Arrays.asList("q0", null, "S", "q1", "A", "B"));
		assertThatIllegalArgumentException().isThrownBy(() -> { this.uut.build(); });
	}
	
	@Test
	public void shouldBuildWithOneTransition() {
		List<String> transition = Arrays.asList("q0", "a", "S", "q1", "A", "B");
		this.uut.addTransition(transition);
		PdaConfiguration config = this.uut.build();

		assertThat(config.getTransitions()).containsOnly(transition);
	}

}
