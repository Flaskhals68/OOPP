package com.group4.app.testmodel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Position;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Tile;
import com.group4.app.model.World;

public class TestPathFinderHelper {
    // Only for debugging purposes
    private static Set<Position> debugAddBasicMap(int startX, int startY) {
        Set<Position> positions = new HashSet<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                positions.add(new Position(startX + x, startY + y));
            }
        }
        return positions;
    }

    @Test
    public void testGetSurrounding() {
        int startX = 3;
        int startY = 3;
        Model model = Model.getInstance();
        model.addBasicMap(10, 0);
        String worldId = model.getCurrentWorldId();
        Set<Position> correctPositions = debugAddBasicMap(startX, startY);
        Tile startingTile = model.getTile(worldId, new Position(startX, startY));
        Set<Position> legalPositions = PathfindingHelper.getSurrounding(startingTile, 1);
        assertTrue(correctPositions.containsAll(legalPositions) && legalPositions.size() == correctPositions.size());
    }

    @Test
    public void testGetShortestPath() {
        Model model = Model.getInstance();
        model.addBasicMap(5, 0);
        String worldId = model.getCurrentWorldId();
        Tile start = model.getTile(worldId, new Position(0, 0));
        Tile goal = model.getTile(worldId, new Position(2, 0));
        List<Position> path = PathfindingHelper.getShortestPath(start, goal);

        LinkedList<Position> correctPath1 = new LinkedList<>();
        correctPath1.addFirst(new Position(2, 0));
        correctPath1.addFirst(new Position(1, 0));

        LinkedList<Position> correctPath2 = new LinkedList<>();
        correctPath2.addFirst(new Position(2, 0));
        correctPath2.addFirst(new Position(1, 1));

        // assertEquals(correctPath1, path);
        assertTrue(path.equals(correctPath1) || path.equals(correctPath2));
    }

    @Test
    public void testGetPathNextTo() {
        Model model = Model.getInstance();
        // World world = new World(5);
        model.addBasicMap(5, 0);
        String worldId = model.getCurrentWorldId();
        Tile start = model.getTile(worldId, new Position(0, 3));
        Tile goal = model.getTile(worldId, new Position(4, 3));
        List<Position> path = PathfindingHelper.getPathNextTo(start, goal);
        LinkedList<Position> correctPath = new LinkedList<>();
        
        correctPath.addFirst(new Position(3, 3));
        correctPath.addFirst(new Position(2, 3));
        correctPath.addFirst(new Position(1, 3));
        
        assertEquals(correctPath, path);
    }

    public static void main(String[] args) {
        int startX = 3;
        int startY = 3;
        Model model = Model.getInstance();
        model.addBasicMap(10, 0);
        String worldId = model.getCurrentWorldId();
        Set<Position> correctPositions = debugAddBasicMap(startX, startY);
        Tile startingTile = model.getTile(worldId, new Position(startX, startY));
        Set<Position> legalPositions = PathfindingHelper.getSurrounding(startingTile, 1);
        assertTrue(legalPositions.containsAll(legalPositions) && legalPositions.size() == correctPositions.size());
    }
}
