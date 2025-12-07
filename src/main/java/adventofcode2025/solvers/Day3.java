package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Day3 {
    public static String Puzzle1(String input) {

        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        AtomicLong runningJoltage = new AtomicLong(0L);

        for (String line : lines) {
            // find first biggest number for each line unless it's the end
            Integer tensPlace = getBiggestNumber(line.substring(0, line.length() - 1));

            Integer index = line.indexOf(tensPlace.toString());

            Integer onesPlace = getBiggestNumber(line.substring(index + 1));

            runningJoltage.getAndAdd((tensPlace * 10) + onesPlace);
        }


        return runningJoltage.toString();
    }

    private static Integer getBiggestNumber(String line) {
        if (line.contains("9")) {
            return 9;
        } else if (line.contains("8")) {
            return 8;
        } else if (line.contains("7")) {
            return 7;
        } else if (line.contains("6")) {
            return 6;
        } else if (line.contains("5")) {
            return 5;
        } else if (line.contains("4")) {
            return 4;
        } else if (line.contains("3")) {
            return 3;
        } else if (line.contains("2")) {
            return 2;
        } else {
            return 1;
        }
    }

    public static String Puzzle2(String input) {

        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        AtomicLong runningJoltage = new AtomicLong(0L);

        for (String line : lines) {
            Long lineRunningJoltage = 0L;
            Integer startIndex = 0;

            for (int i = 11; i >= 0; i--) {
                Integer biggestNumber = getBiggestNumber(line.substring(startIndex, line.length() - i));
                startIndex = (line.substring(startIndex, line.length() - i).indexOf(biggestNumber.toString()) + 1) + startIndex;
                lineRunningJoltage *= 10L;
                lineRunningJoltage += (long) biggestNumber;
            }

            runningJoltage.getAndAdd(lineRunningJoltage);
        }

        return runningJoltage.toString();
    }
}