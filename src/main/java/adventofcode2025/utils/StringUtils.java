package adventofcode2025.utils;

public class StringUtils {

    // very useful for debugging - you can display a 2d String array as a proper grid
    public static String get2DArrayPrint(String[][] matrix) {
        String output = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                output = output + (matrix[i][j]);
            }
            output = output + "\n";
        }
        return output;
    }
}
