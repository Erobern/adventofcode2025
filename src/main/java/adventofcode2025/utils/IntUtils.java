package adventofcode2025.utils;

public class IntUtils {
    public static boolean isInt(String input) {
        boolean isInt = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            isInt = false;
        }
        return isInt;
    }
}
