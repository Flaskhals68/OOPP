package com.group4.app.model.dungeon;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.group4.app.model.dungeon.DungeonGraphGenerator.Room;

public class DungeonGraphPathfinder {
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
}
