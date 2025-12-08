import adventofcode2025.solvers.Day8;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Test {

    @Test
    void Day8Example() {
        assertEquals("40", Day8.Puzzle1("Day8.txt", 10));
        assertEquals("25272", Day8.Puzzle2("Day8.txt"));
    }
}
