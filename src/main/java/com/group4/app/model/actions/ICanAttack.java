package com.group4.app.model.actions;

import com.group4.app.model.dungeon.IHasPosition;

public interface ICanAttack extends IHasPosition {
    int getDamage();
}
