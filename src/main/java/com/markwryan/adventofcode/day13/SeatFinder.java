package com.markwryan.adventofcode.day13;

import com.google.common.collect.Collections2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mark on 12/13/15.
 */
public class SeatFinder {

    private Set<String> people;
    private List<Setting> arrangements;

    public SeatFinder() {
        arrangements = new ArrayList<>();
        people = new HashSet<>();

    }

    public static void main(String[] args) throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day13.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input.getPath()));
        SeatFinder finder = new SeatFinder();
        List<Integer> result = finder.getBestSeatOption(reader, false);
        System.out.println(result.get(result.size() - 1));

        SeatFinder finder2 = new SeatFinder();
        BufferedReader reader2 = new BufferedReader(new FileReader(input.getPath()));
        List<Integer> result2 = finder2.getBestSeatOption(reader2, true);
        System.out.println(result2.get(result2.size() -1));
    }

    List<Integer> getBestSeatOption(BufferedReader reader, boolean includeSelf) {
        List<Integer> result = new ArrayList<>();

        reader.lines().forEach(line -> readSeats(line));
        if(includeSelf) {
            addSelf();
        }
        Collection<List<String>> permutations = Collections2.permutations(people);

        for (List<String> arrangement : permutations) {
            int total = 0;

            for (int i = 0; i < arrangement.size() -1; i++) {
                String p1 = arrangement.get(i);
                String p2 = arrangement.get(i + 1);
                total += getHappiness(p1, p2);
            }
            total += getHappiness(arrangement.get(0), arrangement.get(arrangement.size() - 1));
            result.add(total);
        }

        return result.stream().sorted().collect(Collectors.toList());
    }

    int getHappiness(String p1, String p2) {
        for (Setting s : arrangements) {
            if (s.neighbors[0].equals(p1) || s.neighbors[0].equals(p2)) {
                if (s.neighbors[1].equals(p1) || s.neighbors[1].equals(p2)) {
                    return s.happiness;
                }
            }
        }
        throw new IllegalArgumentException("Setting is not found: " + p1 + " to " + p2);
    }

    void addSelf() {
        for(String person : people) {
            String[] p = new String[2];
            p[0] = person;
            p[1] = "Mark";
            arrangements.add(new Setting(p, 0));
        }
        people.add("Mark");
    }


    void readSeats(String input) {
        input = input.replace("gain ", "");
        input = input.replace("lose ", "-");
        input = input.replace(".", "");
        String[] line = input.split(" ");

        int happiness = Integer.parseInt(line[2]);
        String[] p = new String[2];
        p[0] = line[0];
        p[1] = line[line.length-1];

        boolean exists = false;
        for(Setting s : arrangements) {
            if(s.neighbors[0].equals(p[0]) || s.neighbors[0].equals(p[1])) {
                if(s.neighbors[1].equals(p[0]) || s.neighbors[1].equals(p[1])) {
                    s.happiness += happiness;
                    exists = true;
                }
            }
        }

        if(!exists) {
            Setting setting = new Setting(p, happiness);
            arrangements.add(setting);
        }

        if(!people.contains(p[0])) {
            people.add(p[0]);
        }
        if(!people.contains(p[1])) {
            people.add(p[1]);
        }
    }


    private class Setting {
        String[] neighbors;
        int happiness;

        public Setting(String[] n, int h) {
            neighbors = n;
            happiness = h;
        }
    }
}
