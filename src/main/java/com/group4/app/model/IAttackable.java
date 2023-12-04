package com.group4.app.model;

public interface IAttackable extends IHasPosition{
    void takeHit(int damage);
    int getHitPoints();
    int getMaxHitPoints();
}
