package me.carlosdg.pda.symbols;

/**
 * Class to represent stack symbols
 *
 * @author Carlos Domínguez García
 */
public class StackAlphabetSymbol extends AlphabetSymbol {
	/** @see me.carlosdg.pda.symbols.AlphabetSymbol */
	public StackAlphabetSymbol(String representation) {
		super(representation);
	}

	@Override
	public int hashCode() {
		return 31 * getClass().hashCode() + getRepresentation().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		return ((StackAlphabetSymbol) obj).getRepresentation().equals(getRepresentation());
	}
}
