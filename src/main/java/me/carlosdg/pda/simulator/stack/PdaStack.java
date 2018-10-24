package me.carlosdg.pda.simulator.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.List;
import me.carlosdg.pda.sets.StackAlphabet;
import me.carlosdg.pda.symbols.StackAlphabetSymbol;

/**
 * PDA Stack
 *
 * @author Carlos Domínguez García
 */
public class PdaStack {

	/** Stack alphabet (set of allowed symbols to be on the stack) */
	private StackAlphabet alphabet;
	/** Stack */
	private Deque<StackAlphabetSymbol> stack = new ArrayDeque<>();
	/** Initial stack top */
	private StackAlphabetSymbol initialSymbol;

	/** Creates a PDA Stack with the given alphabet and initial top symbol */
	public PdaStack(StackAlphabet alphabet, StackAlphabetSymbol initialSymbol) {
		this.alphabet = alphabet;
		setInitialSymbol(initialSymbol);
		push(initialSymbol);
	}

	/** Creates this PDA Stack from copying the given stack */
	public PdaStack(PdaStack other) {
		alphabet = other.alphabet;
		setInitialSymbol(other.initialSymbol);
		stack.addAll(other.stack);
	}

	/** Returns whether the stack is empty or not */
	public boolean empty() {
		return stack.isEmpty();
	}

	/**
	 * Pushes the given symbol to the stack, throws if the given symbol is not part
	 * of the the alphabet
	 */
	public void push(StackAlphabetSymbol newSymbol) throws IllegalArgumentException {
		if (!alphabet.has(newSymbol)) {
			throw new IllegalArgumentException(
					"Asked to push Symbol(" + newSymbol + ") to the stack but it doesn't belong to the stack alphabet");
		}
		stack.push(newSymbol);
	}

	/**
	 * Pushes the given list of symbol to the stack, throws if any symbol is part of
	 * the the alphabet
	 */
	public void push(List<StackAlphabetSymbol> newSymbols) {
		for (StackAlphabetSymbol symbol : newSymbols) {
			push(symbol);
		}
	}

	/** Removes the top symbol from the stack and returns it */
	public StackAlphabetSymbol pop() throws EmptyStackException {
		return stack.pop();
	}

	/** Removes all symbols from the stack and inserts the given symbol */
	public void reset() {
		stack.clear();
		push(initialSymbol);
	}

	/** Sets the initial symbol. Throws if it doesn't belong to the alphabet */
	private void setInitialSymbol(StackAlphabetSymbol newInitialSymbol) {
		if (!alphabet.has(newInitialSymbol)) {
			throw new IllegalArgumentException(
					"Invalid initial stack symbol '" + newInitialSymbol + "'. It doesn't belong to the stack alphabet");
		}
		initialSymbol = newInitialSymbol;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (StackAlphabetSymbol symbol : stack) {
			builder.append(symbol);
			builder.append(" ");
		}

		return builder.toString();
	}

}
