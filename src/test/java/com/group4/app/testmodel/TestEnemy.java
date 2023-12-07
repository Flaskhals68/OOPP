package com.group4.app.testmodel;

import com.group4.app.model.*;
import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.creatures.Attributes;
import com.group4.app.model.creatures.Enemy;
import com.group4.app.model.items.WeaponFactory;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEnemy {

    @Test
    public void test_constructor(){
        Model.getInstance().addBasicMap(5);
        Position pos = new Position(0, 0, Model.getInstance().getCurrentWorldId());
        Enemy enemy = new Enemy("Zombie", "Bob", pos, WeaponFactory.createClaws(), 10, new Attributes(1, 1, 30,1,70, 1), 1);
        assertEquals(enemy.getName(), "Bob");
        assertEquals(2, enemy.getDamage());
        assertEquals(enemy.getHitPoints(), 14);
    }
}
