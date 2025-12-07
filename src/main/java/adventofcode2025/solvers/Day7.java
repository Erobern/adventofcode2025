package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;
import adventofcode2025.utils.LongUtils;

import java.util.List;

public class Day7 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                grid[i][j] = lines.get(i).substring(j, j + 1);

                if (grid[i][j].equals("S")) {
                    grid[i][j] = "|";
                }
            }
        }

        Integer splitterCount = 0;

        for (int i = 1; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                if (grid[i - 1][j].equals("|")) {
                    if (grid[i][j].equals("^")) {
                        grid[i][j - 1] = "|";
                        grid[i][j + 1] = "|";

                        splitterCount++;

                    } else {
                        grid[i][j] = "|";
                    }
                }
            }
        }


        return splitterCount.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                grid[i][j] = lines.get(i).substring(j, j + 1);

                if (grid[i][j].equals("S")) {
                    grid[i][j] = "1";
                }
            }
        }

        for (int i = 1; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {

                if (!grid[i][j].equals("^")) {
                    Long beamTotal = 0L;


                    if (LongUtils.isLong(grid[i - 1][j])) {
                        beamTotal += Long.parseLong(grid[i - 1][j]);
                    }

                    try {
                        if (LongUtils.isLong(grid[i - 1][j - 1]) && grid[i][j - 1].equals("^")) {
                            beamTotal += Long.parseLong(grid[i - 1][j - 1]);
                        }
                    } catch (Exception ignored) {
                    }

                    try {
                        if (LongUtils.isLong(grid[i - 1][j + 1]) && grid[i][j + 1].equals("^")) {
                            beamTotal += Long.parseLong(grid[i - 1][j + 1]);
                        }
                    } catch (Exception ignored) {
                    }

                    grid[i][j] = beamTotal.toString();
                }

            }
        }

        int i = lines.size() - 1;
        Long totalBeams = 0L;
        for (int j = 0; j < lines.getFirst().length(); j++) {
            if (LongUtils.isLong(grid[i][j])) {
                totalBeams += Long.parseLong(grid[i][j]);
            }
        }

        return totalBeams.toString();
    }
}