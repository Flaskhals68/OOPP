package com.group4.app.model;

public interface Fightable {
    int getAttackDamage();
    int getHitPoints();

    void takeDamage(int damage);
}
