package me.carlosdg.pda.symbols;

/**
 * Abstract base class to represent any kind of symbol
 *
 * @author Carlos Domínguez García
 */
public abstract class Symbol {
	/**
	 * A string representation of this symbol to know how do we represent this
	 * symbol in the screen. And how does the user represent the symbol to input it
	 * to the application
	 */
	private String representation;

	/**
	 * Creates a new symbol given its string representation
	 *
	 * @param representation String representation of the symbol to create
	 */
	public Symbol(String representation) {
		setRepresentation(representation);
	}

	/** Returns the string representation of this symbol */
	public String getRepresentation() {
		return representation;
	}

	@Override
	public String toString() {
		return getRepresentation();
	}

	/** Sets the string representation of this symbol */
	private void setRepresentation(String representation) {
		this.representation = representation;
	}

}
