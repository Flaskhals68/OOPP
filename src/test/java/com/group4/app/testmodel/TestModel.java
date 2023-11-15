package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.group4.app.model.*;
import org.junit.jupiter.api.Test;

public class TestModel {
    @Test
    public void testPlayerAttackActionShouldHit() {
        World world = new World(4);
        Model.getInstance().addWorld(world);
        Model model = Model.getInstance();
        Tile pTile = new Tile("stone", world.getId(), 2, 2);
        world.addTile(pTile);
        Tile eTile = new Tile("stone", world.getId(), 3, 1);
        world.addTile(eTile);

        Player p = new Player("player", 10, WeaponFactory.createSword(), world.getId(), 2,2);
        Enemy e = EnemyFactory.createZombie();
        e.setPosition(world.getId(), 3, 1);

        int hpBeforeAttack = e.getHitPoints();
        model.performAttackAction(p, e);

        assertEquals(hpBeforeAttack - p.getDamage(), e.getHitPoints());
    }

    @Test
    public void testPlayerAttackActionOutOfRange() {
        World world = new World(4);
        Model.getInstance().addWorld(world);
        Model model = Model.getInstance();
        Tile pTile = new Tile("stone", world.getId(), 2, 2);
        world.addTile(pTile);
        Tile eTile = new Tile("stone", world.getId(), 0, 0);
        world.addTile(eTile);

        Player p = new Player("player", 10, WeaponFactory.createSword(), world.getId(), 2,2);
        Enemy e = EnemyFactory.createZombie();
        e.setPosition(world.getId(), 0, 0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            model.performAttackAction(p, e);
        }, "IllegalArgumentException was expected, since attacker and victim are out of range");

        assertTrue(ex.getMessage().contains("Attacker is out of range"));
    }

    @Test
    public void testPlayerAttackActionDifferentFloors() {
        World world1 = new World(4);
        World world2 = new World(4);
        Model.getInstance().addWorld(world1);
        Model.getInstance().addWorld(world2);
        Model model = Model.getInstance();
        Tile pTile = new Tile("stone", world1.getId(), 2, 2);
        world1.addTile(pTile);
        Tile eTile = new Tile("stone", world2.getId(), 3, 1);
        world2.addTile(eTile);

        Player p = new Player("player", 10, WeaponFactory.createSword(), world1.getId(), 2,2);
        Enemy e = EnemyFactory.createZombie();
        e.setPosition(world2.getId(), 3, 1);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            model.performAttackAction(p, e);
        }, "IllegalArgumentException was expected, since attacker and victim on different floors");

        assertTrue(ex.getMessage().contains("Attacker and victim are on different floors/worlds"));
    }
}
