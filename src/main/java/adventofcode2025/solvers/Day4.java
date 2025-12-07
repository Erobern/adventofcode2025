package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;

import java.util.List;
import java.util.Objects;

public class Day4 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Integer runningTotal = 0;

        String[][] grid = new String[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                grid[i][j] = lines.get(i).substring(j, j + 1);
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                if (Objects.equals(grid[i][j], "@") && evaluatePosition(i, j, grid)) {
                    runningTotal++;
                }
            }
        }

        return runningTotal.toString();
    }

    private static boolean evaluatePosition(int i, int j, String[][] grid) {
        Integer blockedSquares = 0;

        try {
            if (Objects.equals(grid[i - 1][j - 1], "@")) {
                blockedSquares++;
            }
        } catch (Exception ignored) {
        }

        try {
            if (Objects.equals(grid[i - 1][j], "@")) {
                blockedSquares++;
            }
        } catch (Exception ignored) {
        }

        try {
            if (Objects.equals(grid[i - 1][j + 1], "@")) {
                blockedSquares++;
            }
        } catch (Exception ignored) {
        }

        try {
            if (Objects.equals(grid[i][j - 1], "@")) {
                blockedSquares++;
            }
        } catch (Exception ignored) {
        }

        try {
            if (Objects.equals(grid[i][j + 1], "@")) {
                blockedSquares++;
            }
        } catch (Exception ignored) {
        }

        try {
            if (Objects.equals(grid[i + 1][j - 1], "@")) {
                blockedSquares++;
            }
        } catch (Exception ignored) {
        }

        try {
            if (Objects.equals(grid[i + 1][j], "@")) {
                blockedSquares++;
            }
        } catch (Exception ignored) {
        }

        try {
            if (Objects.equals(grid[i + 1][j + 1], "@")) {
                blockedSquares++;
            }
        } catch (Exception ignored) {
        }

        return blockedSquares < 4;
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Integer runningTotal = 0;

        String[][] grid = new String[lines.size()][lines.getFirst().length()];
        String[][] gridCopy = new String[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                grid[i][j] = lines.get(i).substring(j, j + 1);
            }
        }

        Boolean movedRolls = true;

        while (movedRolls) {
            movedRolls = false;

            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.getFirst().length(); j++) {
                    gridCopy[i][j] = grid[i][j];
                    if (Objects.equals(grid[i][j], "@") && evaluatePosition(i, j, grid)) {
                        runningTotal++;
                        movedRolls = true;
                        gridCopy[i][j] = ".";
                    }
                }
            }

            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.getFirst().length(); j++) {
                    grid[i][j] = gridCopy[i][j];
                }
            }
        }

        return runningTotal.toString();
    }
}