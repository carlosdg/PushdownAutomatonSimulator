package me.carlosdg.pda.word;

import java.util.ArrayList;
import java.util.List;

import me.carlosdg.pda.sets.InputAlphabet;
import me.carlosdg.pda.symbols.InputAlphabetSymbol;

public class Word {
	/**
	 * String representation of the Empty string/word. This is not really a symbol
	 * but a string/word with no symbols. However, to represent it in the screen we
	 * need this reserved token
	 */
	public static final String EMPTY_STRING_REPR = ".";

	/** List of symbols that represent the word */
	private List<InputAlphabetSymbol> symbols = new ArrayList<>();

	/** Create a Word from the given list of symbol representations */
	public Word(List<String> symbolRepresentations) {
		if (symbolRepresentations.size() != 1 || !symbolRepresentations.get(0).equals(Word.EMPTY_STRING_REPR)) {
			for (String representation : symbolRepresentations) {
				symbols.add(new InputAlphabetSymbol(representation));
			}
		}
	}

	/** Returns the number of symbols in the word */
	public int size() {
		return symbols.size();
	}

	/** Returns the symbol at the given position */
	public InputAlphabetSymbol get(int position) {
		return symbols.get(position);
	}

	/**
	 * Returns whether all symbols of this word belong to the given alphabet or not
	 */
	public boolean areAllSymbolsPartOfAlphabet(InputAlphabet alphabet) {
		for (InputAlphabetSymbol symbol : symbols) {
			if (!alphabet.has(symbol)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		if (symbols.size() == 0) {
			return Word.EMPTY_STRING_REPR;
		}

		StringBuilder builder = new StringBuilder();
		for (InputAlphabetSymbol symbol : symbols) {
			builder.append(symbol);
			builder.append(" ");
		}

		return builder.toString();
	}

}
