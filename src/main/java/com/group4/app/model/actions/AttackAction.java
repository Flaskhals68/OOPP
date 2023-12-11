package com.group4.app.model.actions;

import java.util.HashSet;
import java.util.Set;

import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;
import com.group4.app.model.creatures.IAttackable;
import com.group4.app.model.creatures.ICanAttack;
import com.group4.app.model.creatures.IPositionable;

public class AttackAction extends Action<ICanAttack, IAttackable> {
    
    public AttackAction(int apCost, String name, ICanAttack actionTaker) {
        super(apCost, name, actionTaker);
    }

    /**
     * Only implemented for melee weapons currently,
     * but should be relatively simple to adapt for ranged as well in the future
     * @param target the entity getting hit
     */
    public void perform(IAttackable target) {
        int xDiff = Math.abs(this.getActionTaker().getPos().getX() - target.getPos().getX());
        int yDiff = Math.abs(this.getActionTaker().getPos().getY() - target.getPos().getY());

        if(!this.getActionTaker().getFloor().equals(target.getFloor())) {
            throw new IllegalArgumentException("Attacker and victim are on different floors/worlds");
        } else if(xDiff > 1 || yDiff > 1) {
            throw new IllegalArgumentException("Attacker is out of range");
        }

        (target).takeHit(this.getActionTaker().getDamage());
        Model.getInstance().updateObservers();
    }

    public Set<IAttackable> getTargetable() {
        Set<Position> positions = PathfindingHelper.getSurrounding(this.getActionTaker().getPos(), 1, Model.getInstance());
        positions.remove(getActionTaker().getPos());
        Set<IAttackable> attackables = new HashSet<IAttackable>();
        for (Position position : positions) {
            Set<IPositionable> positionables = Model.getInstance().getEntities(position);
            for (IPositionable positionable : positionables) {
                if (positionable instanceof IAttackable) {
                    attackables.add((IAttackable) positionable);
                }
            }
        }
        return attackables;
    }

    public Set<Position> getTargetablePositions() {
        Set<IAttackable> attackables = getTargetable();
        Set<Position> attackablePositions = new HashSet<Position>();
        for (IAttackable attackable : attackables) {
            attackablePositions.add(attackable.getPos());
        }

        return attackablePositions;
    }
}