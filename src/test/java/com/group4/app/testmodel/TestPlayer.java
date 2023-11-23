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

import java.util.Map;


public class TestPlayer {
    @Test
    public void testConstructors() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), new Position(0, 0));
        world.addTile(t1);
        Player player = new Player("player", 3, null, world.getId(), new Position(0, 0));
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());

        Weapon weapon = WeaponFactory.createSword();
        world = new World(2);
        Model.getInstance().addWorld(world);
        t1 = new Tile("stone", world.getId(), new Position(0, 0));
        world.addTile(t1);
        player = new Player("player", 3, weapon, world.getId(), new Position(0, 0));
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());
        assertEquals(weapon.getAttack() + 5, player.getDamage());
    } 

    @Test
    public void testMove() {
        Model.getInstance().addBasicMap(2);
        String worldId = Model.getInstance().getCurrentWorldId();
        Tile t1 = new Tile("stone", worldId, new Position(0, 0));
        // world.addTile(t1);
        Tile t2 = Model.getInstance().getTile(worldId, new Position(0, 1));
        Player p = new Player("player", 3, null, worldId, new Position(0, 0));
        p.move(new Position(0, 1));
        int[] pos1 = new int[] {t2.getXPos(), t2.getYPos()};
        int[] pos2 = new int[] {p.getXPos(), p.getYPos()};
        assertEquals(pos1[0], pos2[0]);
        assertEquals(pos1[1], pos2[1]);
        assertTrue(t2.getEntities().contains(p));
    }

    @Test
    public void testIllegalMove() {
        Model.getInstance().addBasicMap(10);
        String worldId = Model.getInstance().getCurrentWorldId();
        Player p = new Player("player", 5, null, worldId, new Position(0, 0));

        assertThrows(IllegalArgumentException.class, () -> {
            p.move(new Position(9, 9));
        });
    }

    @Test
    public void testFetchItemFromInventory() {

        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), new Position(0, 0));
        world.addTile(t1);
        Player p = new Player("player", 3, null, world.getId(), new Position(0, 0));

        Weapon testItem = WeaponFactory.createSword();

        p.addItemToInventory(testItem);

        assertEquals(p.fetchItemFromInventory(testItem.getName()).getName(), testItem.getName());
    }

    @Test
    public void testGetInventoryItems() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), new Position(0, 0));
        world.addTile(t1);
        Player p = new Player("player", 3, null, world.getId(), new Position(0, 0));

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
        Tile t1 = new Tile("stone", world.getId(), new Position(0, 0));
        world.addTile(t1);
        Player p = new Player("player", 3, WeaponFactory.createSword(), world.getId(), new Position(0, 0));

        Weapon basic_claws = WeaponFactory.createClaws();

        p.setWeapon(basic_claws);

        assertEquals(basic_claws.getAttack() + 5, p.getDamage());
        assertEquals(p.fetchItemFromInventory("Basic Sword").getName(), "Basic Sword");
    }
}
