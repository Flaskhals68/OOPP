package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.Tile;
import com.group4.app.model.World;

public class TestTile {
    @Test
    public void testCalculateNeighbors()
    {
        World world = new World(9);
        Model.getInstance().add(world);
        world.add(new Tile("stone", new Position(0, 0, world.getId())));
        world.add(new Tile("stone", new Position(0, 1, world.getId())));
        world.add(new Tile("stone", new Position(0, 2, world.getId())));

        world.add(new Tile("stone", new Position(1, 0, world.getId())));
        world.add(new Tile("stone", new Position(1, 1, world.getId())));
        world.add(new Tile("stone", new Position(1, 2, world.getId())));

        world.add(new Tile("stone", new Position(2, 0, world.getId())));
        world.add(new Tile("stone", new Position(2, 1, world.getId())));
        world.add(new Tile("stone", new Position(2, 2, world.getId())));

        Set<Tile> tile00Neighbors = world.getTile(new Position(0, 0, world.getId())).getNeighbors();
        Tile tile01 = world.getTile(new Position(0, 1, world.getId()));
        Tile tile11 = world.getTile(new Position(1, 1, world.getId()));
        Tile tile10 = world.getTile(new Position(1, 0, world.getId()));

        World world2 = new World(1);
        Model.getInstance().add(world2);
        Tile testTile = new Tile("stone", new Position(0, 0, world2.getId()));
        testTile.addNeighbors(new Tile[] {tile01, tile11, tile10});

        assertEquals(testTile.getNeighbors(), tile00Neighbors);
    }
}