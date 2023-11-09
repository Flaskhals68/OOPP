package com.group4.app.testmodel;

import com.group4.app.model.Enemy;
import com.group4.app.model.WeaponFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEnemy {

    @Test
    public void test_constructor(){
        Enemy enemy = new Enemy("Zombie", "Bob", WeaponFactory.createClaws(),10);
        assertEquals(enemy.getName(), "Bob");
        assertEquals(enemy.getDamage(), 3);
        assertEquals(enemy.getHitPoints(), 10);
    }
}
