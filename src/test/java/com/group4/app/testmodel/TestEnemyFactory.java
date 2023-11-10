package com.group4.app.testmodel;

import com.group4.app.model.Enemy;
import com.group4.app.model.EnemyFactory;
import com.group4.app.model.Weapon;
import com.group4.app.model.WeaponFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEnemyFactory {
    @Test
    public void testCreateZombie() {
        Enemy zombie = EnemyFactory.createZombie();
        Weapon zombie_claws = WeaponFactory.createClaws();
        assertEquals(zombie.getDamage(), zombie_claws.getAttack());
        assertEquals(zombie.getId(), "Zombie");
    }

    @Test
    public void testCreateSkeleton() {
        Enemy skeleton = EnemyFactory.createSkeleton();
        Weapon skeleton_sword = WeaponFactory.createSword();
        assertEquals(skeleton.getDamage(), skeleton_sword.getAttack());
    }
}
