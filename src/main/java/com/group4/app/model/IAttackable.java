package com.group4.app.model;

public interface IAttackable {
    void takeHit(int damage);
    int getHitPoints();
    int getXPos();
    int getYPos();
    String getFloor();
}
