package com.markwryan.adventofcode.day11;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mark on 12/11/15.
 */
public class PasswordFinder {
    static List<Character> ALPHABET = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z');

    static List<Character> ILLEGAL_CHAR = Arrays.asList('i', 'o', 'l');

    public static void main(String[] args) {
        System.out.println(findNextPassword("cqjxjnds".toCharArray()));
        System.out.println(findNextPassword(findNextPassword("cqjxjnds".toCharArray()).toCharArray()));
    }

    static String findNextPassword(char[] input) {
        boolean valid = false;

        while (!valid) {
            if (input[input.length - 1] == 'z') {
                for (int i = input.length - 1; i >= 0; i--) {
                    if (input[i] != 'z') {
                        input[i] = ALPHABET.get(ALPHABET.indexOf(input[i]) + 1);
                        break;
                    } else {
                        input[i] = 'a';
                    }
                }
            } else {
                input[input.length - 1] = ALPHABET.get(ALPHABET.indexOf(input[input.length - 1]) + 1);
            }
            valid = isValid(input);
        }

        return new String(input);
    }

    static boolean isValid(char[] password) {
        String fullPwd = new String(password);
        Pattern duplicate = Pattern.compile("(\\w)\\1");
        Matcher matcher = duplicate.matcher(fullPwd);

        for (char p : password) {
            if (ILLEGAL_CHAR.contains(p)) {
                return false;
            }
        }
        int countDupe = 0;
        while (matcher.find()) {
            countDupe++;
        }
        if (countDupe < 2) {
            return false;
        }

        boolean foundGroupOfThree = false;

        for (int i = 0; i < password.length - 2; i++) {
            int current = ALPHABET.indexOf(password[i]);
            if (current != 'y' && current != 'z') {
                int second = ALPHABET.indexOf(password[i + 1]);
                int third = ALPHABET.indexOf(password[i + 2]);


                if (second == current + 1 && third == current + 2) {
                    foundGroupOfThree = true;
                    break;
                }
            }
        }

        if (!foundGroupOfThree) {
            return false;
        }

        return true;
    }


}
