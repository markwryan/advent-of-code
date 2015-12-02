package com.markwryan.adventofcode.day1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by maryan on 12/2/15.
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
        assertEquals(0, FloorFinder.calculateFloor("(())".toCharArray()));
        assertEquals(0,FloorFinder.calculateFloor("()()".toCharArray()));

        assertEquals(3, FloorFinder.calculateFloor("(((".toCharArray()));
        assertEquals(3, FloorFinder.calculateFloor("(()(()(".toCharArray()));
        assertEquals(3, FloorFinder.calculateFloor("))(((((".toCharArray()));

        assertEquals(-1, FloorFinder.calculateFloor("())".toCharArray()));
        assertEquals(-1, FloorFinder.calculateFloor("))(".toCharArray()));

        assertEquals(-3, FloorFinder.calculateFloor(")))".toCharArray()));
        assertEquals(-3, FloorFinder.calculateFloor(")())())".toCharArray()));
    }

    @Test
    public void testCalculateFloor_CalculatesSingleFloors() {
        assertEquals(1, FloorFinder.calculateFloor("(".toCharArray()));
        assertEquals(-1, FloorFinder.calculateFloor(")".toCharArray()));

    }

    @Test
    public void testCalculateFloor_ReturnsGroundFloorForBlankInput() {
        assertEquals(0, FloorFinder.calculateFloor("".toCharArray()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateFloor_ThrowsIllegalArgumentException_IfBadDataIsPassedIn() {
        FloorFinder.calculateFloor("this shouldn't work".toCharArray());
    }
}
