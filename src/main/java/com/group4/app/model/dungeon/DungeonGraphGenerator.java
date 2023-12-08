package com.group4.app.model.dungeon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class DungeonGraphGenerator {
    public static DungeonGraph generate(int size) {
        DungeonGraph graph = new DungeonGraph(size);
        
        fillGraphWithRooms(graph, size);

        addCorridors(graph);

        ensureGraphIsConnected(graph);

        return graph;
    }

    private static void fillGraphWithRooms(DungeonGraph graph, int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                graph.addRoom(new Room(x, y));
            }
        }
        graph.updateAdjacent();
    }

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

    private static void ensureGraphIsConnected(DungeonGraph graph) {
        Room start = graph.getMatrix()[0][0];
        for (int x = 0; x < graph.getMatrix().length; x++) {
            for (int y = 0; y < graph.getMatrix()[x].length; y++) {
                Room room = graph.getMatrix()[x][y];
                if (room == null) continue;
                while (!pathExists(start, room)) {
                    System.out.println("No path to " + room.getX() + ", " + room.getY() + " from start. Adding corridor.");
                    Room other = getRandomElement(room.getAdjacent());
                    graph.addCorridor(new Corridor(room, other));
                }
            }
        }
    }

    private static class Edge {
        private Room start;
        private Room end;
        private int weight;

        public Edge(Room start, Room end) {
            this(start, end, 1);
        } 

        public Edge(Room start, Room end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public Room getStart() { return start; }
        public Room getEnd() { return end; }
        public int getWeight() { return weight; }
    }

    /**
     * Helper class for storing entries in PriorityQueue
     */
    private static class AStarEntry implements Comparable<AStarEntry> {
        private Room current;
        private Edge lastEdge;
        private AStarEntry backPointer;
        private double costToHere;
        private double guessedCost;
        private Set<Edge> outgoingEdges;

        public AStarEntry(Room current, Edge lastEdge, AStarEntry backPointer, double costToHere, double guessedCost) {
            this.current = current;
            this.lastEdge = lastEdge;
            this.backPointer = backPointer;
            this.costToHere = costToHere;
            this.guessedCost = guessedCost;
            this.outgoingEdges = initOutgoingEdges(current);
        }

        private Set<Edge> initOutgoingEdges(Room room) {
            if (room == null) throw new IllegalArgumentException("Room cannot be null");
            Set<Edge> outgoingEdges = new HashSet<>();
            for (Room neighbor : room.getConnected()) {
                Edge e = new Edge(room, neighbor);
                outgoingEdges.add(e);
            }
            return outgoingEdges;
        }

        @Override
        public int compareTo(AStarEntry other) {
            if (this.getGuessedCost() < other.getGuessedCost()) { return -1; }
            if (this.getCostToHere() + this.getGuessedCost() > other.getCostToHere() + other.getGuessedCost()) { return 1; }
            else return 0;
        }

        public Room getCurrent() { return current; }
        public AStarEntry getBackPointer() { return backPointer; }
        public double getCostToHere() { return costToHere; }
        public double getGuessedCost() { return guessedCost; }
        public Set<Edge> getOutgoingEdges() { return outgoingEdges; }
    }

    /**
     * Get a list where each element is the next step in order of the shortest path from start to goal
     * @param start
     * @param goal
     * @param tc
     * @return List representing the shortest path between two tiles
     */
    public static boolean pathExists(Room start, Room goal) {
        return aStarSearch(start, goal) != null;
    }

    private static AStarEntry aStarSearch(Room start, Room goal) {
        PriorityQueue<AStarEntry> pq = new PriorityQueue<>();
        Set<Room> visited = new HashSet<>();
        AStarEntry startEntry = new AStarEntry(start, null, null, 0, 0);
        
        pq.add(startEntry);

        while (!pq.isEmpty()) {
            AStarEntry entry = pq.poll();
            if (visited.contains(entry.getCurrent()))
                continue;
            if (entry.getCurrent() == goal)
                return entry;
            for (Edge edge : entry.getOutgoingEdges()) {
                double costToHere = entry.getCostToHere() + edge.getWeight();
                double guessedCost = guessCost(edge.getEnd(), goal);
                AStarEntry newEntry = new AStarEntry(edge.getEnd(), edge, entry, costToHere, guessedCost);
                pq.add(newEntry);
            }
            visited.add(entry.current);
        }

        // If no path was found
        return null;
    }
    
    private static double guessCost(Room current, Room goal) {
        int dx = goal.getX() - current.getX();
        int dy = goal.getY() - current.getY();
        return Math.sqrt( dx*dx + dy*dy );
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

    public static class Room {
        private int x;
        private int y;
        private Set<Room> adjacent;
        private Set<Corridor> corridors;

        public Room(int x, int y) {
            this.x = x;
            this.y = y;
            this.adjacent = new HashSet<>();
            this.corridors = new HashSet<>();
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public Set<Room> getAdjacent() {
            return this.adjacent;
        }

        public Set<Corridor> getCorridors() {
            return this.corridors;
        }

        private void updateAdjacent(Room[][] matrix) {
            this.adjacent.clear();

            if (x - 1 >= 0 && matrix[x - 1][y] != null) {
                this.adjacent.add(matrix[x - 1][y]);
                matrix[x - 1][y].addAdjacent(this);
            }
            if (x + 1 < matrix.length && matrix[x + 1][y] != null) {
                this.adjacent.add(matrix[x + 1][y]);
                matrix[x + 1][y].addAdjacent(this);
            }
            if (y - 1 >= 0 && matrix[x][y - 1] != null) {
                this.adjacent.add(matrix[x][y - 1]);
                matrix[x][y - 1].addAdjacent(this);
            }
            if (y + 1 < matrix[x].length && matrix[x][y + 1] != null) {
                this.adjacent.add(matrix[x][y + 1]);
                matrix[x][y + 1].addAdjacent(this);
            }
        }

        private void addAdjacent(Room room) {
            if (room == null) {
                throw new IllegalArgumentException("Room cannot be null");
            }
            this.adjacent.add(room);
        }

        private void addCorridor(Corridor corridor) {
            if (corridor == null) {
                throw new IllegalArgumentException("Corridor cannot be null");
            }
            this.corridors.add(corridor);
        }

        private void removeCorridor(Corridor corridor) {
            if (corridor == null) {
                throw new IllegalArgumentException("Corridor cannot be null");
            }
            this.corridors.remove(corridor);
        }

        private boolean isAdjacent(Room room) {
            return this.adjacent.contains(room);
        }

        private boolean isConnected(Room room) {
            for (Corridor corridor : this.corridors) {
                if (corridor.contains(room)) {
                    return true;
                }
            }
            return false;
        }

        private Set<Room> getConnected() {
            Set<Room> connected = new HashSet<>();
            for (Corridor corridor : this.corridors) {
                connected.add(corridor.getOther(this));
            }
            return connected;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Room other = (Room) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    public static class Corridor {
        private Room room1;
        private Room room2;
        private Direction direction;

        public Corridor(Room room1, Room room2) {
            if (room1 == null || room2 == null) {
                throw new IllegalArgumentException("Rooms cannot be null");
            }
            this.room1 = room1;
            this.room2 = room2;
            calculateDirection();
        }

        private void calculateDirection() {
            if (room1.getX() == room2.getX()) {
                if (room1.getY() < room2.getY()) {
                    this.direction = Direction.SOUTH;
                } else {
                    this.direction = Direction.NORTH;
                }
            } else if (room1.getY() == room2.getY()) {
                if (room1.getX() < room2.getX()) {
                    this.direction = Direction.EAST;
                } else {
                    this.direction = Direction.WEST;
                }
            } else {
                throw new IllegalArgumentException("Rooms must be adjacent");
            }
        }

        public Direction getDirection() {
            return this.direction;
        }

        public Room getRoom1() {
            return this.room1;
        }

        public Room getRoom2() {
            return this.room2;
        }

        public boolean contains(Room room) {
            return room1 == room || room2 == room;
        }

        public Room getOther(Room room) {
            if (room1 == room) {
                return room2;
            } else if (room2 == room) {
                return room1;
            } else {
                throw new IllegalArgumentException("Room is not connected to this corridor");
            }
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((room1 == null) ? 0 : room1.hashCode());
            result = prime * result + ((room2 == null) ? 0 : room2.hashCode());
            int result2 = 1;
            result2 = prime * result2 + ((room2 == null) ? 0 : room2.hashCode());
            result2 = prime * result2 + ((room1 == null) ? 0 : room1.hashCode());
            
            return result + result2;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Corridor other = (Corridor) obj;
            if (room1 == null) {
                if (other.room1 != null)
                    return false;
            } else if (!(room1.equals(other.room1) || room1.equals(other.room2)))
                return false;
            if (room2 == null) {
                if (other.room2 != null)
                    return false;
            } else if (!(room2.equals(other.room2) || room2.equals(other.room1)))
                return false;
            return true;
        }
    }

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
