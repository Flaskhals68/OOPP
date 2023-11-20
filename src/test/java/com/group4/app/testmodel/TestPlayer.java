package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.group4.app.model.Position;
import com.group4.app.model.Model;
import com.group4.app.model.Player;
import com.group4.app.model.Tile;
import com.group4.app.model.Weapon;
import com.group4.app.model.WeaponFactory;
import com.group4.app.model.World;

import java.util.HashMap;
import java.util.Map;


public class TestPlayer {
    @Test
    public void testConstructors() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        Player player = new Player("player", 3, null, world.getId(), 0, 0);
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());

        Weapon weapon = WeaponFactory.createSword();
        world = new World(2);
        Model.getInstance().addWorld(world);
        t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        player = new Player("player", 3, weapon, world.getId(), 0, 0);
        assertEquals(weapon.getAttack() + 5, player.getDamage());

        world = new World(2);
        Model.getInstance().addWorld(world);
        t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        player = new Player("player", 3, weapon, world.getId(), 0, 0);
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());
        assertEquals(weapon.getAttack() + 5, player.getDamage());
    } 

    @Test
    public void testMove() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        Tile t2 = new Tile("stone", world.getId(), 0, 1);
        world.addTile(t2);
        Player p = new Player("player", 3, null, world.getId(), 0, 0);
        p.move(new Position(0, 1));
        int[] pos1 = new int[] {t2.getXPos(), t2.getYPos()};
        int[] pos2 = new int[] {p.getXPos(), p.getYPos()};
        assertEquals(pos1[0], pos2[0]);
        assertEquals(pos1[1], pos2[1]);
        assertTrue(t2.getEntities().contains(p));
    }

    @Test
    public void testIllegalMove() {
        Model model = Model.getInstance();
        World world = new World(10);
        model.addWorld(world);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                world.addTile(new Tile("stone", world.getId(), i, j));
            }
        }
        Player p = new Player("player", 5, null, world.getId(), 0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            p.move(new Position(9, 9));
        });
    }

    @Test
    public void testFetchItemFromInventory() {

        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        Player p = new Player("player", 3, null, world.getId(), 0, 0);

        Weapon testItem = WeaponFactory.createSword();

        p.addItemToInventory(testItem);

        assertEquals(p.fetchItemFromInventory(testItem.getName()).getName(), testItem.getName());
    }

    @Test
    public void testGetInventoryItems() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        Player p = new Player("player", 3, null, world.getId(), 0, 0);

        for(int i = 0; i<4; i++) {
            p.addItemToInventory(WeaponFactory.createSword());
            if (i >= 1) {
                p.addItemToInventory(WeaponFactory.createClaws());
            }
        }

        Map<String, Integer> testMap = p.getInventoryItems();

        assertEquals(4, testMap.get("Basic Sword"));
        assertEquals(3, testMap.get("Basic Claws"));
    }

    @Test
    public void testSetWeapon() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        Player p = new Player("player", 3, WeaponFactory.createSword(), world.getId(), 0, 0);

        Weapon basic_claws = WeaponFactory.createClaws();

        p.setWeapon(basic_claws);

        assertEquals(basic_claws.getAttack() + 5, p.getDamage());
        assertEquals(p.fetchItemFromInventory("Basic Sword").getName(), "Basic Sword");
    }
}
