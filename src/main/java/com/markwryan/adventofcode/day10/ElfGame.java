package com.markwryan.adventofcode.day10;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 12/10/15.
 */
public class ElfGame {
    public static void main(String[] args) {
        String input = "3113322113";
        simulateGame(50, input);
    }

    static void simulateGame(int rounds, String input) {

        List<Integer> values = new ArrayList<>();
        char[] split = input.toCharArray();
        for(char entry : split) {
            int val = Integer.parseInt(String.valueOf(entry));
            values.add(val);
        }

        for(int i = 0; i < rounds; i++) {
            List<Integer> result = new ArrayList<>();
            Integer[] choices = values.toArray(new Integer[values.size()]);
            int current = -1;
            int count = 0;

            for(int j = 0; j < choices.length; j++) {
                int choice = choices[j];
                if(current == -1) {
                    current = choice;
                    count++;
                }
                else if(current == choice) {
                    count++;
                }
                else {
                    result.add(count);
                    result.add(current);
                    current = choice;
                    count = 1;
                }

                if(j == choices.length -1) {

                    result.add(count);
                    result.add(choice);

                    values.clear();
                    values.addAll(result);
                }
            }
        }
        System.out.println(values.size());
    }
}
