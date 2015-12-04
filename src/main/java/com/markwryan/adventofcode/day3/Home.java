package com.markwryan.adventofcode.day3;

/**
 * Home which can receive presents from Santa.
 *
 * Created by mark on 12/3/15.
 */
class Home {
    private int count;

    public Home() {
        count = 1;
    }

    public void updatePresentCount() {
        count++;
    }
}
