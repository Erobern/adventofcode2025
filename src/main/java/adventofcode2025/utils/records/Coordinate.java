package adventofcode2025.utils.records;

import adventofcode2025.utils.enums.Direction;

import java.util.Objects;

public record Coordinate(Integer row, Integer col) {
    public String toString() {
        return row + "," + col;
    }

    public boolean equals(Coordinate aCoordinate) {
        return Objects.equals(row, aCoordinate.row()) && Objects.equals(col, aCoordinate.col());
    }

    public Coordinate step(Direction direction, Integer steps) {
        return new Coordinate(
                direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT) ? row : (
                        direction.equals(Direction.UP) ? row - steps : row + steps
                ),
                direction.equals(Direction.UP) || direction.equals(Direction.DOWN) ? col : (
                        direction.equals(Direction.LEFT) ? col - steps : col + steps
                )
        );
    }
}
