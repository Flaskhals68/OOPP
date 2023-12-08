package com.group4.app.controller;

import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.creatures.IAttackable;
import com.group4.app.model.creatures.IPositionable;

public class CreatureStatController {
    public int getCreatureHealth(Position creaturePosition) {
        IPositionable creature = Model.getInstance().getEntities(creaturePosition).iterator().next();
        if (creature instanceof IAttackable) {
            return ((IAttackable) creature).getHitPoints();
        }
        else {
            throw new IllegalArgumentException("Creature at position " + creaturePosition + " is not attackable");
        }
    }

    public int getCreatureMaxHealth(Position creaturePosition) {
        IPositionable creature = Model.getInstance().getEntities(creaturePosition).iterator().next();
        if (creature instanceof IAttackable) {
            return ((IAttackable) creature).getMaxHitPoints();
        }
        else {
            throw new IllegalArgumentException("Creature at position " + creaturePosition + " is not attackable");
        }
    }
}
