package model;

public interface Fightable {
    int getAttackDamage();
    int getHitPoints();

    void takeDamage(int damage);
}
