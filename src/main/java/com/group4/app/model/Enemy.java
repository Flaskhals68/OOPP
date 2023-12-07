package com.group4.app.model;

public class Enemy extends Creature {
    private final String name;

    public Enemy(String id, String name, Position pos, Weapon weapon, int maxAp, Attributes attr, int level) {
        super(id, pos, maxAp, weapon, attr, level);
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public void takeTurn() {
        // TODO : Implement enemy AI
    }

    public void endTurn() {
        
    }

    @Override
    public void death() {
        // TODO : Implement the rest of enemy death
        Model.getInstance().remove(this);
        Model.getInstance().giveExperience(1);
    }
}
