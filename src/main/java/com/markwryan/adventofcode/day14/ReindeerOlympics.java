package com.markwryan.adventofcode.day14;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mark on 12/14/15.
 */
public class ReindeerOlympics {

    public static void main(String[] args) throws FileNotFoundException {
        runRace(2503);
    }

    //2503 seconds
    private static void runRace(int d) {
        Map<Reindeer, int[]> results = new HashMap<>();
        for (Reindeer reindeer : Reindeer.values()) {
            int duration = d;
            int[] steps = new int[d];
            int currentSecond = 0;
            int totalDistance = 0;
            boolean isResting = false;
            while (duration > 0) {
                if (isResting) {
                    if (duration < reindeer.rest) {
                        for (int i = currentSecond; i < d; i++) {
                            steps[i] = totalDistance;
                        }
                        duration = 0;
                        currentSecond = d;
                    } else {
                        for (int i = currentSecond; i < (currentSecond + reindeer.rest); i++) {
                            steps[i] = totalDistance;
                        }
                        duration = duration - reindeer.rest;
                        currentSecond += reindeer.rest;
                    }
                    isResting = false;
                } else {
                    if (duration > reindeer.duration) {
                        for (int i = currentSecond; i < (currentSecond + reindeer.duration); i++) {
                            totalDistance += reindeer.speed;
                            steps[i] = totalDistance;
                        }
                        currentSecond += reindeer.duration;
                        duration = duration - reindeer.duration;
                    } else {
                        for (int i = currentSecond; i < (currentSecond + duration); i++) {
                            totalDistance += reindeer.speed;
                            steps[i] = totalDistance;
                        }
                        currentSecond = d;
                        duration = 0;
                    }
                    isResting = true;
                }
            }
            results.put(reindeer, steps);
        }

        Map<Reindeer, Integer> points = getTotalPoints(results, d);
        points.forEach((reindeer, score) -> System.out.println(reindeer + ": " + score));
    }

    private static Map<Reindeer, Integer> getTotalPoints(Map<Reindeer, int[]> results, int duration) {
        Map<Reindeer, Integer> winners = new HashMap<>();

        for (int i = 0; i < duration; i++) {
            List<Reindeer> pointWinner = new ArrayList<>();
            int top = -1;

            for (Reindeer reindeer : results.keySet()) {
                int distance = results.get(reindeer)[i];
                if (top < distance) {
                    top = distance;
                    pointWinner.clear();
                    pointWinner.add(reindeer);
                }
                else if (top == distance) {
                    pointWinner.add(reindeer);
                }
            }

            for (Reindeer reindeer : pointWinner) {
                if (winners.containsKey(reindeer)) {
                    int score = winners.get(reindeer) + 1;
                    winners.put(reindeer, score);
                } else {
                    winners.put(reindeer, 1);
                }
            }
        }
        return winners;
    }


    private static enum Reindeer {
        VIXEN(8, 8, 53),
        BLITZEN(13, 4, 49),
        RUDOLPH(20, 7, 132),
        CUPID(12, 4, 43),
        DONNER(9, 5, 38),
        DASHER(10, 4, 37),
        COMET(3, 37, 76),
        PRANCER(9, 12, 97),
        DANCER(37, 1, 36);

/*
        DANCER(16, 11, 162),
        COMET(14, 10, 127);
*/
        private int speed;
        private int duration;
        private int rest;

        private Reindeer(int s, int d, int r) {
            this.speed = s;
            this.rest = r;
            this.duration = d;
        }

    }

}

