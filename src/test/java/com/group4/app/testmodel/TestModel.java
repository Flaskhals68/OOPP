package com.group4.app.testmodel;

import com.group4.app.model.*;
import com.group4.app.model.creatures.Enemy;
import com.group4.app.model.creatures.EnemyFactory;
import com.group4.app.model.dungeon.Position;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestModel {
    @Test
    public void testNextToPlayerTrue() {
        Model m = Model.getInstance();
        m.addBasicMap(5, 0);
        Position pos = new Position(0, 1, m.getCurrentWorldId());
        Enemy e = EnemyFactory.createZombie(pos, m);
        m.add(e);
        assertTrue(m.nextToPlayer(e.getPos()));
    }

    @Test
    public void testNextToPlayerFalse() {
        Model m = Model.getInstance();
        m.addBasicMap(5, 0);
        Position pos = new Position(0, 2, m.getCurrentWorldId());
        Enemy e = EnemyFactory.createZombie(pos, m);
        m.add(e);
        assertFalse(m.nextToPlayer(e.getPos()));
    }
}
