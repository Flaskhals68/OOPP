package com.group4.app.testmodel;

import com.group4.app.model.*;
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

    @Test
    public void testTakeTurnMove() {
        Model.resetModel();
        Model m = Model.getInstance();
        World w = new World(100);

        m.addWorld(w);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++ ) {
                w.add(new Tile("stone", new Position(i, j, w.getId())));
            }
        }
        Player p = new Player("player", 3, WeaponFactory.createSword(), new Position(0, 0, w.getId()));
        m.setPlayer(p);
        m.add(p, p.getPos());

        Enemy e = EnemyFactory.createZombie(new Position(10, 10, w.getId()));
        m.addToTurnOrder(e);
        m.add(e, e.getPos());
        e = EnemyFactory.createZombie(new Position(11, 10, w.getId()));
        m.add(e, e.getPos());
        m.addToTurnOrder(e);
        e = EnemyFactory.createZombie(new Position(12, 10, w.getId()));
        m.add(e, e.getPos());
        m.addToTurnOrder(e);
        e = EnemyFactory.createZombie(new Position(13, 10, w.getId()));
        m.add(e, e.getPos());
        m.addToTurnOrder(e);
        m.nextTurn();
        m.nextTurn();
        m.nextTurn();
        m.nextTurn();

        Set<Position> surrounding = m.getSurrounding(m.getPlayerPos(), 1);
        int counter = 0;
        for(Position pos : surrounding) {
            if(!m.getEntities(pos).isEmpty()) {
                counter++;
            }
        }
        // substracts one because getSurrounding counts the tile the player as well
        assertEquals(2, counter - 1);
    }
}
