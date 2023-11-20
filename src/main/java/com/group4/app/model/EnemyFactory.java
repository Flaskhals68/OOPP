package com.group4.app.model;

public class EnemyFactory {
    public static Enemy createSkeleton() {
        Attributes attr = new Attributes(30, 30, 30, 40, 40, 40);
        return new Enemy("Skeleton", "Bob", WeaponFactory.createSword(), 3, attr, 1);
    }
    public static Enemy createZombie() {
        Attributes attr = new Attributes(20, 20, 10, 10, 70, 30);
        return new Enemy("Zombie", "Steven", WeaponFactory.createClaws(), 3, attr, 1);
    }
}
