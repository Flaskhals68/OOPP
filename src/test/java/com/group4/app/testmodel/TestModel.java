package com.group4.app.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.group4.app.model.*;
import org.junit.jupiter.api.Test;

public class TestModel {
    @Test
    public void testAttackActionShouldHit() {
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

        model.performAttackAction(p, e);

        assertEquals(e.getHitPoints() - p.getDamage(), e.getHitPoints());
    }
}
