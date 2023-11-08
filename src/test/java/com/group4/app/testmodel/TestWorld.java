package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import com.group4.app.model.Tile;
import com.group4.app.model.World;

import java.lang.ArrayIndexOutOfBoundsException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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

    @Test
    public void testSerialization()
    {
        World world = new World(101);
        world.addTile(new Tile(world, 1, 1));

        World world2 = null;

        try 
        {
            // To String
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(world);
            String serializedObject = bos.toString();
            os.close();

            // To Object 
            ByteArrayInputStream bis = new ByteArrayInputStream(serializedObject.getBytes());
            ObjectInputStream oInputStream = new ObjectInputStream(bis);
            world2 = (World) oInputStream.readObject();            

            oInputStream.close();
        } catch(Exception ex) {
            throw new AssertionFailedError();
        }

        assertTrue(world2.getTile(1, 1) != null);
        assertEquals(world, world2);
    }
}
