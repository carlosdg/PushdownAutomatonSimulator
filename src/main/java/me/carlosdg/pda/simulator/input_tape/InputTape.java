package me.carlosdg.pda.simulator.input_tape;

import me.carlosdg.pda.sets.InputAlphabet;
import me.carlosdg.pda.symbols.InputAlphabetSymbol;
import me.carlosdg.pda.word.Word;

/**
 * Input tape class that holds an input word to be processed by the PDA
 *
 * @author Carlos Domínguez García
 */
public class InputTape {

	/** Input alphabet */
	InputAlphabet alphabet;
	/** Current word in the tape */
	Word word;
	/** Position of the next symbol to return */
	int currentSymbolIndex = 0;

	/** Instantiates an InputTape object with the given input alphabet */
	public InputTape(InputAlphabet alphabet) {
		this.alphabet = alphabet;
	}

	/**
	 * Sets the word in the input tape to the given one. Throws if there is a symbol
	 * that doesn't belong to the alphabet
	 */
	public void setInput(Word newWord) {
		if (!newWord.areAllSymbolsPartOfAlphabet(alphabet)) {
			throw new IllegalArgumentException(
					"Invalid word, it has a symbol that does not belong to the input alphabet:'" + newWord + "'");
		}
		word = newWord;
		currentSymbolIndex = 0;
	}

	/**
	 * Returns the next symbol from the tape but it doesn't consume it. Throws if
	 * there are no more symbols to be read
	 */
	public InputAlphabetSymbol peek() throws IndexOutOfBoundsException {
		if (currentSymbolIndex >= word.size()) {
			throw new IndexOutOfBoundsException("There are no more symbols in the input string");
		}
		InputAlphabetSymbol symbol = word.get(currentSymbolIndex);

		return symbol;
	}

	/**
	 * Returns the next symbol from the tape. Throws if there are no more symbols to
	 * be read
	 */
	public InputAlphabetSymbol consumeInput() throws IndexOutOfBoundsException {
		InputAlphabetSymbol symbol = peek();
		currentSymbolIndex += 1;

		return symbol;
	}

	public void revertConsumption() {
		if (currentSymbolIndex <= 0) {
			throw new IndexOutOfBoundsException("There are no more symbols in the input string");
		}
		currentSymbolIndex -= 1;
	}

	/** Returns whether all the input symbols have been read or not */
	public boolean isEmpty() {
		return currentSymbolIndex >= word.size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = currentSymbolIndex; i < word.size(); ++i) {
			builder.append(word.get(i));
			builder.append(" ");
		}

		return builder.toString();
	}
}
