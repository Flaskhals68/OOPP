package com.group4.app.model;

import java.util.Random;

public class Player extends Creature {

    private ResourceBar xp;

    public Player(String id, int ap, Weapon weapon, Position position) {
        super(id, position, ap, weapon, new Attributes(50, 50, 50, 50, 50, 50), 1);
        this.xp = new ResourceBar(10);
        this.xp.setCurrent(0);
        for (int i = 0; i < 3; i++) {
            this.addItemToInventory(PotionFactory.createHealthPotion());
        }
    }

    public void giveXP(int amount) {
        xp.increaseCurrent(amount);
        if (xp.getCurrent() == xp.getMax()) {
            levelUp();
            xp.setCurrent(0);
        }
    }

    /**
     * Levels up the player, increases level by 1 and increases one attributes by 10
     */
    private void levelUp() {
        setLevel(getLevel() + 1);
        getAttributes().levelUpRandom();
    }

    @Override
    public void takeTurn() {
        Model.getInstance().startPlayerTurn();
        while (this.getAp() > 0) {
            ActionInput<?> input = Model.getInstance().getActionInput();
            this.performAction(input);
            this.useAp(1);
            Model.getInstance().updateObservers();
        }
        endTurn();
    }

    public void endTurn() {
        Model.getInstance().endPlayerTurn();
    }


    @Override
    public void death() {
        // TODO : Implement player death
        System.out.println("Player died");
    }
}
