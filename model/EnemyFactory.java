package model;

class EnemyFactory {
    Fightable createSkeleton() {
        return new Enemy("Skeleton", new Sword("Steel Sword", 5), 10);
    }
    Fightable createZombie() {
        return new Enemy("Zombie", new Claws("Blunt Claws", 3), 15);
    }
}
