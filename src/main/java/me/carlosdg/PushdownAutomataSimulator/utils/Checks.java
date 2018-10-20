package me.carlosdg.PushdownAutomataSimulator.utils;

import java.util.Collection;

/**
 * Helper methods
 * 
 * @author Carlos Domínguez García
 *
 */
public class Checks {

	/**
	 * Helper method that throws an IllegalArgumentException if the given object is
	 * null
	 * 
	 * @param toCheck Object to check if it is null
	 * @param message Message to pass to the exception if the object is null
	 * @throws IllegalArgumentException If the given object is null
	 */
	static public void throwIfNull(Object toCheck, String message) throws IllegalArgumentException {
		if (toCheck == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Helper method that throws an IllegalArgumentException if the given collection
	 * or any of its elements is null. This check recurses down to children
	 * containers if any
	 * 
	 * @param toCheck Collection to check if itself or any of its elements is null
	 * @param message Message to pass to the exception if the check turns to be null
	 * @throws IllegalArgumentException If the given collection is null itself or
	 *                                  any of its elements
	 */
	static public void throwIfAnyNullElement(Collection<?> collectionToCheck, String message)
			throws IllegalArgumentException {
		// Throw if container reference is null
		if (collectionToCheck == null) {
			throw new IllegalArgumentException(message);
		}

		// Throw if any element is null or if it has a children container with any null
		// element
		for (Object element : collectionToCheck) {
			if (element == null) {
				throw new IllegalArgumentException(message);
			}
			if (element instanceof Collection<?>) {
				Checks.throwIfAnyNullElement((Collection<?>) element, message);
			}
		}
	}

}
