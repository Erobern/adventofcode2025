package adventofcode2025.utils.records;

import java.util.Objects;

public record Coordinate3D(Long x, Long y, Long z) {
    public String toString() {
        return x.toString() + "," + y.toString() + "," + z.toString();
    }

    public boolean equals(Coordinate3D aCoordinate) {
        return Objects.equals(x, aCoordinate.x()) && Objects.equals(y, aCoordinate.y()) && Objects.equals(z, aCoordinate.z());
    }
}
