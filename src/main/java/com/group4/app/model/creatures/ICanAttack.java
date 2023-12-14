package com.group4.app.model.creatures;

import com.group4.app.model.dungeon.IHasPosition;

public interface ICanAttack extends IHasPosition {
    int getDamage();
}
