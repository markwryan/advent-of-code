package com.markwryan.adventofcode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by maryan on 12/2/15.
 */
public class UserInputUtils {
    /**
     * Prompt a user for a single line of input. Return the user's input as a String.
     *
     * @param prompt
     * @return
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
