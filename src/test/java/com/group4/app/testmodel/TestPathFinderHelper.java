package com.group4.app.testmodel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import com.group4.app.model.creatures.EnemyFactory;
import com.group4.app.model.dungeon.PathfindingHelper;
import com.group4.app.model.dungeon.Position;
import com.group4.app.model.dungeon.Tile;
import com.group4.app.model.dungeon.World;
import com.group4.app.model.Model;

public class TestPathFinderHelper {
    // Only for debugging purposes
    private static Set<Position> debugAddBasicMap(int startX, int startY, String floor) {
        Set<Position> positions = new HashSet<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                positions.add(new Position(startX + x, startY + y, floor));
            }
        }
        return positions;
    }

    @Test
    public void testGetSurrounding() {
        int startX = 2;
        int startY = 0;
        Model model = Model.getInstance();
        World world = new World(5);
        model.add(world);
        String worldId = world.getId();
        initFlatWorld(5, world);;
        Set<Position> correctPositions = new HashSet<>();
        correctPositions.add(new Position(1, 0, worldId));
        correctPositions.add(new Position(3, 0, worldId));
        correctPositions.add(new Position(2, 0, worldId));
        Position startingPos = new Position(startX, startY, worldId);
        Set<Position> legalPositions = PathfindingHelper.getSurrounding(startingPos, 1, model);
        assertTrue(correctPositions.containsAll(legalPositions) && legalPositions.size() == correctPositions.size());
    }

    @Test
    public void testGetShortestPath() {
        Model model = Model.getInstance();
        // model.addBasicMap(5, 0);
        World world = new World(5);
        String worldId = world.getId();
        model.add(world);
        model.setCurrentWorld(worldId);
        initFlatWorld(5, world);
        Position start = new Position(0, 0, worldId);
        Position goal = new Position(2, 0, worldId);
        List<Position> path = PathfindingHelper.getShortestPath(start, goal, world);

        LinkedList<Position> correctPath = new LinkedList<>();
        correctPath.addFirst(new Position(2, 0, worldId));
        correctPath.addFirst(new Position(1, 0, worldId));
        assertEquals(correctPath, path);
    }

    @Test
    public void testGetPathNextTo() {
        Model model = Model.getInstance();
        World world = new World(5);
        model.add(world);
        String worldId = world.getId();
        model.setCurrentWorld(worldId);
        initFlatWorld(5, world);
        Position start = new Position(0, 0, worldId);
        Position goal = new Position(4, 0, worldId);
        List<Position> path = PathfindingHelper.getPathNextTo(start, goal, model);
        LinkedList<Position> correctPath = new LinkedList<>();
        
        correctPath.addFirst(new Position(3, 0, worldId));
        correctPath.addFirst(new Position(2, 0, worldId));
        correctPath.addFirst(new Position(1, 0, worldId));
        
        assertEquals(correctPath, path);
    }

    @Test
    public void pathToClosest() {
        Model model = Model.getInstance();
        World world = new World(10);
        model.add(world);
        initFlatWorld(5, world);
        model.setCurrentWorld(world.getId());
        String worldId = model.getCurrentWorldId();
        Position start = new Position(0, 0, worldId);
        Position goal = new Position(4, 0, worldId);
        List<Position> path = PathfindingHelper.pathToClosest(start, goal, model);
        List<Position> correctPositions = new ArrayList<>();
        correctPositions.add(new Position(1, 0, worldId));
        correctPositions.add(new Position(2, 0, worldId));
        correctPositions.add(new Position(3, 0, worldId));
        correctPositions.add(new Position(4, 0, worldId));
        assertEquals(correctPositions, path);
    }

    @Test
    public void testBlockedPath() {
        Model model = Model.getInstance();
        World world = new World(5);
        model.add(world);
        initFlatWorld(5, world);
        model.setCurrentWorld(world.getId());
        String worldId = model.getCurrentWorldId();
        Position start = new Position(0, 0, worldId);
        Position goal = new Position(4, 0, worldId);
        Tile obstacle = model.getTile(new Position(2, 0, worldId));
        obstacle.add(EnemyFactory.createZombie(obstacle.getPos(), model));
        List<Position> path = PathfindingHelper.pathToClosest(start, goal, model);
        Position finalPosition = path.get(path.size() - 1);
        assertEquals(new Position(1, 0, worldId), finalPosition);
    }

    private static void initFlatWorld(int size, World world) {
        for (int i = 0; i < size; i++) {
            world.add(new Tile(world.getId(), new Position(i, 0, world.getId()), Model.getInstance()));
        }
    }
}
