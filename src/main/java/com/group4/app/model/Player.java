package com.group4.app.model;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Player extends FightingEntity {

    private ResourceBar xp;

    public Player(String id, int ap, Weapon weapon, String floorId, int xPos, int yPos) {
        super(id, floorId, xPos, yPos, ap, weapon, new Attributes(50, 50, 50, 50, 50), 1);
        this.xp = new ResourceBar(10);

    }

    public void giveXP(int amount) {
        xp.increaseCurrent(amount);
        if (xp.getCurrent() == xp.getMax()) {
            levelUp();
        }
    }

    private void levelUp() {
        this.setLevel(this.getLevel() + 1);
    }


    @Override
    public void startTurn() {
        Model.getInstance().startPlayerTurn();
        // TODO : Implement player turn
    }

    @Override
    public void endTurn() {
        Model.getInstance().endPlayerTurn();
    }


    @Override
    public void death() {
        // TODO : Implement player death
        System.out.println("Player died");
    }
}
