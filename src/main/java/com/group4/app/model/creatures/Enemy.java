package com.group4.app.model.creatures;

import com.group4.app.model.actions.EnemyMoveAction;
import com.group4.app.model.dungeon.Position;
import com.group4.app.model.input.AttackActionInput;
import com.group4.app.model.input.PositionActionInput;
import com.group4.app.model.items.Weapon;

import java.util.List;

public class Enemy extends Creature {
    private final String name;
    private static final int DETECTION_RANGE = 10;
    private final int moveSpeed;
    private final IEnemyManager manager;

    public Enemy(String id, String name, Position pos, Weapon weapon, int maxAp, Attributes attr, int level, int moveSpeed, IEnemyManager manager) {
        super(id, pos, maxAp, weapon, attr, level, manager);
        this.name = name;
        this.moveSpeed = moveSpeed;
        this.manager = manager;
        this.addMoveAction("move", new EnemyMoveAction(1, "move", this, moveSpeed, manager));
    }

    public String getName() {
        return name;
    }


    @Override
    public void takeTurn() {

        Position pPos = manager.getPlayerPos();

        int xDiff = Math.abs(pPos.getX() - getPos().getX());
        int yDiff = Math.abs(pPos.getY() - getPos().getY());

        int pStealthBonus = manager.getPlayerStealthBonus();

        // if the player is too far away, don't do anything. Depends on players dex stat and enemy perception
        if(xDiff > (DETECTION_RANGE + getPerceptionBonus() - pStealthBonus) || yDiff > (DETECTION_RANGE + getPerceptionBonus() - pStealthBonus)) {
            return;
        }
        System.out.println(getName() + " is taking a turn");
        while(getAp() > 0) {
            if(manager.nextToPlayer(this.getPos())){
                System.out.println(getName() + " is attacking player now");
                performAction(new AttackActionInput("attack", manager.getPlayer()));
            } else {
                performAction(new PositionActionInput("move", manager.getPlayerPos()));
            }

        }
    }

    @Override
    public void death() {
        manager.remove(this);
        manager.setDeadTile(getPos());
        manager.removeFromTurnOrder(this);
        manager.giveExperienceToPlayer(1);
    }
}
