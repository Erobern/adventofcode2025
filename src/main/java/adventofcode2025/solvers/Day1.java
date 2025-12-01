package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;

import java.util.List;

public class Day1 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Integer dialPosition = 50;
        Integer zeroCount = 0;


        for (String line : lines) {
            String direction = line.substring(0, 1);
            Integer turns = Integer.parseInt(line.substring(1));

            if (direction.equals("L")) {
                dialPosition -= turns;

                while (dialPosition < 0) {
                    dialPosition += 100;
                }
            } else if (direction.equals("R")) {
                dialPosition += turns;

                while (dialPosition > 99) {
                    dialPosition -= 100;
                }
            }

            if (dialPosition == 0) {
                zeroCount++;
            }
        }

        return String.valueOf(zeroCount);
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Integer dialPosition = 50;
        Integer zeroCount = 0;

        for (String line : lines) {
            String direction = line.substring(0, 1);
            Integer turns = Integer.parseInt(line.substring(1));

            Boolean startedOnZero = dialPosition == 0;

            while (turns >= 100) {
                turns -= 100;
                zeroCount++;
            }

            if (direction.equals("L")) {
                dialPosition -= turns;

                if (dialPosition == 0) {
                    zeroCount++;
                } else if (dialPosition < 0) {
                    dialPosition += 100;

                    if (!startedOnZero) {
                        zeroCount++;
                    }
                }
            } else if (direction.equals("R")) {
                dialPosition += turns;

                if (dialPosition == 100) {
                    dialPosition = 0;
                    zeroCount++;
                } else if (dialPosition > 100) {
                    dialPosition -= 100;
                    if (!startedOnZero) {
                        zeroCount++;
                    }
                }
            }
        }

        return String.valueOf(zeroCount);
    }
}