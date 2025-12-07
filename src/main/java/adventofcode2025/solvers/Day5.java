package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<List<Long>> listOfRanges = new ArrayList<>();

        Boolean readingRanges = true;

        Integer freshIngredientCount = 0;

        for (String line : lines) {
            if (readingRanges) {
                if (line.isBlank()) {
                    readingRanges = false;
                } else {
                    listOfRanges.add(Arrays.stream(line.split("-")).map(Long::valueOf).toList());
                }
            } else {
                Long ingredientId = Long.valueOf(line);
                for (List<Long> listOfRange : listOfRanges) {
                    if (listOfRange.getFirst() <= ingredientId && listOfRange.getLast() >= ingredientId) {
                        freshIngredientCount++;
                        break;
                    }
                }
            }
        }


        return freshIngredientCount.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<List<Long>> listOfRanges = new ArrayList<>();

        Boolean readingRanges = true;

        for (String line : lines) {
            if (readingRanges) {
                if (line.isBlank()) {
                    readingRanges = false;
                } else {
                    listOfRanges.add(Arrays.stream(line.split("-")).map(Long::valueOf).toList());
                }
            }
        }

        Boolean rangesChanged = true;

        while (rangesChanged) {

            rangesChanged = false;

            List<List<Long>> newRanges = new ArrayList<>();

            for (List<Long> listOfRange : listOfRanges) {
                List<Long> overlapRange = new ArrayList<>();
                for (List<Long> newRange : newRanges) {
                    if (
                            (listOfRange.getFirst() <= newRange.getFirst() && listOfRange.getLast() >= newRange.getFirst()) ||
                                    (listOfRange.getFirst() <= newRange.getLast() && listOfRange.getLast() >= newRange.getFirst())
                    ) {
                        overlapRange = newRange;
                        break;
                    }
                }
                if (!overlapRange.isEmpty()) {
                    List<Long> updatedRange = new ArrayList<>();

                    updatedRange.add(overlapRange.getFirst() <= listOfRange.getFirst() ? overlapRange.getFirst() : listOfRange.getFirst());
                    updatedRange.add(overlapRange.getLast() >= listOfRange.getLast() ? overlapRange.getLast() : listOfRange.getLast());

                    newRanges.add(updatedRange);
                    newRanges.remove(overlapRange);

                    rangesChanged = true;
                } else {
                    newRanges.add(listOfRange);
                }
            }

            listOfRanges.clear();
            listOfRanges.addAll(newRanges);
        }

        Long sumOfFreshIngredients = 0L;

        for (List<Long> listOfRange : listOfRanges) {
            sumOfFreshIngredients += (listOfRange.getLast() - listOfRange.getFirst()) + 1;
        }


        return sumOfFreshIngredients.toString();
    }
}