package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.jupiter.api.Test;

import com.group4.app.model.Tile;
import com.group4.app.model.World;

import java.lang.ArrayIndexOutOfBoundsException;

/**
 * Unit test for simple App.
 */
public class TestWorld 
{
    @Test
    public void testAddTileInside()
    {
        World world = new World(101);
        Tile tile = new Tile(world, 100, 100);
        world.addTile(tile);

        assertEquals(world.getTile(100, 100), tile);
    }

    @Test
    public void testAddTileOutside()
    {
        World world = new World(101);

        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{world.addTile(new Tile(world, 101, 101));});
    }

    @Test
    public void testConstructor0()
    {
        World world = new World(101);
        world.addTile(new Tile(world, 1, 1));

        assertFalse(world.getTile(1, 1) == null);
    }
}
