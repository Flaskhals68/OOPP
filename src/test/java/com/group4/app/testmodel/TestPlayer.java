package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.group4.app.model.Player;
import com.group4.app.model.Tile;
import com.group4.app.model.Weapon;
import com.group4.app.model.WeaponFactory;
import com.group4.app.model.World;


public class TestPlayer {
    @Test
    public void testConstructors() {
        Player player = new Player("player", 10);
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());

        Weapon weapon = WeaponFactory.createSword();
        player = new Player("player", 10, weapon);
        assertEquals(weapon.getAttack(), player.getDamage());

        World world = new World(1);
        Tile tile = new Tile(world, 0, 0);
        player = new Player("player", 10, tile);
        assertEquals(tile, player.getTile());

        player = new Player("player", 10, weapon, tile);
        assertEquals("player", player.getId());
        assertEquals(10, player.getHitPoints());
        assertEquals(weapon.getAttack(), player.getDamage());
        assertEquals(tile, player.getTile());
    } 

    @Test
    public void testMove() {
        World world = new World(2);
        Tile t1 = new Tile(world, 0, 0);
        Tile t2 = new Tile(world, 0, 1);
        Player p = new Player("player", 10, null, t1);
        p.move(t2);
        assertEquals(t2, p.getTile());
    }
}
