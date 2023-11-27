package com.group4.app.testmodel;

import com.group4.app.model.Attributes;
import com.group4.app.model.Enemy;
import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.WeaponFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEnemy {

    @Test
    public void test_constructor(){
        Model.getInstance().addBasicMap(5);
        Position pos = new Position(0, 0, Model.getInstance().getCurrentWorldId());
        Enemy enemy = new Enemy("Zombie", "Bob", pos, WeaponFactory.createClaws(), 10, new Attributes(1, 1, 10,1,70, 1), 1);
        assertEquals(enemy.getName(), "Bob");
        assertEquals(enemy.getDamage(), 5);
        assertEquals(enemy.getHitPoints(), 14);
    }
}
