package com.markwryan.adventofcode.day16;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mark on 12/20/15.
 */
public class SueFinder {
    List<Sue> sues;

    public SueFinder() throws FileNotFoundException {
        sues = new ArrayList<>();
        populateSues();
    }

    public static void main(String[] args) throws FileNotFoundException {
        SueFinder finder = new SueFinder();
        finder.populateSues();
        System.out.println(finder.findSue());
    }

    private int findSue() {
        Sue searchSue = new Sue(501, 3, 7, 2, 3, 0, 0, 5, 3, 2, 1);
        int bestConfidence = 0;
        Sue bestCandidate = null;

        for (Sue sue : sues) {
            int confidence = sue.score(searchSue);
            if (confidence > bestConfidence) {
                bestConfidence = confidence;
                bestCandidate = sue;
            }
        }
        return bestCandidate.id;
    }

    private void populateSues() throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day16.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input.getPath()));
        AtomicInteger i = new AtomicInteger(1);
        reader.lines().forEach(line -> {
            Sue s = new Sue(i.get(), line);
            sues.add(s);
            i.getAndAdd(1);
        });
    }

    private class Sue {
        int id;
        int children;
        int cats;
        int samoyeds;
        int pomeranians;
        int akitas;
        int vizslas;
        int goldfish;
        int trees;
        int cars;
        int perfumes;

        public Sue(int id, int children, int cats, int samoyeds, int pomeranians, int akitas, int vizslas, int goldfish,
                   int trees, int cars, int perfumes) {
            this.id = id;
            this.children = children;
            this.cats = cats;
            this.samoyeds = samoyeds;
            this.pomeranians = pomeranians;
            this.akitas = akitas;
            this.vizslas = vizslas;
            this.goldfish = goldfish;
            this.trees = trees;
            this.cars = cars;
            this.perfumes = perfumes;
        }

        public Sue(int id, String line) {

            this.id = id;
            this.children = -1;
            this.cats = -1;
            this.samoyeds = -1;
            this.pomeranians = -1;
            this.akitas = -1;
            this.vizslas = -1;
            this.goldfish = -1;
            this.trees = -1;
            this.cars = -1;
            this.perfumes = -1;
            int firstColon = line.indexOf(":");
            String parts = line.substring(firstColon + 1).trim();
            String[] things = parts.split(",");
            for (String thing : things) {
                String[] t = thing.split(":");
                String attr = t[0];

                int amount;

                try {
                    amount = Integer.parseInt(t[1].trim());

                } catch (NumberFormatException nfe) {
                    System.out.println(t[1]);
                    amount = 0;
                }
                if (attr.contains("children")) {
                    this.children = amount;
                } else if (attr.contains("cats")) {
                    this.cats = amount;
                } else if (attr.contains("samoyeds")) {
                    this.samoyeds = amount;
                } else if (attr.contains("pomeranians")) {
                    this.pomeranians = amount;

                } else if (attr.contains("akitas")) {
                    this.akitas = amount;
                } else if (attr.contains("vizslas")) {
                    this.vizslas = amount;
                } else if (attr.contains("goldfish")) {
                    this.goldfish = amount;
                } else if (attr.contains("trees")) {
                    this.trees = amount;
                } else if (attr.contains("cars")) {
                    this.cars = amount;
                } else if (attr.contains("perfume")) {
                    this.perfumes = amount;
                }
            }
        }

        public int score(Object o) {
            if (!(o instanceof Sue)) return -1;

            Sue sue = (Sue) o;

            int totalScore = 0;

            if (akitas != -1 && akitas == sue.akitas) {
                totalScore++;
            }
            else if (akitas != -1 && akitas != sue.akitas) {
                return -999;
            }
            if (cars != -1 && cars == sue.cars) {
                totalScore++;
            }
            else if (cars != -1 && cars != sue.cars) {
                return -999;
            }
            if (cats != -1 && cats > sue.cats) {
                totalScore++;
            }
            else if (cats != -1 && cats <= sue.cats) {
                return -999;
            }
            if (children != -1 && children == sue.children) {
                totalScore++;
            }
            else if (children != -1 && children != sue.children) {
                return -999;
            }
            if (goldfish != -1 && goldfish < sue.goldfish) {
                totalScore++;
            }
            else if (goldfish != -1 && goldfish >= sue.goldfish) {
                return -999;
            }
            if (perfumes != -1 && perfumes == sue.perfumes) {
                totalScore++;
            }
            else if (perfumes != -1 && perfumes != sue.perfumes) {
                return -999;
            }
            if (pomeranians != -1 && pomeranians < sue.pomeranians) {
                totalScore++;
            }
            else if (pomeranians != -1 && pomeranians >= sue.pomeranians) {
                return -999;
            }
            if (samoyeds != -1 && samoyeds == sue.samoyeds) {
                totalScore++;
            }
            else if (samoyeds != -1 && samoyeds != sue.samoyeds) {
                return -999;
            }
            if (trees != -1 && trees > sue.trees) {
                totalScore++;
            }
            else if (trees != -1 && trees <= sue.trees) {
                return -999;
            }
            if (vizslas != -1 && vizslas == sue.vizslas){
                totalScore++;
            }
            else if (vizslas != -1 && vizslas != sue.vizslas) {
                return -999;
            }

            return totalScore;
        }

    }
}
