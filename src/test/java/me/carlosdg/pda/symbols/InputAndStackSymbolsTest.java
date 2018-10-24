package me.carlosdg.pda.symbols;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import me.carlosdg.pda.word.Word;

public class InputAndStackSymbolsTest {

	@Test
	public void inputSymbolShouldThrowWithEmptyStringRepresentation() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new InputAlphabetSymbol(Word.EMPTY_STRING_REPR);
		}).withMessageContaining("Cannot create a symbol").withMessageContaining("empty string");
	}

	@Test
	public void stackSymbolShouldThrowWithEmptyStringRepresentation() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new StackAlphabetSymbol(Word.EMPTY_STRING_REPR);
		}).withMessageContaining("Cannot create a symbol").withMessageContaining("empty string");
	}

	@Test
	public void shouldBeAbleToCreateInputSymbolsFromSingleCharacterString() {
		String symbolRepresentation = "s";
		AlphabetSymbol symbol = new InputAlphabetSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void shouldBeAbleToCreateStackSymbolsFromSingleCharacterString() {
		String symbolRepresentation = "s";
		AlphabetSymbol symbol = new StackAlphabetSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void shouldBeAbleToCreateInputSymbolsFromMultipleCharactersString() {
		String symbolRepresentation = "q0";
		AlphabetSymbol symbol = new InputAlphabetSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void shouldBeAbleToCreateStackSymbolsFromMultipleCharactersString() {
		String symbolRepresentation = "s0";
		AlphabetSymbol symbol = new StackAlphabetSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void inputSymbolShouldNotBeEqualToStackSymbolWithDifferentRepresentation() {
		AlphabetSymbol iSymbol = new InputAlphabetSymbol("q");
		AlphabetSymbol sSymbol = new StackAlphabetSymbol("S");
		assertThat(iSymbol).isNotEqualTo(sSymbol);
	}

	@Test
	public void inputSymbolShouldNotBeEqualToStackSymbolWithSameRepresentation() {
		AlphabetSymbol iSymbol = new InputAlphabetSymbol("a");
		AlphabetSymbol sSymbol = new StackAlphabetSymbol("a");
		assertThat(iSymbol).isNotEqualTo(sSymbol);
	}

	@Test
	public void inputSymbolShouldNotBeEqualToInputSymbolWithDifferentRepresentation() {
		AlphabetSymbol s1 = new InputAlphabetSymbol("a");
		AlphabetSymbol s2 = new InputAlphabetSymbol("b");
		assertThat(s1).isNotEqualTo(s2);
	}

	@Test
	public void inputSymbolShouldBeEqualToInputSymbolWithSameRepresentation() {
		AlphabetSymbol s1 = new InputAlphabetSymbol("a");
		AlphabetSymbol s2 = new InputAlphabetSymbol("a");
		assertThat(s1).isEqualTo(s2);
	}

	@Test
	public void inputSymbolShouldBeEqualToInputSymbolWithSameReference() {
		AlphabetSymbol s1 = new InputAlphabetSymbol("a");
		AlphabetSymbol s2 = s1;
		assertThat(s1).isEqualTo(s2);
	}

	@Test
	public void stackSymbolShouldNotBeEqualToStackSymbolWithDifferentRepresentation() {
		AlphabetSymbol s1 = new StackAlphabetSymbol("S");
		AlphabetSymbol s2 = new StackAlphabetSymbol("R");
		assertThat(s1).isNotEqualTo(s2);
	}

	@Test
	public void stackSymbolShouldBeEqualToStackSymbolWithSameRepresentation() {
		AlphabetSymbol s1 = new StackAlphabetSymbol("S");
		AlphabetSymbol s2 = new StackAlphabetSymbol("S");
		assertThat(s1).isEqualTo(s2);
	}

	@Test
	public void stackSymbolShouldBeEqualToStackSymbolWithSameReference() {
		AlphabetSymbol s1 = new StackAlphabetSymbol("S");
		AlphabetSymbol s2 = s1;
		assertThat(s1).isEqualTo(s2);
	}
}
