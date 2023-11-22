package com.group4.app.model;

public class Enemy extends Creature {
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
        // TODO : Implement the rest of enemy death
        Model.getInstance().giveExperience(1);
    }
}
