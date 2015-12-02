package com.markwryan.adventofcode.day1;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Run through given examples of the first puzzle. Also test some other situations which come to mind (bad input, blank
 * input)
 */
public class FloorFinderTest {
    /**
     * (()) and ()() both result in floor 0.
     * ((( and (()(()( both result in floor 3.
     * ))((((( also results in floor 3.
     * ()) and ))( both result in floor -1 (the first basement level).
     * ))) and )())()) both result in floor -3.
     */
    @Test
    public void testCalculateFloor_PassesWithGivenExamples() {
        assertEquals(0, getFloorResult("(())"));
        assertEquals(0, getFloorResult("()()"));

        assertEquals(3, getFloorResult("((("));
        assertEquals(3, getFloorResult("(()(()("));
        assertEquals(3, getFloorResult("))((((("));

        assertEquals(-1, getFloorResult("())"));
        assertEquals(-1, getFloorResult("))("));

        assertEquals(-3, getFloorResult(")))"));
        assertEquals(-3, getFloorResult(")())())"));
    }

    @Test
    public void testCalculateFloor_CalculatesSingleFloors() {
        assertEquals(1, getFloorResult("("));
        assertEquals(-1, getFloorResult(")"));
    }

    @Test
    public void testCalculateFloor_ReturnsGroundFloorForBlankInput() {
        assertEquals(0, getFloorResult(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateFloor_ThrowsIllegalArgumentException_IfBadDataIsPassedIn() {
        getFloorResult("this shouldn't work");
    }

    @Test
    public void testCalculateFloor_ReturnsExampleBasementPositions() {
        assertEquals(1, getBasementPositionResult(")"));
        assertEquals(5, getBasementPositionResult("()())"));
    }

    @Test
    public void testCalculateFloor_HandlesNeverEnteringTheBasement() {
        assertEquals(-1, getBasementPositionResult("((("));
    }

    /**
     * If we enter, leave and re-enter the basement, we should still return the first position we end up in the basement
     */
    @Test
    public void testCalculateFloor_HandlesEnteringAndLeavingTheBasement_MultipleTimes() {
        assertEquals(5, getBasementPositionResult("()())(()))(()))((()"));
    }

    private static int getFloorResult(final String testInput) {
        Map<String, Integer> result = FloorFinder.calculateFloor(testInput.toCharArray());
        return result.get(FloorFinder.FLOOR_KEY);
    }

    private  static  int getBasementPositionResult(final String testInput) {
        Map<String, Integer> result = FloorFinder.calculateFloor(testInput.toCharArray());
        return result.get(FloorFinder.BASEMENT_KEY);
    }
}
