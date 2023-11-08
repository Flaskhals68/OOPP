package com.group4.app.model;

public interface ICanAttack {
    void attack(IHittable entity);
    int getDamage();
}
