package adventofcode2025.utils.records;

import adventofcode2025.utils.enums.Direction;

import java.util.Objects;

public record Coordinate2D(Long x, Long y) {
    public String toString() {
        return x + "," + y;
    }

    public boolean equals(Coordinate2D aCoordinate2D) {
        return Objects.equals(x, aCoordinate2D.x()) && Objects.equals(y, aCoordinate2D.y());
    }

    public Coordinate2D step(Direction direction, Integer steps) {
        return new Coordinate2D(
                direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT) ? x : (
                        direction.equals(Direction.UP) ? x - steps : x + steps
                ),
                direction.equals(Direction.UP) || direction.equals(Direction.DOWN) ? y : (
                        direction.equals(Direction.LEFT) ? y - steps : y + steps
                )
        );
    }
}
