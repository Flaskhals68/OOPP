package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class PathfindingHelper {

    // Helper class for storing remaining steps in entry
    private static class Entry {
        private Tile tile;
        private int remainingSteps;
        public Entry(Tile tile, int remainingSteps) {
            this.tile = tile;
            this.remainingSteps = remainingSteps;
        }

        public Tile getTile() { return tile; }
        public int getRemainingSteps() { return remainingSteps; }
    }

    /**
     * Get all tiles that can be reached from specified tile with given amounts of steps
     * @param tile
     * @param steps
     * @return Set of Points with all legal positions
     */
    public static Set<Position> getSurrounding(Tile tile, int steps) {
    Set<Tile> visited = new HashSet<>();
    Queue<Entry> queue = new LinkedList<>();
    Set<Position> positions = new HashSet<>();

    // Perform Breadth-first search
    queue.add(new Entry(tile, steps));
    while (!queue.isEmpty()) {
        Entry entry = queue.remove();
        if (!entry.getTile().isEmpty() && !visited.add(entry.getTile())) continue;
        Position entryPos = entry.tile.getPos();
        Position p = new Position(entryPos.getX(), entryPos.getY(), entryPos.getFloor());
        positions.add(p);

        if (entry.remainingSteps > 0) {
            for (Tile neighbor : entry.getTile().getNeighbors()) {
                queue.add(new Entry(neighbor, entry.getRemainingSteps()-1));
            }
        }
    }
    return positions;
}

    private static class Edge {
        private Tile start;
        private Tile end;
        private int weight;

        public Edge(Tile start, Tile end) {
            this(start, end, 1);
        } 

        public Edge(Tile start, Tile end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public Tile getStart() { return start; }
        public Tile getEnd() { return end; }
        public int getWeight() { return weight; }
    }

    /**
     * Helper class for storing entries in PriorityQueue
     */
    private static class AStarEntry implements Comparable<AStarEntry> {
        private Tile current;
        private Edge lastEdge;
        private AStarEntry backPointer;
        private double costToHere;
        private double guessedCost;
        private Set<Edge> outgoingEdges;

        public AStarEntry(Tile current, Edge lastEdge, AStarEntry backPointer, double costToHere, double guessedCost) {
            this.current = current;
            this.lastEdge = lastEdge;
            this.backPointer = backPointer;
            this.costToHere = costToHere;
            this.guessedCost = guessedCost;
            this.outgoingEdges = initOutgoingEdges(current);
        }

        private Set<Edge> initOutgoingEdges(Tile tile) {
            if (tile == null) throw new IllegalArgumentException("Tile cannot be null");
            Set<Edge> outgoingEdges = new HashSet<>();
            for (Tile neighbor : tile.getNeighbors()) {
                Edge e = new Edge(tile, neighbor);
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

        public Tile getCurrent() { return current; }
        public AStarEntry getBackPointer() { return backPointer; }
        public double getCostToHere() { return costToHere; }
        public double getGuessedCost() { return guessedCost; }
        public Set<Edge> getOutgoingEdges() { return outgoingEdges; }
    }

    /**
     * Get a list where each element is the next step in order of the shortest path from start to goal
     * @param start
     * @param goal
     * @return List representing the shortest path between two tiles
     */
    public static List<Position> getShortestPath(Tile start, Tile goal) {
        AStarEntry finalEntry = aStarSearch(start, goal);
        return extractPath(finalEntry);  
    }

    /**
     * Get the shortest path from start to position next to goal
     * @param start
     * @param goal
     * @return List of tiles representing the shortest path between start and tile next to goal
     */
    public static List<Position> getPathNextTo(Position start, Position goal) {
        Tile startTile = Model.getInstance().getTile(start);
        Tile goalTile = Model.getInstance().getTile(goal);
        AStarEntry finalEntry = aStarSearch(startTile, goalTile);
        List<Position> path = extractPath(finalEntry);
        path.remove(path.size()-1);
        return path;
    }

    private static AStarEntry aStarSearch(Tile start, Tile goal) {
        PriorityQueue<AStarEntry> pq = new PriorityQueue<>();
        Set<Tile> visited = new HashSet<>();
        AStarEntry startEntry = new AStarEntry(start, null, null, 0, 0);
        
        pq.add(startEntry);

        while (!pq.isEmpty()) {
            AStarEntry entry = pq.poll();
            if (visited.contains(entry.getCurrent()))
                continue;
                if (entry.getCurrent() == goal)
                    return entry;
                if (entry != startEntry && !entry.getCurrent().isEmpty())
                    continue;
            for (Edge edge : entry.getOutgoingEdges()) {
                double costToHere = entry.getCostToHere() + edge.getWeight();
                double guessedCost = guessCost(edge.getEnd(), goal);
                AStarEntry newEntry = new AStarEntry(edge.getEnd(), edge, entry, costToHere, guessedCost);
                pq.add(newEntry);
            }
            visited.add(entry.current);
        }

        // If no path was found
        throw new IllegalArgumentException("No path between tiles exists");
    }
    
    private static double guessCost(Tile current, Tile goal) {
        Position currentPos = current.getPos();
        Position goalPos = goal.getPos();
        int dx = goalPos.getX() - currentPos.getX();
        int dy = goalPos.getY() - currentPos.getY();
        return Math.sqrt( dx*dx + dy*dy );
    }
    
    private static List<Position> extractPath(AStarEntry entry) {
        if (entry == null) throw new IllegalArgumentException("entry must not be null");
        LinkedList<Position> path = new LinkedList<>();
        while (entry.backPointer != null) {
            Tile current = entry.getCurrent();
            Position currentPos = current.getPos();
            path.addFirst(new Position(currentPos.getX(), currentPos.getY(), current.getFloor()));
            entry = entry.getBackPointer();
        }
        return path;
    }


    /**
     * @param start
     * @param goal
     * @return The shortest path from start to goal.
     * If the goal cannot be reached, the path to the closest tile is returned
     */
    public static List<Position> pathToClosest(Tile start, Tile goal) {
        PriorityQueue<AStarEntry> pq = new PriorityQueue<>();
        Set<Tile> visited = new HashSet<>();
        AStarEntry startEntry = new AStarEntry(start, null, null, 0, guessCost(start, goal));
        AStarEntry closest = startEntry;
        pq.add(startEntry);
        
        AStarEntry entry = null;
        while (!pq.isEmpty()) {
            entry = pq.poll();
            if (visited.contains(entry.getCurrent()))
                continue;
            if (entry != startEntry && !entry.getCurrent().isEmpty())
                continue;
            if (entry.getGuessedCost() < closest.getGuessedCost())
                closest = entry;
            for (Edge edge : entry.getOutgoingEdges()) {
                double costToHere = entry.getCostToHere() + edge.getWeight();
                double guessedCost = guessCost(edge.getEnd(), goal);
                AStarEntry newEntry = new AStarEntry(edge.getEnd(), edge, entry, costToHere, guessedCost);
                if (edge.getEnd() == goal) {
                    if (goal.isEmpty()) {
                        closest = newEntry;
                    }
                    break;
                }
                pq.add(newEntry);
            }
            visited.add(entry.current);
        }

        List<Position> path = extractPath(closest);
        return path;
    }
}
