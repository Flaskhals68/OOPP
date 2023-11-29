package com.group4.app.testmodel;

import com.group4.app.model.Enemy;
import com.group4.app.model.EnemyFactory;
import com.group4.app.model.Model;
import com.group4.app.model.Weapon;
import com.group4.app.model.WeaponFactory;
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
        Weapon skeleton_sword = WeaponFactory.createSword();
        assertEquals(skeleton_sword.getAttack() + 1, skeleton.getDamage());
    }
}
