package com.group4.app.testmodel;

import com.group4.app.model.Claws;
import com.group4.app.model.Enemy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEnemy {

    @Test
    public void test_constructor(){
        Enemy enemy = new Enemy("Zombie", "Bob", new Claws("Basic claw", 3),10);
        assertEquals(enemy.getName(), "Bob");
        assertEquals(enemy.getDamage(), 3);
        assertEquals(enemy.getHitPoints(), 10);
    }
}
