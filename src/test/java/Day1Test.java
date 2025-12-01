import adventofcode2025.solvers.Day1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

    @Test
    void Day1Example() {
        assertEquals("3", Day1.Puzzle1("Day1.txt"));
        assertEquals("6", Day1.Puzzle2("Day1.txt"));
    }

    @Test
    void Day1Tests() {
        assertEquals("2", Day1.Puzzle2("Day1Left.txt"));
        assertEquals("2", Day1.Puzzle2("Day1Right.txt"));
        assertEquals("3", Day1.Puzzle2("Day1LeftAndRight.txt"));
        assertEquals("30", Day1.Puzzle2("Day1LeftAndRightBig.txt"));
        assertEquals("30", Day1.Puzzle2("Day1RightAndLeftBig.txt"));
        assertEquals("4", Day1.Puzzle2("Day1LeftAndRightOffset.txt"));
        assertEquals("30", Day1.Puzzle2("Day1LeftAndRightOffsetBig.txt"));
    }
}
