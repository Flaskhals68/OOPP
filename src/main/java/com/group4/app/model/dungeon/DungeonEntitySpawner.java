package com.group4.app.model.dungeon;

import com.group4.app.model.creatures.EnemyFactory;

public class DungeonEntitySpawner {
    public static void spawnEnemies(World world, double density) {
        for (int x = 0; x < world.getWorldWidth(); x++) {
            for (int y = 0; y < world.getWorldHeight(); y++) {
                double rand = Math.random();
                if (rand < density) {
                    spawnEnemy(x, y, world);
                }
            }
        }
    }

    private static void spawnEnemy(int x, int y, World world) {
        Position pos = new Position(x, y, world.getId());
        if (!(world.getTile(pos) == null) && world.getEntities(pos).isEmpty()) {
            world.add(EnemyFactory.createZombie(pos));
        }
    }
}
