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

    public static Set<Point2D> getSurrounding(Tile tile, int steps) {
        // Perform depth-first search to find all tiles in range of given steps
        Set<Tile> visited = new HashSet<>();
        Stack<Entry> stack = new Stack<>();
        Set<Point2D> positions = new HashSet<>();

        stack.push(new Entry(tile, steps));
        while (!stack.isEmpty()) {
            Entry entry = stack.pop();
            if (!visited.add(entry.tile)) continue; 

            Point2D p = new Point(entry.tile.getXPos(), entry.tile.getYPos());
            // if (p.getX() == tile.getXPos() && p.getY() == tile.getYPos()) continue;
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

    private static class AStarEntry {
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

        private Set<Edge> initOutgoingEdges(Tile current) {
            Set<Edge> outgoingEdges = new HashSet<>();
            for (Tile neigbor : current.getNeighbors()) {
                Edge e = new Edge(current, neigbor);
                outgoingEdges.add(e);
            }
            return outgoingEdges;
        }

        public Tile getCurrent() { return current; }
        public Edge getLastEdge() { return lastEdge; }
        public AStarEntry getBackPointer() { return backPointer; }
        public double getCostToHere() { return costToHere; }
        public double getGuessedCost() { return guessedCost; }
        public Set<Edge> getOutgoingEdges() { return outgoingEdges; }
        public double getWeight() { return lastEdge.getWeight(); }
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
                AStarEntry newEntry = new AStarEntry(entry.getCurrent(), edge, entry, costToHere, guessedCost);
                pq.add(newEntry);
            }
            visited.add(entry.current);
        }

        // If no path was found
        return null;
    }
    
    private static double guessCost(Tile current, Tile goal) {
        int dx = goal.getXPos() - current.getXPos();
        int dy = goal.getYPos() - current.getYPos();
        return Math.sqrt( dx*dx + dy*dy );
    }
    
    private static List<Tile> extractPath(AStarEntry entry) {
        LinkedList<Tile> path = new LinkedList<>();
        while (entry.backPointer != null) {
            path.addFirst(entry.getCurrent());
            entry = entry.getBackPointer();
        }
        return path;
    }

    // For debugging
    public static void main(String[] args) {
        int size = 10;
        Model model = Model.getInstance();
        World world = new World(size);
        
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                world.addTile(new Tile(world, x, y));
            }
        }
        
        Tile start = new Tile(world, 0, 5);
        Tile end = new Tile(world, 9, 5);
        List<Tile> path = PathfindingHelper.getShortestPath(start, end);
    }
}
