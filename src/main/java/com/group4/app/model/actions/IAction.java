package com.group4.app.model.actions;

import java.util.Set;

import com.group4.app.model.dungeon.IPositionable;
import com.group4.app.model.dungeon.Position;

public interface IAction<T> {
    void perform(T target);
    Set<T> getTargetable();
    Set<Position> getTargetablePositions();
    String getName();
    int getApCost();
}
