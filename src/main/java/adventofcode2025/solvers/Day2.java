package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Day2 {
    public static String Puzzle1(String input) {

        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<String> ranges = List.of(lines.getFirst().split(","));

        AtomicLong runningSum = new AtomicLong(0L);

        ranges.forEach(range -> {
            List<String> splitRange = List.of(range.split("-"));

            Long longStartRange = Long.parseLong(splitRange.getFirst());
            Long longEndRange = Long.parseLong(splitRange.getLast());

            while (longStartRange <= longEndRange) {
                if (longStartRange.toString().length() % 2 == 1) {
                    longStartRange = rollForwardToNextSize(longStartRange);
                } else {
                    String startRange = longStartRange.toString();

                    Long frontSplit = Long.valueOf(startRange.substring(0, startRange.length() / 2));
                    Long backSplit = Long.valueOf(startRange.substring(startRange.length() / 2));

                    Long candidate = Long.parseLong((startRange.substring(0, startRange.length() / 2).concat((startRange.substring(0, startRange.length() / 2)))));

                    if (candidate > longEndRange) {
                        return;
                    }

                    if (candidate < longStartRange) {
                        Long temp = Long.parseLong(startRange.substring(0, startRange.length() / 2)) + 1L;
                        longStartRange = Long.valueOf(temp.toString().concat(temp.toString()));
                    } else if (frontSplit <= backSplit) {
                        runningSum.getAndAdd(candidate);

                        Long temp = Long.parseLong(startRange.substring(0, startRange.length() / 2)) + 1L;
                        longStartRange = Long.valueOf(temp.toString().concat(temp.toString()));
                    } else {
                        Long temp = Long.parseLong(startRange.substring(0, startRange.length() / 2));
                        longStartRange = Long.valueOf(temp.toString().concat(startRange.substring(0, startRange.length() / 2)));
                    }


                }
            }
        });

        return runningSum.toString();
    }

    private static Long rollForwardToNextSize(Long longStartRange) {
        return Long.parseLong("1".concat("0".repeat(longStartRange.toString().length())));
    }

    public static String Puzzle2(String input) {

        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<String> ranges = List.of(lines.getFirst().split(","));

        AtomicLong runningSum = new AtomicLong(0L);

        ranges.forEach(range -> {
            List<String> splitRange = List.of(range.split("-"));

            Long longStartRange = Long.parseLong(splitRange.getFirst());
            Long longEndRange = Long.parseLong(splitRange.getLast());

            while (longStartRange <= longEndRange) {
                String startRange = longStartRange.toString();
                for (Integer i = 1; i <= startRange.length() / 2; i++) {
                    if (startRange.length() % i == 0) {
                        if (startRange.substring(0, i).repeat(startRange.length() / i).equals(startRange)) {
                            runningSum.getAndAdd(longStartRange);
                            break;
                        }
                    }
                }
                longStartRange += 1L;
            }
        });

        return runningSum.toString();
    }
}