package com.group4.app.model.actions;

import java.util.HashSet;
import java.util.Set;

import com.group4.app.model.IAttackable;
import com.group4.app.model.ICanAttack;
import com.group4.app.model.IPositionable;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public class PlayerAttackAction extends Action<ICanAttack, IAttackable> {
    private int apCost;
    private String name;
    private ICanAttack actionTaker;
    
    public PlayerAttackAction(int apCost, String name, ICanAttack actionTaker) {
        super(apCost, name, actionTaker);
    }

    /**
     * Only implemented for melee weapons currently,
     * but should be relatively simple to adapt for ranged as well in the future
     * @param attacker the entity doing the attacking
     * @param victim the entity getting hit
     */
    public void perform(IAttackable target) {

        if (target instanceof IAttackable) {
            throw new IllegalArgumentException("Target is not attackable");
        }

        int xDiff = Math.abs(actionTaker.getXPos() - target.getXPos());
        int yDiff = Math.abs(actionTaker.getYPos() - target.getYPos());

        if(!actionTaker.getFloor().equals(target.getFloor())) {
            throw new IllegalArgumentException("Attacker and victim are on different floors/worlds");
        } else if(xDiff > 1 && yDiff > 1) {
            throw new IllegalArgumentException("Attacker is out of range");
        }

        (target).takeHit(actionTaker.getDamage());
    }

    public Set<IAttackable> getTargetable() {
        Set<Position> positions = this.getTargetablePositions();
        Set<IAttackable> attackables = new HashSet<IAttackable>();
        for (Position position : positions) {
            Set<IPositionable> entities = Model.getInstance().getEntities(actionTaker.getFloor(), position.getX(), position.getY());
            for (IPositionable entity : entities) {
                if (entity instanceof IAttackable) {
                    attackables.add((IAttackable) entity);
                }
            }
        }
        return attackables;
    }

    public Set<Position> getTargetablePositions() {
        return PathfindingHelper.getSurrounding(Model.getInstance().getTile(actionTaker.getFloor(), actionTaker.getYPos(), actionTaker.getYPos()), 5);
    }
}