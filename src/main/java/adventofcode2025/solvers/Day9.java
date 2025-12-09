package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;
import adventofcode2025.utils.records.Coordinate2D;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Day9 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Coordinate2D> coordinateList = new ArrayList<>();

        for (String line : lines) {
            List<String> split = Arrays.stream(line.split(",")).toList();
            coordinateList.add(new Coordinate2D(Long.parseLong(split.getFirst()), Long.parseLong(split.getLast())));
        }

        Long biggestArea = 0L;

        for (int i = 0; i < coordinateList.toArray().length - 1; i++) {
            for (int j = i + 1; j < coordinateList.toArray().length; j++) {
                Long width = coordinateList.get(i).x() > coordinateList.get(j).x() ?
                        coordinateList.get(i).x() - coordinateList.get(j).x() :
                        coordinateList.get(j).x() - coordinateList.get(i).x();
                Long height = coordinateList.get(i).y() > coordinateList.get(j).y() ?
                        coordinateList.get(i).y() - coordinateList.get(j).y() :
                        coordinateList.get(j).y() - coordinateList.get(i).y();
                Long test = (width + 1L) * (height + 1L);

                if (biggestArea < test) {
                    biggestArea = test;
                }
            }
        }

        return biggestArea.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Coordinate2D> coordinateList = new ArrayList<>();

        for (String line : lines) {
            List<String> split = Arrays.stream(line.split(",")).toList();
            coordinateList.add(new Coordinate2D(Long.parseLong(split.getFirst()), Long.parseLong(split.getLast())));
        }

        List<XLine> xlines = new ArrayList<>();
        List<YLine> ylines = new ArrayList<>();

        for (int i = 0; i < coordinateList.size(); i++) {
            Coordinate2D currentCoordinate = coordinateList.get(i);
            Coordinate2D previousCoordinate = coordinateList.get(i == 0 ? coordinateList.size() - 1 : i - 1);

            if (Objects.equals(previousCoordinate.x(), currentCoordinate.x())) {
                Long yStart = previousCoordinate.y() <= currentCoordinate.y() ? previousCoordinate.y() : currentCoordinate.y();
                Long yEnd = previousCoordinate.y() > currentCoordinate.y() ? previousCoordinate.y() : currentCoordinate.y();
                xlines.add(new XLine(previousCoordinate.x(), yStart, yEnd));
            } else {
                Long xStart = previousCoordinate.x() <= currentCoordinate.x() ? previousCoordinate.x() : currentCoordinate.x();
                Long xEnd = previousCoordinate.x() > currentCoordinate.x() ? previousCoordinate.x() : currentCoordinate.x();
                ylines.add(new YLine(previousCoordinate.y(), xStart, xEnd));
            }
        }

        xlines.sort(Comparator.comparing((XLine x) -> x.x));
        ylines.sort(Comparator.comparing((YLine y) -> y.y));

        Long minX = xlines.getFirst().x();
        Long maxX = xlines.getLast().x();

        Long minY = ylines.getFirst().y();
        Long maxY = ylines.getLast().y();

        AtomicLong previousXRaycastMinRange = new AtomicLong(0L);
        AtomicLong previousXRaycastMaxRange = new AtomicLong(0L);

        List<Coordinate2D> untiledCoordinates = new ArrayList<>();

        AtomicInteger intersectionCount = new AtomicInteger(0);

        // raycast all Y lines
        for (long y = minY; y <= maxY; y++) {

            if (y % 1000 == 0) {
                System.out.println("y raycasting: " + y);
            }

            intersectionCount.set(0);
            final Boolean[] insideShape = {false};

            final Boolean[] ridingTopLine = {false};
            final Boolean[] ridingBottomLine = {false};

            final Boolean[] nextIterationInsideShape = {false};

            long finalY1 = y;
            List<XLine> rangeNarrowingLines = xlines.stream().filter(line -> line.yStart <= finalY1 && line.yEnd >= finalY1).toList();
            List<XLine> rangeNarrowingLinesPlus1 = xlines.stream().filter(line -> line.yStart <= finalY1 + 1 && line.yEnd >= finalY1 - 1).toList();

            for (long x = rangeNarrowingLinesPlus1.getFirst().x() - 1; x <= rangeNarrowingLinesPlus1.getLast().x() + 1; x++) {

                if (nextIterationInsideShape[0] == false) {
                    insideShape[0] = false;
                }

                long finalX = x;
                long finalY = y;

                Optional<XLine> xLine = xlines.stream().filter(line -> line.x() == finalX && line.yStart <= finalY && line.yEnd >= finalY).findFirst();

                if (xLine.isEmpty() && insideShape[0] == true) {
                    x = rangeNarrowingLines.stream().filter(line -> line.x() >= finalX).findFirst().map(line -> line.x() - 1).orElse(x);
                }

                xLine.ifPresent(xline -> {

                    intersectionCount.getAndAdd(1);

                    if (!insideShape[0]) {
                        insideShape[0] = true;
                        nextIterationInsideShape[0] = true;

                        if (xline.yEnd == finalY) {
                            ridingBottomLine[0] = true;
                        } else if (xline.yStart == finalY) {
                            ridingTopLine[0] = true;
                        }

                    } else {
                        if (ridingTopLine[0]) {
                            if (xline.yStart == finalY) {
                                if (intersectionCount.get() % 2 == 0) {
                                    nextIterationInsideShape[0] = false;
                                } else {
                                    nextIterationInsideShape[0] = true;
                                }
                                ridingTopLine[0] = false;
                            } else if (xline.yEnd == finalY) {
                                if (intersectionCount.get() % 2 == 1) {
                                    nextIterationInsideShape[0] = false;
                                } else {
                                    nextIterationInsideShape[0] = true;
                                }
                                ridingTopLine[0] = false;
                            }
                        } else if (ridingBottomLine[0]) {
                            if (xline.yEnd == finalY) {
                                if (intersectionCount.get() % 2 == 0) {
                                    nextIterationInsideShape[0] = false;
                                } else {
                                    nextIterationInsideShape[0] = true;
                                }
                                ridingBottomLine[0] = false;
                            } else if (xline.yStart == finalY) {
                                if (intersectionCount.get() % 2 == 1) {
                                    nextIterationInsideShape[0] = false;
                                } else {
                                    nextIterationInsideShape[0] = true;
                                }
                                ridingBottomLine[0] = false;
                            }
                        } else {

                            if (xline.yEnd == finalY) {
                                ridingBottomLine[0] = true;
                                nextIterationInsideShape[0] = true;
                            } else if (xline.yStart == finalY) {
                                ridingTopLine[0] = true;
                                nextIterationInsideShape[0] = true;
                            } else {
                                nextIterationInsideShape[0] = false;
                            }
                        }
                    }
                });

                if (!insideShape[0]) {
                    untiledCoordinates.add(new Coordinate2D(x, y));
                }
            }
        }

        Long biggestArea = 0L;


        for (int i = 0; i < coordinateList.toArray().length - 1; i++) {
            System.out.println("i: " + i);
            for (int j = i + 1; j < coordinateList.toArray().length; j++) {

                Long smallX = coordinateList.get(i).x() <= coordinateList.get(j).x() ? coordinateList.get(i).x() : coordinateList.get(j).x();
                Long smallY = coordinateList.get(i).y() <= coordinateList.get(j).y() ? coordinateList.get(i).y() : coordinateList.get(j).y();
                Long bigX = coordinateList.get(i).x() > coordinateList.get(j).x() ? coordinateList.get(i).x() : coordinateList.get(j).x();
                Long bigY = coordinateList.get(i).y() > coordinateList.get(j).y() ? coordinateList.get(i).y() : coordinateList.get(j).y();

                Long width = coordinateList.get(i).x() > coordinateList.get(j).x() ?
                        coordinateList.get(i).x() - coordinateList.get(j).x() :
                        coordinateList.get(j).x() - coordinateList.get(i).x();
                Long height = coordinateList.get(i).y() > coordinateList.get(j).y() ?
                        coordinateList.get(i).y() - coordinateList.get(j).y() :
                        coordinateList.get(j).y() - coordinateList.get(i).y();
                Long test = (width + 1L) * (height + 1L);

                if (test <= biggestArea) {
                    continue;
                }

                // if any untiled coordinate is inside the rectangle, skip

                if (untiledCoordinates.stream().anyMatch(coordinate2D -> coordinate2D.x() >= smallX && coordinate2D.x() <= bigX
                        && coordinate2D.y() >= smallY && coordinate2D.y() <= bigY)) {
                    continue;
                }

                if (biggestArea <= test) {
                    biggestArea = test;
                }
            }
        }


        return biggestArea.toString();
    }

    private record XLine(Long x, Long yStart, Long yEnd) {
    }

    private record YLine(Long y, Long xStart, Long xEnd) {
    }

}