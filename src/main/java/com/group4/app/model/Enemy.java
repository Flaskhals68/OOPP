package com.group4.app.model;

import java.util.Set;

public class Enemy extends FightingEntity {
    private final String name;

    public Enemy(String id, String name, Weapon weapon, int maxAp, Attributes attr, int level) {
        super(id, maxAp, weapon, attr, level);
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public void startTurn() {
        // TODO : Implement enemy AI
    }

    @Override
    public void endTurn() {
        Model.getInstance().endTurn();
    }

    @Override
    public void death() {
        // TODO : Implement enemy death
        Model.getInstance().getPlayer().giveXP(1);
    }
}
