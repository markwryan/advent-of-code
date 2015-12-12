package com.markwryan.adventofcode.day11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark on 12/11/15.
 */
public class TestPasswordFinder {

    @Test
    public void testPasswordFinder_WithExampleInputs(){
        assertEquals("abcdffaa", PasswordFinder.findNextPassword("abcdefgh".toCharArray()));
        assertEquals("ghjaabcc", PasswordFinder.findNextPassword("ghijklmn".toCharArray()));
    }
}
