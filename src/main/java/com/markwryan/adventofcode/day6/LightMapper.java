package com.markwryan.adventofcode.day6;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mark on 12/6/15.
 */
public class LightMapper {
    static String TURN_ON = "turn on ";
    static String TURN_OFF = "turn off ";
    static String TOGGLE = "toggle ";
    static String THROUGH = " through";

    public static void main(String[] args) throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day6.txt");
        int lit = mapLights(new BufferedReader(new FileReader(input.getPath())));
        System.out.println(lit);
    }

    static int mapLights(BufferedReader input) {
        Map<Point, Integer> grid = new HashMap<>();

        input.lines().forEach(line -> {
            Point[] points = getPoint(line);
            int startX = points[0].x;
            int startY = points[0].y;
            int endX = points[1].x;
            int endY = points[1].y;
            if(line.startsWith(TURN_ON)) {
                for(int i = startX; i <= endX; i++){
                    for(int j = startY; j <= endY; j++) {
                        Point current = new Point(i,j);
                        if(grid.containsKey(current)) {
                            grid.put(current, grid.get(current) + 1);
                        }
                        else {
                            grid.put(current, 1);
                        }

                    }
                }
            }
            else if(line.startsWith(TURN_OFF)) {
                for(int i = startX; i <= endX; i++){
                    for(int j = startY; j <= endY; j++) {
                        Point current = new Point(i,j);
                        if(grid.containsKey(current) && grid.get(current) > 0) {
                            grid.put(current, grid.get(current) - 1);
                        }
                        else {
                            grid.put(current, 0);
                        }
                    }
                }
            }
            else if(line.startsWith(TOGGLE)) {
                for(int i = startX; i <= endX; i++){
                    for(int j = startY; j <= endY; j++) {
                        Point current = new Point(i,j);
                        if(grid.containsKey(current)) {
                            grid.put(current, grid.get(current) + 2);
                        }
                        else {
                            grid.put(current, 2);
                        }
                    }
                }
            }
        });

        int totalBrightness = 0;
        for(Point p : grid.keySet()) {
            totalBrightness += grid.get(p);
        }

        return totalBrightness;
    }

    static Point[] getPoint(String line) {
        Point[] result = new Point[2];
        String cleanPoints = line.replace(TURN_OFF, "").replace(TURN_ON,"").replace(TOGGLE, "").replace(THROUGH,"");
        String[] split = cleanPoints.split(" ");
        for(int i = 0; i < split.length; i++) {
            String[] coordinates = split[i].split(",");
            result[i] = new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        }
        return result;
    }

}
