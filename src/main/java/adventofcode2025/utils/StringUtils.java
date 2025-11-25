package adventofcode2025.utils;

public class StringUtils {

    // very useful for debugging - you can display a 2d String array as a proper grid
    public static String get2DArrayPrint(String[][] matrix) {
        StringBuilder output = new StringBuilder();
        for (String[] strings : matrix) {
            for (String string : strings) {
                output.append(string);
            }
            output.append("\n");
        }
        return output.toString();
    }
}
