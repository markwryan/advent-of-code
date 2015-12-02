package com.markwryan.adventofcode.day2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark on 12/2/15.
 */
public class PresentTest {

    @Test
    public void testConstructor_FromDimensionString() {
        final Present present = new Present("1x1x2");
        assertEquals(1, present.getLength());
        assertEquals(1, present.getWidth());
        assertEquals(2, present.getHeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ThrowsIllegalArgumentException_IfNotInCorrectForm() {
        new Present("1x2");
    }

    @Test(expected = NumberFormatException.class)
    public void testConstructor_ThrowsNumberFormatException_IfInputsNotNumbers() {
        new Present("1x2xTHREE");
    }

    @Test
    public void testGetSurfaceArea_WorksForGivenExample() {
        final Present example1 = new Present("2x3x4");
        assertEquals(52, example1.getSurfaceArea());

        final Present example2 = new Present("1x1x10");
        assertEquals(42, example2.getSurfaceArea());
    }

    @Test
    public void testGetAreaOfSmallestSide_WorksForGivenExample() {
        final Present example1 = new Present("2x3x4");
        assertEquals(6, example1.getAreaOfSmallestSide());

        final Present example2 = new Present("1x1x10");
        assertEquals(1, example2.getAreaOfSmallestSide());
    }

    @Test
    public void testGetNeededWrappingPaperInSquareFeet_WorksForGivenExample() {
        final Present example1 = new Present(("2x3x4"));
        assertEquals(58, example1.getNeededWrappingPaperInSquareFeet());

        final Present example2 = new Present("1x1x10");
        assertEquals(43, example2.getNeededWrappingPaperInSquareFeet());
    }

    @Test
    public void testGetPerimeterOfSmallestSide_WorksForGivenExamples() {
        final Present example1 = new Present("2x3x4");
        assertEquals(10, example1.getPerimeterOfSmallestSide());

        final Present example2 = new Present("1x1x10");
        assertEquals(4, example2.getPerimeterOfSmallestSide());
    }

    @Test
    public void testGetCubicVolume_WorksForGivenExamples() {
        final Present example1 = new Present("2x3x4");
        assertEquals(24, example1.getCubicVolume());

        final Present example2 = new Present("1x1x10");
        assertEquals(10, example2.getCubicVolume());
    }

    @Test
    public void testGetNeededRibbonInFeet_WorksForGivenExamples() {
        final Present example1 = new Present("2x3x4");
        assertEquals(34, example1.getNeededRibbonInFeet());

        final Present example2 = new Present("1x1x10");
        assertEquals(14, example2.getNeededRibbonInFeet());
    }
}
