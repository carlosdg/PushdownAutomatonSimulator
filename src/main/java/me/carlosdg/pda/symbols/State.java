package me.carlosdg.pda.symbols;

/**
 * State class for representing PDA state
 *
 * @author Carlos Domínguez García
 */
public class State extends Symbol {

	/**
	 * Creates a state instance from its string representation
	 *
	 * @param representation String representation of the state
	 */
	public State(String representation) {
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

		return ((State) obj).getRepresentation().equals(getRepresentation());
	}
}
