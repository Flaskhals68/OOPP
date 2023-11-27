package com.group4.app.model.actions;

import java.util.Set;

import com.group4.app.model.Entity;
import com.group4.app.model.IPositionable;
import com.group4.app.model.Position;

public abstract class Action<A, T> implements IAction<T> {
    private int apCost;
    private String name;
    private A actionTaker;

    public Action(int apCost, String name, A actionTaker) {
        this.actionTaker = actionTaker;
        this.apCost = apCost;
        this.name = name;
    }

    public int getApCost() {
        return apCost;
    }

    public String getName() {
        return name;
    }

    public abstract void perform(T target);

    public abstract Set<T> getTargetable();

    public abstract Set<Position> getTargetablePositions();
}
