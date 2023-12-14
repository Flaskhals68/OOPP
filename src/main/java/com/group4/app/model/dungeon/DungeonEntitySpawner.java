package com.group4.app.model.dungeon;

import com.group4.app.model.creatures.EnemyFactory;

public class DungeonEntitySpawner {
    public static List<Creature> spawnEnemies(World world, double density, IEnemyManager em) {
        List<Creature> enemies = new ArrayList<>();
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

    private static void spawnEnemy(int x, int y, World world, IEnemyManager em, List<Creature> enemies) {
        Position pos = new Position(x, y, world.getId());
        if (!(world.getTile(pos) == null) && world.getEntities(pos).isEmpty()) {
            Creature e = EnemyFactory.createZombie(pos, em);
            enemies.add(e);
            world.add(e);
        }
    }
}
