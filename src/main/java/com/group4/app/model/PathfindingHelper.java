package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.List;
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
}
