package com.group4.app.model;

class EnemyFactory {
    Enemy createSkeleton() {
        return new Enemy("Skeleton", "Bob", WeaponFactory.createSword(), 10);
    }
    Enemy createZombie() {
        return new Enemy("Zombie", "Steven", WeaponFactory.createClaws(), 15);
    }
}
