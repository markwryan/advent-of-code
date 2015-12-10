package com.markwryan.adventofcode.day9;

import com.google.common.collect.Collections2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mark on 12/9/15.
 */
public class TravelingSanta {

    private Set<String> destinations;
    private List<Route> routes;

    public TravelingSanta() {
        routes = new ArrayList<>();
        destinations = new HashSet<>();

    }

    public static void main(String[] args) throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day9.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input.getPath()));
        TravelingSanta travel = new TravelingSanta();
        List<Integer> result = travel.getShortestRoute(reader);


        System.out.println(result.get(0));
        System.out.println(result.get(result.size()-1));
    }

    List<Integer> getShortestRoute(BufferedReader reader) {
        List<Integer> result = new ArrayList<>();

        reader.lines().forEach(line -> readRoute(line));

        Collection<List<String>> permutations = Collections2.permutations(destinations);

        for (List<String> places : permutations) {
            int total = 0;
            String route = "";

            for (int i = 1; i < places.size(); i++) {
                String from = places.get(i - 1);
                String to = places.get(i);
                route += from;
                if (i < places.size() - 1) {
                    route += " -> ";
                }
                total += getDistanceBetweenCities(from, to);
            }
            result.add(total);
        }

        return result.stream().sorted().collect(Collectors.toList());
    }

    int getDistanceBetweenCities(String c1, String c2) {
        for (Route r : routes) {
            if (r.cities[0].equals(c1) || r.cities[0].equals(c2)) {
                if (r.cities[1].equals(c1) || r.cities[1].equals(c2)) {
                    return r.distance;
                }
            }
        }
        throw new IllegalArgumentException("Distance is not found: " + c1 + " to " + c2);
    }


    void readRoute(String input) {
        String[] locationAndDist = input.split("=");
        int distance = Integer.parseInt(locationAndDist[1].trim());
        String[] splitLoc = locationAndDist[0].trim().split(" to ");

        Route route = new Route(splitLoc, distance);
        routes.add(route);


        if (!destinations.contains(route.cities[0])) {
            destinations.add(route.cities[0]);
        }
        if (!destinations.contains(route.cities[1])) {
            destinations.add(route.cities[1]);
        }
    }


    private class Route {
        String[] cities;
        int distance;

        public Route(String[] c, int d) {
            cities = c;
            distance = d;
        }
    }
}
