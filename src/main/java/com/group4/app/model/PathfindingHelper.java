package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import java.awt.Point;
import java.awt.geom.Point2D;

public class PathfindingHelper {

    // Helper class for storing remaining steps in entry
    private static class Entry {
        public Tile tile;
        public int remainingSteps;
        public Entry(Tile tile, int remainingSteps) {
            this.tile = tile;
            this.remainingSteps = remainingSteps;
        }
    }

    /**
     * Get all tiles that can be reached from specified tile with given amounts of steps
     * @param tile
     * @param steps
     * @return Set of Points with all legal coordinates
     */
    public static List<Point2D> getSurrounding(Tile tile, int steps) {
        // Perform depth-first search to find all tiles in range of given steps
        Set<Tile> visited = new HashSet<>();
        Stack<Entry> stack = new Stack<>();
        List<Point2D> positions = new ArrayList<>();

        stack.push(new Entry(tile, steps));
        while (!stack.isEmpty()) {
            Entry entry = stack.pop();
            if (!visited.add(entry.tile)) continue; 

            Point2D p = new Point(entry.tile.getXPos(), entry.tile.getYPos());
            positions.add(p);

            if (entry.remainingSteps > 0) {
                for (Tile neighbor : entry.tile.getNeighbors()) {
                    stack.push(new Entry(neighbor, entry.remainingSteps-1));
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
            if (this.getGuessedCost() > other.getGuessedCost()) { return 1; }
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
    public static List<Tile> getShortestPath(Tile start, Tile goal) {
        AStarEntry finalEntry = aStarSearch(start, goal);
        return extractPath(finalEntry);  
    }

    /**
     * Get the shortest path from start to position next to goal
     * @param start
     * @param goal
     * @return List of tiles representing the shortest path between start and tile next to goal
     */
    public static List<Tile> getPathNextTo(Tile start, Tile goal) {
        AStarEntry finalEntry = aStarSearch(start, goal);
        List<Tile> path = extractPath(finalEntry);
        path.remove(path.size()-1);
        return path;
    }

    private static AStarEntry aStarSearch(Tile start, Tile goal) {
        PriorityQueue<AStarEntry> pq = new PriorityQueue<>();
        Set<Tile> visited = new HashSet<>();
        
        pq.add(new AStarEntry(start, null, null, 0, 0));

        while (!pq.isEmpty()) {
            AStarEntry entry = pq.poll();
            if (visited.contains(entry.getCurrent()))
                continue;
            if (entry.getCurrent() == goal)
                return entry;
            for (Edge edge : entry.getOutgoingEdges()) {
                double costToHere = entry.getCostToHere() + edge.getWeight();
                double guessedCost = entry.getCostToHere() + guessCost(edge.getEnd(), goal);
                AStarEntry newEntry = new AStarEntry(edge.getEnd(), edge, entry, costToHere, guessedCost);
                pq.add(newEntry);
            }
            visited.add(entry.current);
        }

        // If no path was found
        throw new IllegalArgumentException("No path between tiles exists");
    }
    
    private static double guessCost(Tile current, Tile goal) {
        int dx = goal.getXPos() - current.getXPos();
        int dy = goal.getYPos() - current.getYPos();
        return Math.sqrt( dx*dx + dy*dy );
    }
    
    private static List<Tile> extractPath(AStarEntry entry) {
        if (entry == null) throw new IllegalArgumentException("entry must not be null");
        LinkedList<Tile> path = new LinkedList<>();
        while (entry.backPointer != null) {
            path.addFirst(entry.getCurrent());
            entry = entry.getBackPointer();
        }
        return path;
    }
}
