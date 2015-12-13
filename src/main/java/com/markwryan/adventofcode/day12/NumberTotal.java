package com.markwryan.adventofcode.day12;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mark on 12/13/15.
 */
public class NumberTotal {
    private static Pattern numbers;

    public static void main(String[] args) throws IOException {

        URL input = ClassLoader.getSystemResource("input/day12.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input.getPath()));
        System.out.println(totalValues(reader));

        BufferedReader reader2 = new BufferedReader(new FileReader(input.getPath()));
        NumberTotal totaler = new NumberTotal();
        int total = totaler.total(reader2);
        System.out.println("Part 2: " + total);
    }

    int total(BufferedReader reader) {
        AtomicInteger total = new AtomicInteger(0);
        reader.lines().forEach(line -> total.getAndAdd(getTotalWithoutRed(new JSONArray(line))));
        return total.get();
    }

    static int totalValues(BufferedReader reader) {
        numbers = Pattern.compile("[-]?[\\d]+");
        AtomicInteger total = new AtomicInteger(0);

        reader.lines().forEach(line -> {
            Matcher matcher = numbers.matcher(line);
            while (matcher.find()) {
                total.getAndAdd(Integer.parseInt(matcher.group()));
            }

        });

        return total.get();
    }

    int getTotalWithoutRed(JSONArray entry) {
        int total = 0;
        for(Object o : entry) {
            if(o.getClass() == Integer.class) {
                Integer value = (Integer) o;
                total += value;
            }
            else if(o.getClass() == JSONArray.class) {
                JSONArray value = (JSONArray) o;
                total += getTotalWithoutRed(value);
            }
            else if(o.getClass() == JSONObject.class) {
                JSONObject value = (JSONObject) o;
                total += getTotalWithoutRed(value);
            }
        }

        return total;
    }

    int getTotalWithoutRed(JSONObject entries) {
        int total = 0;
        for(String key : entries.keySet()) {
            Object o = entries.get(key);
            if(o.getClass() == String.class) {
                String value = (String) o;
                if(value.equals("red")) {
                    return 0;
                }
            }
            else if(o.getClass() == Integer.class) {
                Integer value = (Integer) o;
                total += value;
            }
            else if(o.getClass() == JSONArray.class) {
                JSONArray value = (JSONArray) o;
                total += getTotalWithoutRed(value);
            }
            else if(o.getClass() == JSONObject.class) {
                JSONObject value = (JSONObject) o;
                total += getTotalWithoutRed(value);
            }
        }

        return total;
    }
}
