package com.markwryan.adventofcode.day4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 4: The Ideal Stocking Stuffer ---
 * <p/>
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically
 * forward-thinking little girls and boys.
 * <p/>
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5
 * hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you
 * must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
 * <p/>
 * For example:
 * If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes
 * (000001dbbfa...), and it is the lowest such number to do so.
 * If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is
 * 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
 * <p/>
 * Your puzzle input is ckczppom.
 * Created by maryan on 12/4/15.
 */
public class AdventCoinMiner {
    static final String HASH_KEY = "hash";
    static final String ANSWER_KEY = "answer";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<String, String> result = calculateMD5Hash("ckczppom", "00000");
        System.out
                .println("With 5 Zeros, value of " + result.get(ANSWER_KEY) + " gives hash of " + result.get(HASH_KEY));

        result = calculateMD5Hash("ckczppom", "000000");
        System.out
                .println("With 6 Zeros, value of " + result.get(ANSWER_KEY) + " gives hash of " + result.get(HASH_KEY));
    }

    static Map<String, String> calculateMD5Hash(String input, String prefix) throws NoSuchAlgorithmException {
        Map<String, String> result = new HashMap<>();
        MessageDigest md = MessageDigest.getInstance("MD5");
        long i = 1;
        StringBuffer sb = new StringBuffer();

        while (true) {
            sb.delete(0, sb.length());
            md.update((input + i).getBytes());
            byte[] digest = md.digest();

            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            if (sb.indexOf(prefix) == 0) {
                break;
            }
            i++;
        }
        result.put(HASH_KEY, sb.toString());
        result.put(ANSWER_KEY, String.valueOf(i));
        return result;
    }
}
