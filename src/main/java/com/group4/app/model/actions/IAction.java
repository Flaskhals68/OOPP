package com.group4.app.model.actions;

import java.util.Set;

import com.group4.app.model.Position;
import com.group4.app.model.creatures.IPositionable;

public interface IAction<T> {
    void perform(T target);
    Set<T> getTargetable();
    Set<Position> getTargetablePositions();
    String getName();
    int getApCost();
}
