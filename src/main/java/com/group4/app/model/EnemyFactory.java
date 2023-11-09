package com.group4.app.model;

public class EnemyFactory {
    public static Enemy createSkeleton() {
        return new Enemy("Skeleton", "Bob", WeaponFactory.createSword(), 10);
    }
    public static Enemy createZombie() {
        return new Enemy("Zombie", "Steven", WeaponFactory.createClaws(), 15);
    }
}
