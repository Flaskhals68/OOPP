package com.group4.app.model;

public interface ICanAttack extends IPositionable {
    void attack(IAttackable entity);
    int getDamage();

}
