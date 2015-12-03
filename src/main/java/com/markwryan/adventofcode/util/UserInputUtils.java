package com.markwryan.adventofcode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Assuming we will need to get input a lot. Pull out grabbing it from System.in so it can be reused.
 */
public class UserInputUtils {
    /**
     * Prompt a user for a single line of input. Return the user's input as a String.
     *
     * @param prompt - prompt to display to the user
     * @return - User input as a String
     */
    public static String getUserInput(final String prompt) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(prompt);
        String input;
        try {
            input = reader.readLine();
        } catch (IOException ioe) {
            input = getUserInput(prompt);
        }
        return input;
    }
}
