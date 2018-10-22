package me.carlosdg.pda.symbols;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class InputAndStackSymbolsTest {

	@Test
	public void inputSymbolShouldThrowWithEmptyStringRepresentation() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new InputSymbol(Symbol.EMPTY_STRING_REPR);
		}).withMessageContaining("Cannot create a symbol").withMessageContaining("empty string");
	}

	@Test
	public void stackSymbolShouldThrowWithEmptyStringRepresentation() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new StackSymbol(Symbol.EMPTY_STRING_REPR);
		}).withMessageContaining("Cannot create a symbol").withMessageContaining("empty string");
	}

	@Test
	public void shouldBeAbleToCreateInputSymbolsFromSingleCharacterString() {
		String symbolRepresentation = "s";
		Symbol symbol = new InputSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void shouldBeAbleToCreateStackSymbolsFromSingleCharacterString() {
		String symbolRepresentation = "s";
		Symbol symbol = new StackSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void shouldBeAbleToCreateInputSymbolsFromMultipleCharactersString() {
		String symbolRepresentation = "q0";
		Symbol symbol = new InputSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void shouldBeAbleToCreateStackSymbolsFromMultipleCharactersString() {
		String symbolRepresentation = "s0";
		Symbol symbol = new StackSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void inputSymbolShouldNotBeEqualToStackSymbolWithDifferentRepresentation() {
		Symbol iSymbol = new InputSymbol("q");
		Symbol sSymbol = new StackSymbol("S");
		assertThat(iSymbol).isNotEqualTo(sSymbol);
	}

	@Test
	public void inputSymbolShouldNotBeEqualToStackSymbolWithSameRepresentation() {
		Symbol iSymbol = new InputSymbol("a");
		Symbol sSymbol = new StackSymbol("a");
		assertThat(iSymbol).isNotEqualTo(sSymbol);
	}

	@Test
	public void inputSymbolShouldNotBeEqualToInputSymbolWithDifferentRepresentation() {
		Symbol s1 = new InputSymbol("a");
		Symbol s2 = new InputSymbol("b");
		assertThat(s1).isNotEqualTo(s2);
	}

	@Test
	public void inputSymbolShouldBeEqualToInputSymbolWithSameRepresentation() {
		Symbol s1 = new InputSymbol("a");
		Symbol s2 = new InputSymbol("a");
		assertThat(s1).isEqualTo(s2);
	}

	@Test
	public void inputSymbolShouldBeEqualToInputSymbolWithSameReference() {
		Symbol s1 = new InputSymbol("a");
		Symbol s2 = s1;
		assertThat(s1).isEqualTo(s2);
	}

	@Test
	public void stackSymbolShouldNotBeEqualToStackSymbolWithDifferentRepresentation() {
		Symbol s1 = new StackSymbol("S");
		Symbol s2 = new StackSymbol("R");
		assertThat(s1).isNotEqualTo(s2);
	}

	@Test
	public void stackSymbolShouldBeEqualToStackSymbolWithSameRepresentation() {
		Symbol s1 = new StackSymbol("S");
		Symbol s2 = new StackSymbol("S");
		assertThat(s1).isEqualTo(s2);
	}

	@Test
	public void stackSymbolShouldBeEqualToStackSymbolWithSameReference() {
		Symbol s1 = new StackSymbol("S");
		Symbol s2 = s1;
		assertThat(s1).isEqualTo(s2);
	}
}
