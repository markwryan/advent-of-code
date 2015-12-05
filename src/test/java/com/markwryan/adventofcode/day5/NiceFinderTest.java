package com.markwryan.adventofcode.day5;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test isNice formulas for NiceFinder
 */
public class NiceFinderTest {
    @Test
    public void testIsNice_WorksWithExamples() {
        assertTrue(NiceFinder.isNice("ugknbfddgicrmopn"));
        assertTrue(NiceFinder.isNice("aaa"));

        assertFalse(NiceFinder.isNice("jchzalrnumimnmhp"));
        assertFalse(NiceFinder.isNice("haegwjzuvuyypxyu"));
        assertFalse(NiceFinder.isNice("dvszwmarrgswjxmb"));
    }

    @Test
    public void testIsActuallyNice_WorksWithExamples() {
        assertTrue(NiceFinder.isActuallyNice("qjhvhtzxzqqjkmpb"));
        assertTrue(NiceFinder.isActuallyNice("xxyxx"));

        assertFalse(NiceFinder.isActuallyNice("uurcxstgmygtbstg"));
        assertFalse(NiceFinder.isActuallyNice("ieodomkazucvgmuy"));
    }

}

