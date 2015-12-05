package com.markwryan.adventofcode.day5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 * Santa needs help figuring out which strings in his text file are naughty or nice.
 * <p/>
 * A nice string is one with all of the following properties:
 * -    It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
 * -    It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
 * -    It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
 * <p/>
 * --- Part Two ---
 * Realizing the error of his ways, Santa has switched to a better model of determining whether a string is naughty or
 * nice. None of the old rules apply, as they are all clearly ridiculous.
 * <p/>
 * Now, a nice string is one with all of the following properties:
 * -    It contains a pair of any two letters that appears at least twice in the string without overlapping,
 * -        like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
 * -    It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe),
 * -        or even aaa.
 */
class NiceFinder {
    private static final Pattern VOWELS = Pattern.compile("([aeiou])\\w*([aeiou])\\w*([aeiou])");
    private static final Pattern DUPLICATES = Pattern.compile("(\\w)\\1+");
    private static final Pattern BANNED = Pattern.compile("(ab)|(cd)|(pq)|(xy)");
    private static final Pattern DUPLICATE_NON_OVERLAP = Pattern.compile("(\\w)(\\w)(\\w*)\\1\\2+");
    private static final Pattern REPEAT_WITH_GAP = Pattern.compile("(\\w)(\\w)\\1+");

    public static void main(String[] args) throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day5.txt");

        int niceLines = findCountOfNiceStrings(new BufferedReader(new FileReader(input.getPath())), false);
        int actualNiceLines = findCountOfNiceStrings(new BufferedReader(new FileReader(input.getPath())), true);

        System.out.println("We found " + niceLines + " nice Strings.");
        System.out.print("Using the improved formula, there are actually " + actualNiceLines + " nice Strings.");
    }

    private static int findCountOfNiceStrings(BufferedReader reader, boolean useImprovedFormuala) {
        List<String> niceStrings = new ArrayList<>();
        reader.lines().forEach(line -> {
            boolean nice = useImprovedFormuala ? isActuallyNice(line) : isNice(line);
            if (nice) {
                niceStrings.add(line);
            }
        });
        return niceStrings.size();
    }

    static boolean isNice(String test) {
        boolean hasVowels = VOWELS.matcher(test).find();
        boolean hasDuplicates = DUPLICATES.matcher(test).find();
        boolean isBanned = BANNED.matcher(test).find();
        return hasVowels && hasDuplicates && !isBanned;
    }

    static boolean isActuallyNice(String test) {
        boolean hasNonOverlappingDuplicates = DUPLICATE_NON_OVERLAP.matcher(test).find();
        boolean hasRepeatedCharactersWithGap = REPEAT_WITH_GAP.matcher(test).find();
        return hasNonOverlappingDuplicates && hasRepeatedCharactersWithGap;
    }
}
