package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Tile;
import com.group4.app.model.World;

public class TestTile {
    @Test
    public void testCalculateNeighbors()
    {
        World world = new World(9);
        world.addTile(new Tile(world, 0, 0));
        world.addTile(new Tile(world, 0, 1));
        world.addTile(new Tile(world, 0, 2));

        world.addTile(new Tile(world, 1, 0));
        world.addTile(new Tile(world, 1, 1));
        world.addTile(new Tile(world, 1, 2));

        world.addTile(new Tile(world, 2, 0));
        world.addTile(new Tile(world, 2, 1));
        world.addTile(new Tile(world, 2, 2));

        Set<Tile> tile00Neighbors = world.getTile(0, 0).getNeighbors();
        Tile tile01 = world.getTile(0, 1);
        Tile tile11 = world.getTile(1, 1);
        Tile tile10 = world.getTile(1, 0);

        Tile testTile = new Tile(new World(1), 0, 0);
        testTile.addNeighbors(new Tile[] {tile01, tile11, tile10});

        assertEquals(testTile.getNeighbors(), tile00Neighbors);
    }
}