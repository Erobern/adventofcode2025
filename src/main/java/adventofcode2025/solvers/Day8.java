package adventofcode2025.solvers;

import adventofcode2025.fileloaders.FileLoaders;
import adventofcode2025.utils.records.Coordinate3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day8 {
    public static String Puzzle1(String input, Integer interactions) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Junction> junctions = new ArrayList<>();

        HashMap<Double, Link> globalPossibleLinkMap = new HashMap<>();


        lines.forEach(line -> {
            List<String> coordinates = Arrays.stream(line.split(",")).toList();
            Coordinate3D coordinate3D = new Coordinate3D(Long.parseLong(coordinates.get(0)), Long.parseLong(coordinates.get(1)), Long.parseLong(coordinates.get(2)));
            junctions.add(new Junction(coordinate3D, coordinate3D.toString()));
        });

        for (int i = 0; i < junctions.size() - 1; i++) {
            for (int j = i + 1; j < junctions.size(); j++) {
                Link link = new Link(junctions.get(i).id, junctions.get(j).id);
                globalPossibleLinkMap.put(distanceBetweenTwoPoints(junctions.get(i).coordinate3D, junctions.get(j).coordinate3D), link);
            }
        }

        List<Double> sortedDoubles = globalPossibleLinkMap.keySet().stream().sorted().toList();

        HashMap<Integer, List<String>> circuitMap = new HashMap<>();

        for (int i = 0; i < interactions && i < sortedDoubles.size(); i++) {
            Link link = globalPossibleLinkMap.get(sortedDoubles.get(i));

            Boolean circuitFound = false;

            for (Integer circuitId : circuitMap.keySet()) {
                if (circuitMap.get(circuitId).stream().anyMatch(junctionId -> junctionId.equals(link.junction1Id()))) {
                    if (circuitMap.get(circuitId).stream().noneMatch(junctionId -> junctionId.equals(link.junction2Id()))) {
                        circuitMap.get(circuitId).add(link.junction2Id());

                        // check if the circuit is now connected to any other circuit
                        List<String> otherCircuit = new ArrayList<>();
                        Integer otherCircuitIdToRemove = 0;
                        Boolean mergeCircuits = false;
                        for (Integer otherCircuitId : circuitMap.keySet()) {
                            if (!otherCircuitId.equals(circuitId)) {
                                if (circuitMap.get(otherCircuitId).stream().anyMatch(junctionId -> junctionId.equals(link.junction2Id()))) {
                                    // merge circuits
                                    otherCircuit = circuitMap.get(otherCircuitId);
                                    otherCircuitIdToRemove = otherCircuitId;
                                    mergeCircuits = true;
                                    break;
                                }
                            }
                        }
                        if (mergeCircuits) {
                            circuitMap.remove(otherCircuitIdToRemove);

                            otherCircuit.remove(link.junction2Id);
                            circuitMap.get(circuitId).addAll(otherCircuit);
                        }
                    } else {
                        //interactions++;
                    }
                    circuitFound = true;
                    break;
                } else if (circuitMap.get(circuitId).stream().anyMatch(junctionId -> junctionId.equals(link.junction2Id()))) {
                    if (circuitMap.get(circuitId).stream().noneMatch(junctionId -> junctionId.equals(link.junction1Id()))) {
                        circuitMap.get(circuitId).add(link.junction1Id());

                        // check if the circuit is now connected to any other circuit
                        List<String> otherCircuit = new ArrayList<>();
                        Integer otherCircuitIdToRemove = 0;
                        Boolean mergeCircuits = false;
                        for (Integer otherCircuitId : circuitMap.keySet()) {
                            if (!otherCircuitId.equals(circuitId)) {
                                if (circuitMap.get(otherCircuitId).stream().anyMatch(junctionId -> junctionId.equals(link.junction1Id()))) {
                                    // merge circuits
                                    otherCircuit = circuitMap.get(otherCircuitId);
                                    otherCircuitIdToRemove = otherCircuitId;
                                    mergeCircuits = true;
                                    break;
                                }
                            }
                        }
                        if (mergeCircuits) {
                            circuitMap.remove(otherCircuitIdToRemove);

                            otherCircuit.remove(link.junction1Id);
                            circuitMap.get(circuitId).addAll(otherCircuit);
                        }
                    } else {
                        //interactions++;
                    }
                    circuitFound = true;
                    break;
                }
            }

            if (!circuitFound) {
                List<String> newCircuit = new ArrayList<>();
                newCircuit.add(link.junction1Id());
                newCircuit.add(link.junction2Id());
                circuitMap.put(i, newCircuit);
            }
        }

        List<Integer> sortedCircuitSizes = circuitMap.keySet().stream().map(circuitId -> circuitMap.get(circuitId).size()).sorted().toList().reversed();

        return String.valueOf(sortedCircuitSizes.get(0) * sortedCircuitSizes.get(1) * sortedCircuitSizes.get(2));
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Junction> junctions = new ArrayList<>();

        HashMap<Double, Link> globalPossibleLinkMap = new HashMap<>();


        lines.forEach(line -> {
            List<String> coordinates = Arrays.stream(line.split(",")).toList();
            Coordinate3D coordinate3D = new Coordinate3D(Long.parseLong(coordinates.get(0)), Long.parseLong(coordinates.get(1)), Long.parseLong(coordinates.get(2)));
            junctions.add(new Junction(coordinate3D, coordinate3D.toString()));
        });

        for (int i = 0; i < junctions.size() - 1; i++) {
            for (int j = i + 1; j < junctions.size(); j++) {
                Link link = new Link(junctions.get(i).id, junctions.get(j).id);
                globalPossibleLinkMap.put(distanceBetweenTwoPoints(junctions.get(i).coordinate3D, junctions.get(j).coordinate3D), link);
            }
        }

        List<Double> sortedDoubles = globalPossibleLinkMap.keySet().stream().sorted().toList();

        HashMap<Integer, List<String>> circuitMap = new HashMap<>();

        List<Link> mostRecentCircuitMerge = new ArrayList<>();

        for (int i = 0; i < sortedDoubles.size(); i++) {
            Link link = globalPossibleLinkMap.get(sortedDoubles.get(i));

            Boolean circuitFound = false;

            for (Integer circuitId : circuitMap.keySet()) {
                if (circuitMap.get(circuitId).stream().anyMatch(junctionId -> junctionId.equals(link.junction1Id()))) {
                    if (circuitMap.get(circuitId).stream().noneMatch(junctionId -> junctionId.equals(link.junction2Id()))) {
                        circuitMap.get(circuitId).add(link.junction2Id());

                        // check if the circuit is now connected to any other circuit
                        List<String> otherCircuit = new ArrayList<>();
                        Integer otherCircuitIdToRemove = 0;
                        Boolean mergeCircuits = false;
                        for (Integer otherCircuitId : circuitMap.keySet()) {
                            if (!otherCircuitId.equals(circuitId)) {
                                if (circuitMap.get(otherCircuitId).stream().anyMatch(junctionId -> junctionId.equals(link.junction2Id()))) {
                                    // merge circuits
                                    otherCircuit = circuitMap.get(otherCircuitId);
                                    otherCircuitIdToRemove = otherCircuitId;
                                    mergeCircuits = true;
                                    break;
                                }
                            }
                        }
                        if (mergeCircuits) {
                            circuitMap.remove(otherCircuitIdToRemove);

                            otherCircuit.remove(link.junction2Id);
                            circuitMap.get(circuitId).addAll(otherCircuit);


                        }

                        if (circuitMap.size() == 1 && circuitMap.get(circuitId).size() == junctions.size()) {
                            mostRecentCircuitMerge.add(link);
                        }
                    } else {
                        //interactions++;
                    }
                    circuitFound = true;
                    break;
                } else if (circuitMap.get(circuitId).stream().anyMatch(junctionId -> junctionId.equals(link.junction2Id()))) {
                    if (circuitMap.get(circuitId).stream().noneMatch(junctionId -> junctionId.equals(link.junction1Id()))) {
                        circuitMap.get(circuitId).add(link.junction1Id());

                        // check if the circuit is now connected to any other circuit
                        List<String> otherCircuit = new ArrayList<>();
                        Integer otherCircuitIdToRemove = 0;
                        Boolean mergeCircuits = false;
                        for (Integer otherCircuitId : circuitMap.keySet()) {
                            if (!otherCircuitId.equals(circuitId)) {
                                if (circuitMap.get(otherCircuitId).stream().anyMatch(junctionId -> junctionId.equals(link.junction1Id()))) {
                                    // merge circuits
                                    otherCircuit = circuitMap.get(otherCircuitId);
                                    otherCircuitIdToRemove = otherCircuitId;
                                    mergeCircuits = true;
                                    break;
                                }
                            }
                        }
                        if (mergeCircuits) {
                            circuitMap.remove(otherCircuitIdToRemove);

                            otherCircuit.remove(link.junction1Id);
                            circuitMap.get(circuitId).addAll(otherCircuit);

                        }
                        if (circuitMap.size() == 1 && circuitMap.get(circuitId).size() == junctions.size()) {
                            mostRecentCircuitMerge.add(link);
                        }
                    } else {
                        //interactions++;
                    }
                    circuitFound = true;
                    break;
                }
            }

            if (!circuitFound) {
                List<String> newCircuit = new ArrayList<>();
                newCircuit.add(link.junction1Id());
                newCircuit.add(link.junction2Id());
                circuitMap.put(i, newCircuit);
            }
        }

        List<Integer> sortedCircuitSizes = circuitMap.keySet().stream().map(circuitId -> circuitMap.get(circuitId).size()).sorted().toList().reversed();

        return String.valueOf(Long.parseLong(Arrays.stream(mostRecentCircuitMerge.getFirst().junction1Id.split(",")).toList().getFirst()) *
                Long.parseLong(Arrays.stream(mostRecentCircuitMerge.getFirst().junction2Id.split(",")).toList().getFirst()));
    }

    private static Double distanceBetweenTwoPoints(Coordinate3D coordinate1, Coordinate3D coordinate2) {
        Long xSquare = (coordinate1.x() - coordinate2.x()) * (coordinate1.x() - coordinate2.x());
        Long ySquare = (coordinate1.y() - coordinate2.y()) * (coordinate1.y() - coordinate2.y());
        Long zSquare = (coordinate1.z() - coordinate2.z()) * (coordinate1.z() - coordinate2.z());

        Long sum = xSquare + ySquare + zSquare;

        return Math.sqrt(sum.doubleValue());
    }

    private record Junction(Coordinate3D coordinate3D, String id) {
    }

    private record Link(String junction1Id, String junction2Id) {
    }
}