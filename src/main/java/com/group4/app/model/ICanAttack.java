package com.group4.app.model;

public interface ICanAttack {
    void attack(IAttackable entity);
    int getDamage();
}
