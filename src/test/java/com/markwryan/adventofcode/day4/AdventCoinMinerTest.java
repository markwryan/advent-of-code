package com.markwryan.adventofcode.day4;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by maryan on 12/4/15.
 */
public class AdventCoinMinerTest {
    @Test
    public void testCalculateMD5Hash_WorksForProvidedExamples() throws NoSuchAlgorithmException {
        Map<String, String> result = AdventCoinMiner.calculateMD5Hash("abcdef", "00000");
        assertEquals("609043", result.get(AdventCoinMiner.ANSWER_KEY));
        result = AdventCoinMiner.calculateMD5Hash("pqrstuv", "00000");
        assertEquals("1048970", result.get(AdventCoinMiner.ANSWER_KEY));
    }
}
