package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Long grandTotal = 0L;

        List<List<String>> splitLines = new ArrayList<>();

        for (String line : lines) {
            splitLines.add(Arrays.stream(line.trim().split("\\s+")).toList());
        }

        for (int i = 0; i < splitLines.getFirst().size(); i++) {
            if (splitLines.getLast().get(i).equals("+")) {
                Long runningTotal = 0L;
                for (int j = 0; j < splitLines.size() - 1; j++) {
                    runningTotal += Long.valueOf(splitLines.get(j).get(i));
                }
                grandTotal += runningTotal;
            } else {
                Long runningTotal = 1L;
                for (int j = 0; j < splitLines.size() - 1; j++) {
                    runningTotal *= Long.valueOf(splitLines.get(j).get(i));
                }
                grandTotal += runningTotal;
            }
        }

        return grandTotal.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Long grandTotal = 0L;

        Integer maxLength = lines.stream().map(String::length).max(Integer::compare).orElseThrow();

        List<Long> numbers = new ArrayList<>();

        for (int i = maxLength - 1; i >= 0; i--) {
            Long number = 0L;
            for (int j = 0; j < lines.size() - 1; j++) {
                try {
                    Long sample = Long.valueOf(String.valueOf(lines.get(j).charAt(i)));

                    if (sample != 0) {
                        number *= 10;
                        number += sample;
                    }
                } catch (Exception ignored) {
                }
            }

            numbers.add(number);

            try {
                String operator = String.valueOf(lines.getLast().charAt(i));

                if (operator.equals("+")) {
                    grandTotal += numbers.stream().reduce(0L, Long::sum);
                    numbers.clear();
                    i--;
                } else if (operator.equals("*")) {
                    grandTotal += numbers.stream().reduce(1L, (a, b) -> a * b);
                    numbers.clear();
                    i--;
                }
            } catch (Exception ignored) {
            }

        }
        return grandTotal.toString();
    }
}