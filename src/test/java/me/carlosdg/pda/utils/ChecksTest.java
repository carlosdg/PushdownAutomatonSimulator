package me.carlosdg.pda.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import me.carlosdg.pda.utils.Checks;

public class ChecksTest {

	@Test
	public void throwsIfObjectIsNull() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			Checks.throwIfNull(null, "Object is null");
		}).withMessage("Object is null");
	}

	@Test
	public void noThrowIfObjectIsNotNull() {
		try {
			Checks.throwIfNull("This object is a string", "Object is null");
			assertTrue(true);
		} catch (Exception e) {
			fail("An exception was thrown with Checks.throwIfNull with a non null string argument");
		}
	}

	@Test
	public void throwsIfContainerIsNull() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			Checks.throwIfAnyNullElement(null, "Container is null");
		}).withMessage("Container is null");
	}

	@Test
	public void noThrowIfContainerIsNotNull() {
		try {
			Checks.throwIfAnyNullElement(new ArrayList<String>(), "Container is null");
			assertTrue(true);
		} catch (Exception e) {
			fail("An exception was thrown with Checks.throwIfAnyNullElement with a non null list of strings argument");
		}

	}

	@Test
	public void throwsIfAContainerElementIsNull() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			Checks.throwIfAnyNullElement(Arrays.asList(1, 2, null, 3, 4), "Container is null");
		}).withMessage("Container is null");
	}

	@Test
	public void throwsIfManyContainerElementsAreNull() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			Checks.throwIfAnyNullElement(Arrays.asList(1, 2, null, 3, null, 4), "Container element is null");
		}).withMessage("Container element is null");
	}

	@Test
	public void noThrowIfAllContainerElementsAreNotNull() {
		try {
			Checks.throwIfAnyNullElement(Arrays.asList(1, 2, 3, 4), "Container is null");
			assertTrue(true);
		} catch (Exception e) {
			fail("An exception was thrown with Checks.throwIfAnyNullElement with a List of non null integers");
		}

	}

	@Test
	public void throwsIfAContainerChildrenHasANullElement() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			Checks.throwIfAnyNullElement(Arrays.asList(Arrays.asList(1, null, 2)),
					"Container children has null element");
		}).withMessage("Container children has null element");
	}

	@Test
	public void throwsIfAContainerChildrenHasManyNullElements() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			Checks.throwIfAnyNullElement(Arrays.asList(Arrays.asList(1, null, 2, 3, null, 4, null)),
					"Container children has null elements");
		}).withMessage("Container children has null elements");
	}

	@Test
	public void throwsIfAChildrenContainerIsNull() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			Checks.throwIfAnyNullElement(Arrays.asList(Arrays.asList(1, 2), null, Arrays.asList(3, 4)), "asd");
		}).withMessage("asd");
	}

}
