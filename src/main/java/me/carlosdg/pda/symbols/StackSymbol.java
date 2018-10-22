package me.carlosdg.pda.symbols;

/**
 * Class to represent stack symbols
 *
 * @author Carlos Domínguez García
 */
public class StackSymbol extends Symbol {
	/** @see me.carlosdg.pda.symbols.Symbol */
	public StackSymbol(String representation) {
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

		return ((StackSymbol) obj).getRepresentation().equals(getRepresentation());
	}
}
