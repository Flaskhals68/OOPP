package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.group4.app.model.Model;
import com.group4.app.model.Player;
import com.group4.app.model.Tile;
import com.group4.app.model.Weapon;
import com.group4.app.model.WeaponFactory;
import com.group4.app.model.World;


public class TestPlayer {
    @Test
    public void testConstructors() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        Player player = new Player("player", 10, 3, null, world.getId(), 0, 0);
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());

        Weapon weapon = WeaponFactory.createSword();
        world = new World(2);
        Model.getInstance().addWorld(world);
        t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        player = new Player("player", 10, 3, weapon, world.getId(), 0, 0);
        assertEquals(weapon.getAttack(), player.getDamage());

        world = new World(2);
        Model.getInstance().addWorld(world);
        t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        player = new Player("player", 10, 3, weapon, world.getId(), 0, 0);
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());
        assertEquals(weapon.getAttack(), player.getDamage());
    } 

    @Test
    public void testMove() {
        World world = new World(2);
        Model.getInstance().addWorld(world);
        Tile t1 = new Tile("stone", world.getId(), 0, 0);
        world.addTile(t1);
        Tile t2 = new Tile("stone", world.getId(), 0, 1);
        world.addTile(t2);
        Player p = new Player("player", 10, 3, null, world.getId(), 0, 0);
        p.move(0, 1);
        int[] pos1 = new int[] {t2.getXPos(), t2.getYPos()};
        int[] pos2 = new int[] {p.getXPos(), p.getYPos()};
        assertEquals(pos1[0], pos2[0]);
        assertEquals(pos1[1], pos2[1]);
        assertTrue(t2.getEntities().contains(p));
    }
}
