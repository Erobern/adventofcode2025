import adventofcode2025.solvers.Day9;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {

    @Test
    void Day9Example() {
        assertEquals("50", Day9.Puzzle1("Day9.txt"));
        assertEquals("24", Day9.Puzzle2("Day9.txt"));
    }

    @Test
    void Day9Reddit1Example() {
        assertEquals("30", Day9.Puzzle2("Day9Reddit1.txt"));
    }

    @Test
    void Day9Reddit2Example() {
        assertEquals("88", Day9.Puzzle2("Day9Reddit2.txt"));
    }

    @Test
    void Day9Reddit3Example() {
        assertEquals("72", Day9.Puzzle2("Day9Reddit3.txt"));
    }

}
