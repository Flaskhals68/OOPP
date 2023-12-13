package com.group4.app.model.dungeon;

import com.group4.app.model.ITurnTaker;
import com.group4.app.model.Position;
import com.group4.app.model.creatures.EnemyFactory;
import com.group4.app.model.creatures.ICreatureManager;
import com.group4.app.model.creatures.IEnemyManager;

import java.util.ArrayList;
import java.util.List;

public class DungeonEntitySpawner {
    public static List<ITurnTaker> spawnEnemies(World world, double density, IEnemyManager em) {
        List<ITurnTaker> enemies = new ArrayList<>();
        for (int x = 0; x < world.getWorldWidth(); x++) {
            for (int y = 0; y < world.getWorldHeight(); y++) {
                double rand = Math.random();
                if (rand < density) {
                    spawnEnemy(x, y, world, em, enemies);
                }
            }
        }
        return enemies;
    }

    private static void spawnEnemy(int x, int y, World world, IEnemyManager em, List<ITurnTaker> enemies) {
        Position pos = new Position(x, y, world.getId());
        if (!(world.getTile(pos) == null) && world.getEntities(pos).isEmpty()) {
            ITurnTaker e = EnemyFactory.createZombie(pos, em);
            enemies.add(e);
            world.add(e);
        }
    }
}
