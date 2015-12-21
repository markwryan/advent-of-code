package com.markwryan.adventofcode.day18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by maryan on 12/21/15.
 */
public class GifOfLights {


    final static int WIDTH = 100;
    final static int HEIGHT = 100;
    final static int STEPS = 100;
    int[][] lights;

    public static void main(String[] args) throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day18.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input.getPath()));
        GifOfLights gifOfLights = new GifOfLights();
        gifOfLights.readInInitialState(reader, false);

        for (int i = 0; i < STEPS; i++) {
            gifOfLights.update(false);
        }
        System.out.println("Total lit: " + gifOfLights.getTotalLit());

        //Part 2
        BufferedReader reader2 = new BufferedReader(new FileReader(input.getPath()));
        GifOfLights gifOfLightsStuckCorners = new GifOfLights();
        gifOfLightsStuckCorners.readInInitialState(reader2, true);
        for (int i = 0; i < STEPS; i++) {
            gifOfLightsStuckCorners.update(true);
        }
        System.out.println("Total lit with stuck corners: " + gifOfLightsStuckCorners.getTotalLit());


    }

    void update(boolean stuckCorners) {
        int[][] updated = new int[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                int current = lights[row][col];
                int litNeighbors;

                if (col == 0) {
                    if (row == 0) {
                        litNeighbors = lights[row][col + 1] + lights[row + 1][col] + lights[row + 1][col + 1];
                    }
                    else if (row == WIDTH - 1) {
                        litNeighbors = lights[row - 1][col] + lights[row - 1][col + 1] + lights[row][col + 1];
                    }
                    else {
                        litNeighbors =
                                lights[row - 1][col] + lights[row - 1][col + 1] + lights[row][col + 1] +
                                        lights[row + 1][col + 1] +
                                        lights[row + 1][col];
                    }
                }
                else if (col == WIDTH - 1) {
                    if (row == 0) {
                        litNeighbors = lights[row][col - 1] + lights[row + 1][col - 1] + lights[row + 1][col];
                    }
                    else if (row == HEIGHT - 1) {
                        litNeighbors = lights[row - 1][col] + lights[row - 1][col - 1] + lights[row][col - 1];
                    }
                    else {
                        litNeighbors =
                                lights[row - 1][col] + lights[row - 1][col - 1] + lights[row][col - 1] +
                                        lights[row + 1][col - 1] +
                                        lights[row + 1][col];
                    }
                }
                else {
                    if (row == 0) {
                        litNeighbors =
                                lights[row][col - 1] + lights[row + 1][col - 1] + lights[row + 1][col] +
                                        lights[row + 1][col + 1] + lights[row][col + 1];
                    }
                    else if (row == HEIGHT - 1) {
                        litNeighbors =
                                lights[row][col + 1] + lights[row - 1][col + 1] + lights[row - 1][col] +
                                        lights[row - 1][col - 1] +
                                        lights[row][col - 1];
                    }
                    else {
                        litNeighbors =
                                lights[row][col + 1] + lights[row - 1][col + 1] + lights[row - 1][col] +
                                        lights[row - 1][col - 1] +
                                        lights[row][col - 1] + lights[row + 1][col - 1] + lights[row + 1][col] +
                                        lights[row + 1][col + 1];
                    }
                }

                //Force corner lights to always be on
                if (stuckCorners) {
                    if ((row == 0 || row == HEIGHT - 1) && (col == 0 || col == WIDTH - 1)) {
                        updated[row][col] = 1;
                    }
                    else if (current == 1) {
                        if (litNeighbors == 2 || litNeighbors == 3) {
                            updated[row][col] = 1;
                        }
                        else {
                            updated[row][col] = 0;
                        }
                    }
                    else {
                        if (litNeighbors == 3) {
                            updated[row][col] = 1;
                        }
                        else {
                            updated[row][col] = 0;
                        }

                    }
                }
                else {
                    if (current == 1) {
                        if (litNeighbors == 2 || litNeighbors == 3) {
                            updated[row][col] = 1;
                        }
                        else {
                            updated[row][col] = 0;
                        }
                    }
                    else {
                        if (litNeighbors == 3) {
                            updated[row][col] = 1;
                        }
                        else {
                            updated[row][col] = 0;
                        }

                    }
                }
            }
        }

        //Update lights
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                lights[i][j] = updated[i][j];
            }
        }
    }

    int getTotalLit() {
        int totalLit = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (lights[i][j] == 1) {
                    totalLit++;
                }
            }
        }
        return totalLit;
    }

    void readInInitialState(BufferedReader reader, boolean stuckCorners) {
        lights = new int[HEIGHT][WIDTH];
        AtomicInteger row = new AtomicInteger(0);
        reader.lines().forEach(line -> {
            char[] entries = line.toCharArray();
            for (int col = 0; col < entries.length; col++) {
                if (stuckCorners) {
                    if ((row.get() == 0 || row.get() == HEIGHT - 1) && (col == 0 || col == WIDTH - 1)) {
                        lights[row.get()][col] = 1;
                    }
                    else if (entries[col] == '.') {
                        lights[row.get()][col] = 0;
                    }
                    else {
                        lights[row.get()][col] = 1;
                    }
                }
                else {
                    if (entries[col] == '.') {
                        lights[row.get()][col] = 0;
                    }
                    else {
                        lights[row.get()][col] = 1;
                    }
                }
            }
            row.getAndIncrement();
        });
    }
}
