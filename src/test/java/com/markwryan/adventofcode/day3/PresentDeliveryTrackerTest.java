package com.markwryan.adventofcode.day3;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests around the present delivery tracker
 * Created by mark on 12/3/15.
 */
public class PresentDeliveryTrackerTest {
    @Test
    public void testMapPresentDelivery_WorksForExampleInputWithOneSanta() throws IOException {
        assertEquals(2, PresentDeliveryTracker.mapPresentDelivery(1, ">"));
        assertEquals(4, PresentDeliveryTracker.mapPresentDelivery(1, "^>v<"));
        assertEquals(2, PresentDeliveryTracker.mapPresentDelivery(1, "^v^v^v^v^v"));
    }

    @Test
    public void testMapPresentDelivery_WorksForExampleInputWithTwoSantas() throws IOException {
        assertEquals(3, PresentDeliveryTracker.mapPresentDelivery(2, "^v"));
        assertEquals(3, PresentDeliveryTracker.mapPresentDelivery(2, "^>v<"));
        assertEquals(11, PresentDeliveryTracker.mapPresentDelivery(2, "^v^v^v^v^v"));
    }
}
