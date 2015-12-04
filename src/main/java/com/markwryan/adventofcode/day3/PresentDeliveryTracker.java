package com.markwryan.adventofcode.day3;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 * Santa is delivering presents to an infinite two-dimensional grid of houses.
 * <p/>
 * He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls him
 * via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v), east (>),
 * or west (<). After each move, he delivers another present to the house at his new location.
 * <p/>
 * However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off,
 * and Santa ends up visiting some houses more than once. How many houses receive at least one present?
 * <p/>
 * For example:
 * <p/>
 * > delivers presents to 2 houses: one at the starting location, and one to the east.
 * ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
 * ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 * --- Part Two ---
 * <p/>
 * The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents
 * with him.
 * <p/>
 * Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns
 * moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.
 * <p/>
 * This year, how many houses receive at least one present?
 * <p/>
 * For example:
 *      ^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
 *      ^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
 *      ^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
 * source: http://adventofcode.com/day/3
 * 
 * Created by mark on 12/3/15.
 */
public class PresentDeliveryTracker {
    public static void main(String[] args) throws IOException {
        URL input = ClassLoader.getSystemResource("input/day3.txt");

        BufferedReader reader = new BufferedReader(new FileReader(input.getPath()));
        String lines = reader.readLine();
        // 1 Santa
        int oneSantaNumberOfHomes = mapPresentDelivery(1, lines);
        System.out.println("1 Santa deliver presnets to " + oneSantaNumberOfHomes + "homes.");
        // Santa and Robo-Santa
        int twoSantasNumberOfHomes = mapPresentDelivery(2, lines);
        System.out.println("2 Santas deliver presents to " + twoSantasNumberOfHomes + " homes.");
    }

    /**
     * For n-number of Santas, take turns moving to a home based on input and deliver a present. Return count of homes,
     * which is the number of homes in the map of homes, with specific x,y Points as it's key.
     *
     * @param numberOfSantas - number of santas who will deliver presents
     * @param input          - input string of directions
     * @return - Number of distinct homes visited
     * @throws IOException
     */
    static int mapPresentDelivery(int numberOfSantas, String input) throws IOException {
        final Map<Point, Home> homes = new HashMap<>();
        final Point[] santas = new Point[numberOfSantas];
        final Point start = new Point(0, 0);

        for (int j = 0; j < numberOfSantas; j++) {
            santas[j] = start;
            addOrUpdateHome(homes, start);
        }

        char[] directions = input.toCharArray();
        int santaIndex = 0;
        for (int i = 0; i < directions.length; i++) {
            char direction = directions[i];
            Point location = getLocationFromString(santas[santaIndex], direction);
            addOrUpdateHome(homes, location);
            santas[santaIndex] = location;

            santaIndex++;
            if (santaIndex >= santas.length) {
                santaIndex = 0;
            }
        }
        return homes.size();
    }

    /**
     * Update home's present count if it exits, create and add to the list if it does not yet exist.
     *
     * @param homes
     * @param location
     */
    private static void addOrUpdateHome(final Map<Point, Home> homes, final Point location) {
        if (homes.containsKey(location)) {
            homes.get(location).updatePresentCount();
        } else {
            homes.put(location, new Home());
        }
    }

    /**
     * Update point based upon input character
     *
     * @param point
     * @param direction
     * @return
     */
    private static Point getLocationFromString(final Point point, final char direction) {
        int x = point.x;
        int y = point.y;
        if (direction == '^') {
            y++;
        } else if (direction == 'v') {
            y--;
        } else if (direction == '<') {
            x--;
        } else if (direction == '>') {
            x++;
        }
        return new Point(x, y);
    }


}
