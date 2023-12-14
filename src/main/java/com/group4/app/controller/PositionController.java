package com.group4.app.controller;

import java.util.Iterator;

import com.group4.app.model.Model;
import com.group4.app.model.creatures.IAttackable;
import com.group4.app.model.dungeon.IPositionable;
import com.group4.app.model.dungeon.Position;

public class PositionController {
    public PositionController() {  }
    public Position getCreaturePosition(Position pos) { 
        // Set<Entity> e = Model.getInstance().getEntities(pos);
        return null;
    }

    public boolean hasAttackable(Position pos) {
        Iterator<IPositionable> e = Model.getInstance().getEntities(pos).iterator();
        if (!e.hasNext()) return false;
        return e.next() instanceof IAttackable;
    }
}
