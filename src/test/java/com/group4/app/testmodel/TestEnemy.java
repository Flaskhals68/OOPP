package com.group4.app.testmodel;

import com.group4.app.model.*;
import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.creatures.Attributes;
import com.group4.app.model.creatures.Enemy;
import com.group4.app.model.creatures.EnemyFactory;
import com.group4.app.model.creatures.Player;
import com.group4.app.model.dungeon.Tile;
import com.group4.app.model.dungeon.World;
import com.group4.app.model.items.WeaponFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEnemy {
    Model m;
    World world;

    @BeforeEach
    public void setup() {
        world = new World(20);
        m = Model.getInstance();

        m.add(world);
        m.setCurrentWorld(world.getId());
        m.add(new Tile("stone", new Position(0, 0, world.getId())));
        m.add(new Tile("stone", new Position(0, 1, world.getId())));
        Player p = new Player("player", 3, null, new Position(0, 0, world.getId()));
        m.setPlayer(p);
    }
    @Test
    public void test_constructor(){
        Model.getInstance().addBasicMap(5);
        Position pos = new Position(0, 0, Model.getInstance().getCurrentWorldId());
        Enemy enemy = new Enemy("Zombie", "Bob", pos, WeaponFactory.createClaws(), 10, new Attributes(1, 1, 30,1,70, 1), 1, 3);
        assertEquals(enemy.getName(), "Bob");
        assertEquals(2, enemy.getDamage());
        assertEquals(enemy.getHitPoints(), 14);
    }

    @Test
    public void testTakeTurnAttack() {
        int playerHPBefore = m.getPlayer().getHitPoints();

        Enemy e = EnemyFactory.createZombie(new Position(0, 1, world.getId()));
        m.add(e);
        e.takeTurn();
        e.takeTurn();
        e.takeTurn();
        int playerHPAfter = m.getPlayer().getHitPoints();

        assertEquals(playerHPBefore - e.getDamage()*3, playerHPAfter);
    }
    @Test
    public void testTakeTurnMoveAndAttack() {

        m.add(world);
        m.setCurrentWorld(world.getId());
        for (int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                m.add(new Tile("stone", new Position(i, j, world.getId())));
            }
        }

        Player p = new Player("player", 3, null, new Position(0, 0, world.getId()));
        m.setPlayer(p);

        int playerHPBefore = p.getHitPoints();

        Enemy e = EnemyFactory.createZombie(new Position(5, 0, world.getId()));
        m.add(e);
        e.takeTurn();
        e.takeTurn();
        e.takeTurn();
        int playerHPAfter = m.getPlayer().getHitPoints();

        assertEquals(playerHPBefore - e.getDamage(), playerHPAfter);
        assertEquals(1, e.getPos().getX());
        assertEquals(0, e.getPos().getY());
    }
}
