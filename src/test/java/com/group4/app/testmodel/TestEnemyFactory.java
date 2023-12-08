package com.group4.app.testmodel;

import com.group4.app.model.Model;
import com.group4.app.model.creatures.Enemy;
import com.group4.app.model.creatures.EnemyFactory;
import com.group4.app.model.items.Weapon;
import com.group4.app.model.items.WeaponFactory;
import com.group4.app.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEnemyFactory {
    @Test
    public void testCreateZombie() {
        Model.getInstance().addBasicMap(5);
        Enemy zombie = EnemyFactory.createZombie(new Position(0, 0,  Model.getInstance().getCurrentWorldId()));
        Weapon zombie_claws = WeaponFactory.createClaws();
        assertEquals(zombie_claws.getAttack() - 2, zombie.getDamage());
        assertEquals("Zombie", zombie.getId());
    }

    @Test
    public void testCreateSkeleton() {
        Enemy skeleton = EnemyFactory.createSkeleton(new Position(0, 0,  Model.getInstance().getCurrentWorldId()));
        Weapon skeleton_sword = WeaponFactory.createRustySword();
        assertEquals(skeleton_sword.getAttack() - 1, skeleton.getDamage());
    }
}
