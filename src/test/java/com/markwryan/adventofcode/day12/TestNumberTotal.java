package com.markwryan.adventofcode.day12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark on 12/13/15.
 */
public class TestNumberTotal {
    @Test
    public void testGetValuesWithoutRed() {
        NumberTotal totaler = new NumberTotal();
        assertEquals(6, totaler.getTotalWithoutRed(new JSONArray("[1,2,3]")));
        assertEquals(4, totaler.getTotalWithoutRed(new JSONArray("[1,{\"c\":\"red\",\"b\":2},3]")));
        assertEquals(0, totaler.getTotalWithoutRed(new JSONObject("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")));
        assertEquals(6, totaler.getTotalWithoutRed(new JSONArray("[1,\"red\",5]")));
        assertEquals(1, totaler.getTotalWithoutRed(new JSONObject("{\"a\":1,\"b\":{\"c\":\"red\"}}")));
        assertEquals(0, totaler.getTotalWithoutRed(new JSONObject("{\"a\":1,\"b\":[\"red\",1],\"c\":\"red\"}")));
    }
}
