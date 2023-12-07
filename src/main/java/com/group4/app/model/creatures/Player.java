package com.group4.app.model.creatures;

import java.util.Random;
import java.util.Set;

import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.actions.ActionInput;
import com.group4.app.model.items.PotionFactory;
import com.group4.app.model.items.Weapon;

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
        Model m = Model.getInstance();
        m.startPlayerTurn();
        Set<Position> surrounding = m.getSurrounding(m.getPlayerPos(), 2);
        int counter = 0;
        for(Position p : surrounding) {
            if(!m.getEntities(p).isEmpty()) {
                counter += m.getEntities(p).size();
            }
        }
        while (this.getAp() > 0) {

            System.out.println("Player has " + (counter - 1)  + " enemies within 2 step around them");
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
