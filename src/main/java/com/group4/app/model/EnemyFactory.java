package com.group4.app.model;

class EnemyFactory {
    Enemy createSkeleton() {
        return new Enemy("Skeleton", "Bob", new Sword("Steel Sword", 5), 10);
    }
    Enemy createZombie() {
        return new Enemy("Zombie", "Steven", new Claws("Blunt Claws", 3), 15);
    }
}
