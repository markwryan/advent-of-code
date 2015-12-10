package com.markwryan.adventofcode.day8;


import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mark on 12/8/15.
 */
public class SantasList {
    public static void main(String[] args) throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day8.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input.getPath()));
        BufferedReader reader2 = new BufferedReader(new FileReader(input.getPath()));
        BufferedReader reader3 = new BufferedReader(new FileReader(input.getPath()));

        int memoryLength = countMemory(reader);
        int literalLength = countLiteral(reader2);
        int escapedLength = escapeLiteral(reader3);
        System.out.println("Length: " + (memoryLength - literalLength));
        System.out.println("Length: " + (escapedLength - memoryLength));
    }

    static int countMemory(BufferedReader reader) {
        AtomicInteger count = new AtomicInteger();
        reader.lines().forEach(line -> {
            count.set(count.intValue() + line.length());

        });
        return count.get();
    }

    static int countLiteral(BufferedReader reader) {
        AtomicInteger count = new AtomicInteger();
        reader.lines().forEach(line -> {
            String l = line.replaceAll("\\\\\\\\", "a")
                    .replaceAll("\\\\\"", "a")
                    .replaceAll("\\\\[x][0-9abcdeffABCDEFF][0-9abcdeffABCDEF]", "a")
                    .replaceAll("\"", "");
            count.set(count.get() + l.length());
        });

        return count.get();
    }

    static int escapeLiteral(BufferedReader reader) {
        AtomicInteger count = new AtomicInteger();
        reader.lines().forEach(line -> {
            String l = line.replaceAll("\\\\\\\\", "aaaa")
                    .replaceAll("\\\\\"", "aaaa")
                    .replaceAll("\\\\[x][0-9abcdeffABCDEFF][0-9abcdeffABCDEF]", "aaaaa")
                    .replaceAll("\"", "aaa");
            System.out.println(l.length());
            count.set(count.get() + l.length());
        });

        return count.get();
    }



}
