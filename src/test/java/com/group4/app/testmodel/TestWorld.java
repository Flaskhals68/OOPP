package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Model;
import com.group4.app.model.dungeon.Position;
import com.group4.app.model.dungeon.Tile;
import com.group4.app.model.dungeon.World;

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
        Model.getInstance().add(world);
        Tile tile = new Tile("stone", new Position(100, 100, world.getId()));
        world.add(tile);

        assertEquals(world.getTile(new Position(100, 100, world.getId())), tile);
    }

    @Test
    public void testAddTileOutside()
    {
        World world = new World(101);
        Model.getInstance().add(world);
        Tile tile = new Tile("stone", new Position(101, 101, world.getId()));

        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{world.add(tile);});
    }

    @Test
    public void testConstructor0()
    {
        World world = new World(101);
        Model.getInstance().add(world);
        world.add(new Tile("stone", new Position(1, 1, world.getId())));

        assertFalse(world.getTile(new Position(1, 1, world.getId())) == null);
    }
}
