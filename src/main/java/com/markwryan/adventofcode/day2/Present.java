package com.markwryan.adventofcode.day2;

import java.util.Arrays;
import java.util.List;

/**
 * An individual present, which knows it's lenght, width and height. Also containing convenience methods to get
 * information based on the dimensions.
 */
public class Present {
    final int length;
    final int width;
    final int height;

    /**
     * Asserts that the dimensions are a string with LxWxH e.g 2x4x6. Split and assign to the correct lengths.
     *
     * @param dims - the raw string input
     */
    public Present(String dims) {
        String[] dimensions = dims.split("x");

        if (dimensions.length != 3) {
            throw new IllegalArgumentException("Dimensions must be in the form of LxWxH");
        }

        this.length = Integer.parseInt(dimensions[0]);
        this.width = Integer.parseInt(dimensions[1]);
        this.height = Integer.parseInt(dimensions[2]);
    }

    /**
     * The total of the individual areas of each side of the boxed present.
     *
     * @return the total surface area for the present
     */
    int getSurfaceArea() {
        return (2 * length * width) + (2 * width * height) + (2 * height * length);
    }

    /**
     * For the list of lengths sorted from smallest to largest, return the area of the two smallest.
     *
     * @return
     */
    int getAreaOfSmallestSide() {
        List<Integer> dimensions = getSortedSides();
        return dimensions.get(0) * dimensions.get(1);
    }

    /**
     * For the list of lengths sorted from smallest to largest, return the perimiter of the side defined by the two
     * smallest.
     *
     * @return the perimiter of the smallest side.
     */
    int getPerimeterOfSmallestSide() {
        List<Integer> dimensions = getSortedSides();
        return (2 * dimensions.get(0)) + (2 * dimensions.get(1));
    }

    /**
     * @return the cubic volume of the present
     */
    int getCubicVolume() {
        return length * width * height;
    }

    /**
     * Sort side lengths from smallest to largest.
     *
     * @return List of the side lengths sorted.
     */
    private List<Integer> getSortedSides() {
        List<Integer> dimensions = Arrays.asList(length, width, height);
        dimensions.sort((a, b) -> Integer.compare(a, b));
        return dimensions;
    }

    /**
     * Calculate the needed wrapping paper by the given formula.
     *
     * @return total paper needed in square feet.
     */
    public int getNeededWrappingPaperInSquareFeet() {
        return getSurfaceArea() + getAreaOfSmallestSide();
    }

    /**
     * Calculate the ribbon needed to tie around the present by the given formula.
     *
     * @return the ribbon needed in feet.
     */
    public int getNeededRibbonInFeet() {
        return getPerimeterOfSmallestSide() + getCubicVolume();
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
