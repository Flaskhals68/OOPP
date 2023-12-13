package com.group4.app.model.creatures;

import com.group4.app.model.dungeon.Position;
import com.group4.app.model.items.WeaponFactory;

public class EnemyFactory {
    public static Enemy createSkeleton(Position pos) {
        Attributes attr = new Attributes(30, 30, 40, 40, 40, 40);
        return new Enemy("Skeleton", "Bob", pos, WeaponFactory.createRustySword(), 3, attr, 1, 3);
    }
    public static Enemy createZombie(Position pos) {
        Attributes attr = new Attributes(20, 20, 30, 10, 70, 30);
        return new Enemy("Zombie", "Steven", pos, WeaponFactory.createClaws(), 3, attr, 1, 2);
    }
}
