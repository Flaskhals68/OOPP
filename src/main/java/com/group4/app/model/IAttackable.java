package com.group4.app.model;

public interface IAttackable {
    void attack(IHittable entity);
    int getDamage();
}
