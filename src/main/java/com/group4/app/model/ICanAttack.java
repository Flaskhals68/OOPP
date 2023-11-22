package com.group4.app.model;

public interface ICanAttack extends IHasPosition {
    void attack(IAttackable entity);
    int getDamage();
}
