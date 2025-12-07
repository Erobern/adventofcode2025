package adventofcode2025.utils;

public class LongUtils {
    public static boolean isLong(String input) {
        boolean isLong = true;
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            isLong = false;
        }
        return isLong;
    }
}
