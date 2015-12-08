package com.markwryan.adventofcode.day7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Ugly, ugly solution to day 7. Rough.
 * Created by mark on 12/7/15.
 */
class CircuitMapper {
    BufferedReader reader;
    Map<String, String> instructions;
    Map<String, String> sources;
    Map<String, Integer> results;

    public CircuitMapper() throws FileNotFoundException {
        URL input = ClassLoader.getSystemResource("input/day7.txt");
        reader = new BufferedReader(new FileReader(input.getPath()));
        instructions = new HashMap<>();
        sources = new HashMap<>();
        results = new HashMap<>();
        getMapOfInstructionsAndSources();
    }


    public static void main(String[] args) throws FileNotFoundException {
        CircuitMapper mapper = new CircuitMapper();
        System.out.println(mapper.getSignalForSpecificWire("a"));

        CircuitMapper mapper2 = new CircuitMapper();
        mapper2.results.put("b", 956);
        System.out.println(mapper2.getSignalForSpecificWire("a"));

    }

    int getSignalForSpecificWire(String wire) {
        try {
            int val = Integer.parseInt(wire);
            return val;
        } catch (NumberFormatException nfe) {
        }

        if (!results.containsKey(wire)) {
            Integer signal;
            String instruction = sources.get(wire);

            if (instruction.length() <= 2) {
                return getSignalForSpecificWire(instruction);
            } else {
                Operation operation = Operation.getOperationFromLine(instruction);
                signal = performOperation(operation, instruction);
            }
            results.put(wire, signal);
        }

        return results.get(wire);
    }

    int performOperation(Operation operation, String instruction) {
        String[] splitInstructions = instruction.replace(operation.toString() + " ", "").split(" ");
        splitInstructions[0] = splitInstructions[0].trim();
        int left;
        try {
            left = Integer.parseInt(splitInstructions[0]);
        } catch (NumberFormatException nfe) {
            left = getSignalForSpecificWire(splitInstructions[0]);
        }
        if (splitInstructions.length > 1) {
            int right;
            splitInstructions[1] = splitInstructions[1].trim();
            try {
                right = Integer.parseInt(splitInstructions[1]);
            } catch (NumberFormatException nfe) {
                right = getSignalForSpecificWire(splitInstructions[1]);
            }
            if (Operation.AND == operation) {
                return left & right;
            } else if (Operation.OR == operation) {
                return left | right;
            } else if (Operation.LSHIFT == operation) {
                return left << right;
            } else if (Operation.RSHIFT == operation) {
                return left >> right;
            }
        } else if (Operation.NOT == operation) {
            return 65535 - getSignalForSpecificWire(splitInstructions[0]);
        }
        return Integer.parseInt(instruction);
    }

    void getMapOfInstructionsAndSources() {
        reader.lines().forEach(line -> {
            String[] command = getInstruction(line);
            instructions.put(command[0], command[1]);
            sources.put(command[1], command[0]);
        });
    }

    String[] getInstruction(String line) {
        String[] instr = line.split("->");
        for (int i = 0; i < instr.length; i++) {
            instr[i] = instr[i].trim();
        }
        return instr;
    }

    enum Operation {
        AND, OR, LSHIFT, RSHIFT, NOT, PROVIDED;
        static Operation getOperationFromLine(String source) {

            for (Operation op : Operation.values()) {
                if (source.contains(op.toString())) {
                    return op;
                }
            }
            return PROVIDED;
        }

    }

}
