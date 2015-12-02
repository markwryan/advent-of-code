package com.markwryan.adventofcode.day2;

import java.util.Arrays;

import java.util.List;

/**
 * Created by mark on 12/2/15.
 */
public class Present {
    final int length;
    final int width;
    final int height;

    public Present(String dims) {
        String[] dimensions = dims.split("x");

        if(dimensions.length != 3) {
            throw new IllegalArgumentException("Dimensions must be in the form of LxWxH");
        }

        this.length = Integer.parseInt(dimensions[0]);
        this.width = Integer.parseInt(dimensions[1]);
        this.height = Integer.parseInt(dimensions[2]);
    }

    int getSurfaceArea() {
        return (2 * length * width) + (2 * width * height) + (2 * height * length);
    }

    int getAreaOfSmallestSide() {
        List<Integer> dimensions = getSortedSides();
        return dimensions.get(0) * dimensions.get(1);
    }

    int getPerimeterOfSmallestSide() {
        List<Integer> dimensions = getSortedSides();
        return (2 * dimensions.get(0)) + (2 * dimensions.get(1));
    }

    int getCubicVolume() {
        return length * width * height;
    }

    private List<Integer> getSortedSides() {
        List<Integer> dimensions = Arrays.asList(length, width, height);
        dimensions.sort((a, b) -> Integer.compare(a,b));
        return dimensions;
    }

    public int getNeededWrappingPaperInSquareFeet() {
        return getSurfaceArea() + getAreaOfSmallestSide();
    }

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
