package com.group4.app.model.creatures;

import com.group4.app.model.Model;
import com.group4.app.model.actions.ItemAction;
import com.group4.app.model.actions.MoveAction;
import com.group4.app.model.dungeon.Position;
import com.group4.app.model.input.ActionInput;
import com.group4.app.model.items.PotionFactory;
import com.group4.app.model.items.Weapon;

public class Player extends Creature {

    private final ResourceBar xp;
    private final IPlayerManager manager;

    public Player(String id, int ap, Weapon weapon, Position position, IPlayerManager manager) {
        super(id, position, ap, weapon, new Attributes(50, 50, 50, 50, 50, 50), 1, manager);
        this.xp = new ResourceBar(10);
        this.xp.setCurrent(0);
        this.manager = manager;
        this.addMoveAction("move", new MoveAction(1, "move", this, 5));
        for (int i = 0; i < 3; i++) {
            this.addItemToInventory(PotionFactory.createHealthPotion());
        }
        this.addInvAction("useItem", new ItemAction(1, "useItem", this));
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
        manager.startPlayerTurn();
        while (this.getAp() > 0) {
            ActionInput<?> input = manager.getActionInput();
            this.performAction(input);
        }
        endTurn();
    }

    public void endTurn() {
        manager.endPlayerTurn();
    }

    @Override
    public void death() {
        manager.remove(this);
        manager.setPlayerDied();
    }
}
