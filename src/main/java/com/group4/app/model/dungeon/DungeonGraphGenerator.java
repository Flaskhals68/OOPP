package com.group4.app.model.dungeon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class DungeonGraphGenerator {
    /**
     * Generates a dungeon graph of the specified size.
     *
     * @param size the size of the dungeon graph
     * @return the generated dungeon graph
     */
    public static DungeonGraph generate(int size) {
        DungeonGraph graph = new DungeonGraph(size);
        
        fillGraphWithRooms(graph);

        addCorridors(graph);

        ensureGraphIsConnected(graph);

        return graph;
    }

    /**
     * Fills the given DungeonGraph with rooms.
     * 
     * @param graph the DungeonGraph to fill with rooms
     */
    private static void fillGraphWithRooms(DungeonGraph graph) {
        for (int x = 0; x < graph.getMatrix().length; x++) {
            for (int y = 0; y < graph.getMatrix()[x].length; y++) {
                graph.addRoom(new Room(x, y));
            }
        }
        graph.updateAdjacent();
    }

    /**
     * Adds corridors to the dungeon graph.
     * 
     * @param graph the dungeon graph to add corridors to
     */
    private static void addCorridors(DungeonGraph graph) {
        Queue<Room> rooms = new LinkedList<>();
        Set<Room> visited = new HashSet<>();
        rooms.add(graph.getMatrix()[0][0]);
        while (!rooms.isEmpty()) {
            Room room = rooms.poll();
            if (visited.contains(room)) continue;
            visited.add(room);
            Set<Room> adjacent = room.getAdjacent();
            for (Room other : adjacent) {
                if (!visited.contains(other)) {
                    rooms.add(other);
                }
            }
            double random = Math.random();
            if (random < 0.5) {
                Room other = getRandomElement(adjacent);
                graph.addCorridor(new Corridor(room, other));
            }
            random = Math.random();
            if (random < 0.5) {
                Room other = getRandomElement(adjacent);
                graph.addCorridor(new Corridor(room, other));
            }
        }
    }

    /**
     * Ensures that the given DungeonGraph is connected by adding corridors between rooms if necessary.
     * 
     * @param graph The DungeonGraph to ensure connectivity for.
     */
    private static void ensureGraphIsConnected(DungeonGraph graph) {
        Room start = graph.getMatrix()[0][0];
        for (int x = 0; x < graph.getMatrix().length; x++) {
            for (int y = 0; y < graph.getMatrix()[x].length; y++) {
                Room room = graph.getMatrix()[x][y];
                if (room == null) continue;
                while (!DungeonGraphPathfinder.pathExists(start, room)) {
                    System.out.println("No path to " + room.getX() + ", " + room.getY() + " from start. Adding corridor.");
                    Room other = getRandomElement(room.getAdjacent());
                    graph.addCorridor(new Corridor(room, other));
                }
            }
        }
    }

    private static <T> T getRandomElement(Set<T> set) {
        int randomIndex = new Random().nextInt(set.size());
        int i = 0;
        for (T element : set) {
            if (i == randomIndex) {
                return element;
            }
            i++;
        }
        return null;
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    /**
    * Represents a graph that represents a dungeon layout.
    * The graph is a grid of rooms with corridors connecting them.
    */
    public static class DungeonGraph {
        private Room[][] matrix;
        private Set<Room> rooms;
        private Set<Corridor> corridors;

        public DungeonGraph(int size) {
            this.matrix = new Room[size][size];
            this.rooms = new HashSet<>();
            this.corridors = new HashSet<>();
        }

        public Room[][] getMatrix() {
            return this.matrix;
        }

        public Set<Room> getRooms() {
            return this.rooms;
        }

        public Set<Corridor> getCorridors() {
            return this.corridors;
        }

        public void addRoom(Room room) {
            if (room == null) {
                throw new IllegalArgumentException("Room cannot be null");
            }
            this.rooms.add(room);
            this.matrix[room.x][room.y] = room;
            for (Corridor corridor : room.getCorridors()) {
                this.corridors.add(corridor);
            }
            for (Room adjacent : room.getAdjacent()) {
                this.corridors.addAll(adjacent.getCorridors());
            }
        }

        public void removeRoom(Room room) {
            if (room == null) {
                throw new IllegalArgumentException("Room cannot be null");
            }
            this.rooms.remove(room);
            this.matrix[room.x][room.y] = null;
            this.corridors.removeIf(c -> c.contains(room));
        }

        public void addCorridor(Corridor corridor) {
            if (corridor == null) {
                throw new IllegalArgumentException("Corridor cannot be null");
            }
            this.corridors.add(corridor);
            corridor.getRoom1().addCorridor(corridor);
            corridor.getRoom2().addCorridor(corridor);
        }

        public void removeCorridor(Corridor corridor) {
            if (corridor == null) {
                throw new IllegalArgumentException("Corridor cannot be null");
            }
            this.corridors.remove(corridor);
            corridor.getRoom1().removeCorridor(corridor);
            corridor.getRoom2().removeCorridor(corridor);
        }

        public boolean isConnected(Room room1, Room room2) {
            if (room1 == null || room2 == null) {
                throw new IllegalArgumentException("Rooms cannot be null");
            }
            return room1.isConnected(room2);
        }

        public boolean isAdjacent(Room room1, Room room2) {
            if (room1 == null || room2 == null) {
                throw new IllegalArgumentException("Rooms cannot be null");
            }
            return room1.isAdjacent(room2);
        }

        public void updateAdjacent() {
            for (Room room : this.rooms) {
                room.updateAdjacent(this.matrix);
            }
        }
    }
}
