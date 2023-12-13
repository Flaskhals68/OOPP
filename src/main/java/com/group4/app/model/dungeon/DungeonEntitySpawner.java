package com.group4.app.model.dungeon;

import com.group4.app.model.Position;
import com.group4.app.model.creatures.EnemyFactory;
import com.group4.app.model.creatures.IEntityManager;

public class DungeonEntitySpawner {
    public static void spawnEnemies(World world, double density, IEntityManager em) {
        for (int x = 0; x < world.getWorldWidth(); x++) {
            for (int y = 0; y < world.getWorldHeight(); y++) {
                double rand = Math.random();
                if (rand < density) {
                    spawnEnemy(x, y, world, em);
                }
            }
        }
    }

    private static void spawnEnemy(int x, int y, World world, IEntityManager em) {
        Position pos = new Position(x, y, world.getId());
        if (!(world.getTile(pos) == null) && world.getEntities(pos).isEmpty()) {
            world.add(EnemyFactory.createZombie(pos, em));
        }
    }
}
