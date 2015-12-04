package com.markwryan.adventofcode.day3;

/**
 * Created by mark on 12/3/15.
 */
public class Home {
    private int count;

    public Home() {
        count = 1;
    }

    public void updatePresentCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
