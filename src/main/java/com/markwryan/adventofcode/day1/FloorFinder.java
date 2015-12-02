package com.markwryan.adventofcode.day1;

import com.markwryan.adventofcode.util.UserInputUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 1: Not Quite Lisp ---
 *
 * Santa was hoping for a white Christmas, but his weather machine's "snow" function is powered by stars, and he's fresh
 * out! To save Christmas, he needs you to collect fifty stars by December 25th.
 *
 * Collect stars by helping Santa solve puzzles. Two puzzles will be made available on each day in the advent calendar;
 * the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 *
 * Here's an easy puzzle to warm you up.
 *
 * Santa is trying to deliver presents in a large apartment building, but he can't find the right floor - the directions
 * he got are a little confusing. He starts on the ground floor (floor 0) and then follows the instructions one
 * character at a time.
 *
 * An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should go down
 * one floor.
 *
 * The apartment building is very tall, and the basement is very deep; he will never find the top or bottom floors.
 *
 * For example:
 *
 * (()) and ()() both result in floor 0.
 * ((( and (()(()( both result in floor 3.
 * ))((((( also results in floor 3.
 * ()) and ))( both result in floor -1 (the first basement level).
 * ))) and )())()) both result in floor -3.
 * To what floor do the instructions take Santa?
 *
 * --- Part Two ---
 * Now, given the same instructions, find the position of the first character that causes him to enter the basement
 * (floor -1). The first character in the instructions has position 1, the second character has position 2, and so on.
 *
 * For example:
 *      ) causes him to enter the basement at character position 1.
 *      ()()) causes him to enter the basement at character position 5.
 *
 * What is the position of the character that causes Santa to first enter the basement?
 *
 * source: http://adventofcode.com/day/1
 */
public class FloorFinder {
    final static String FLOOR_KEY = "floor";
    final static String BASEMENT_KEY = "basement";

    public static void main(String[] args) {
        String userInput = UserInputUtils.getUserInput("Please Enter the input: ");
        Map<String, Integer> results = calculateFloor(userInput.toCharArray());
        System.out.println("Floor: " + results.get(FLOOR_KEY));
        System.out.println("Position First Entered the Basement: " + results.get(BASEMENT_KEY));
    }

    /**
     * Loop through characters, map to their floor directions and add their values to the total. Return the total, which
     * is the current floor.
     *
     * @param inputs - user input as a character array
     * @return Map of current floor and the position in which we first entered the basement.
     */
    static Map<String, Integer> calculateFloor(char[] inputs) {
        Map<String, Integer> results = new HashMap<String, Integer>();
        int currentFloor = 0;
        int basementPosition = -1;

        for(int i = 0; i < inputs.length; i++) {
            char input = inputs[i];
            FloorDirection direction = FloorDirection.fromSymbol(input);
            currentFloor += direction.value;

            // If we entered the basement, set the basement position.
            // Adding +1 to the index, since position is 1-based unlike indexes.
            if(currentFloor == -1 && basementPosition < 0) {
                basementPosition = i + 1;
            }
        }
        results.put(FLOOR_KEY, currentFloor);
        results.put(BASEMENT_KEY, basementPosition);

        return results;
    }

    /**
     * FloorDirection tie together a direction (up, down) with the symbol representation of that direction, as well as
     * its numerical value in the case of the puzzle.
     */
    private static enum FloorDirection {
        UP('(',1),
        DOWN(')',-1);

        private final char symbol;
        private final int value;

        FloorDirection(final char symbol, final int value) {
            this.symbol = symbol;
            this.value = value;
        }

        //Convert from Symbol to FloorDirection
        static FloorDirection fromSymbol(final char symbol) {
            for(FloorDirection direction : FloorDirection.values()) {
                if(direction.symbol == symbol) {
                    return  direction;
                }
            }
            throw new IllegalArgumentException("Symbol does not match any direction.");
        }
    }

}
