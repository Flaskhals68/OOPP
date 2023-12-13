package com.group4.app.model.creatures;

import com.group4.app.model.dungeon.IHasPosition;

public interface IAttackable extends IHasPosition{
    void takeHit(int damage);
    int getHitPoints();
    int getMaxHitPoints();
}
