package com.markwryan.adventofcode.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mark on 12/2/15.
 */
public class WrappingPaperCalculator {

    public static void main(String[] args) throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day2.txt");

        BufferedReader reader = new BufferedReader(new FileReader(input.getPath()));
        calculateWrappingPaperDimensions(reader);

    }

    static void calculateWrappingPaperDimensions(BufferedReader reader) {
        int wrappingPaperTotal = 0;
        int ribbonTotal = 0;
        List<Present> presents = reader.lines().map(l -> new Present(l)).collect(Collectors.toList());
        for (Present present : presents) {
            wrappingPaperTotal += present.getNeededWrappingPaperInSquareFeet();
            ribbonTotal += present.getNeededRibbonInFeet();
        }

        System.out.println("You will need " + wrappingPaperTotal + " square feet of paper.");
        System.out.println("You will need " + ribbonTotal + " feet of ribbon.");
    }
}
