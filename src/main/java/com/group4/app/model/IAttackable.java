package com.group4.app.model;

public interface IAttackable extends IPositionable{
    void takeHit(int damage);
    int getHitPoints();
}
