package me.carlosdg.pda.simulator.input_tape;

import java.util.List;

import me.carlosdg.pda.sets.InputAlphabet;
import me.carlosdg.pda.symbols.InputAlphabetSymbol;

/**
 * Input tape class that holds an input word to be processed by the PDA
 * 
 * @author Carlos Domínguez García
 */
public class InputTape {

	/** Input alphabet */
	InputAlphabet alphabet;
	/** Current word in the tape */
	List<InputAlphabetSymbol> word;
	/** Position of the next symbol to return */
	int currentSymbolIndex = 0;

	/** Instantiates an InputTape object with the given input alphabet */
	public InputTape(InputAlphabet alphabet) {
		this.alphabet = alphabet;
	}

	/** Instantiates an InputTape object as a copy of the given input tape */
	public InputTape(InputTape other) {
		alphabet = other.alphabet;
		word = other.word;
		currentSymbolIndex = other.currentSymbolIndex;
	}

	/**
	 * Sets the word in the input tape to the given one. Throws if there is a symbol
	 * that doesn't belong to the alphabet
	 */
	public void setInput(List<InputAlphabetSymbol> newWord) {
		for (InputAlphabetSymbol symbol : newWord) {
			if (!alphabet.has(symbol)) {
				throw new IllegalArgumentException(
						"Invalid symbol in input word, it does not belong to the input alphabet:'" + symbol + "'");
			}
		}
		word = newWord;
		currentSymbolIndex = 0;
	}

	/**
	 * Returns the next symbol from the tape. Throws if there are no more symbols to
	 * be read
	 */
	public InputAlphabetSymbol nextSymbol() throws IndexOutOfBoundsException {
		if (currentSymbolIndex >= word.size()) {
			throw new IndexOutOfBoundsException("There are no more symbols in the input string");
		}
		InputAlphabetSymbol symbol = word.get(currentSymbolIndex);
		currentSymbolIndex += 1;

		return symbol;
	}

	/** Returns whether all the input symbols have been read or not */
	public boolean empty() {
		return currentSymbolIndex >= word.size();
	}
}
