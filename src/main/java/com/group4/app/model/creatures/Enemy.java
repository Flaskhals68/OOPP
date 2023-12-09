package com.group4.app.model.creatures;

import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;
import com.group4.app.model.actions.AttackActionInput;
import com.group4.app.model.actions.MoveAction;
import com.group4.app.model.actions.PositionActionInput;
import com.group4.app.model.items.Weapon;

import java.util.List;

public class Enemy extends Creature {
    private final String name;
    private static final int DETECTION_RANGE = 10;
    private final int moveSpeed;

    public Enemy(String id, String name, Position pos, Weapon weapon, int maxAp, Attributes attr, int level, int moveSpeed) {
        super(id, pos, maxAp, weapon, attr, level);
        this.name = name;
        this.moveSpeed = moveSpeed;
        this.addMoveAction("move", new MoveAction(1, "move", this, moveSpeed));
    }

    public String getName() {
        return name;
    }


    @Override
    public void takeTurn() {
        Model m = Model.getInstance();
        List<Position> path;

        Position pPos = m.getPlayerPos();
        int xDiff = Math.abs(pPos.getX() - getPos().getX());
        int yDiff = Math.abs(pPos.getY() - getPos().getY());

        int pDexBonus = m.getPlayer().getDexBonus();

        // if the player is too far away, don't do anything. Depends on players dex stat and enemy perception
        if(xDiff > (DETECTION_RANGE + getPerceptionBonus() - pDexBonus) || yDiff > (DETECTION_RANGE + getPerceptionBonus() - pDexBonus)) {
            return;
        }
        System.out.println(getName() + " is taking a turn");
        while(getAp() > 0) {
            if(m.nextToPlayer(this.getPos())){
                System.out.println(getName() + " is attacking player now");
                performAction(new AttackActionInput("attack", m.getPlayer()));
            } else {
                System.out.println(getName() + " should move now");

                // gives the entire path to the player, irrespective of distance
                path = PathfindingHelper.pathToClosest(getPos(), m.getPlayerPos(), m.getWorld(m.getPlayerFloor()));

                // Limit move length to moveSpeed, so that the enemy doesn't move too far
                path = path.subList(0, Math.min(path.size(), moveSpeed));

                if(path.size()>0) {
                    performAction(new PositionActionInput("move", path.get(path.size()-1)));
                } else {
                    performAction(new PositionActionInput("move", getPos()));
                }
            }

        }
    }

    @Override
    public void death() {
        // TODO : Implement the rest of enemy death
        Model.getInstance().remove(this);
        Model.getInstance().add(new Entity("deadEnemy", getPos()));
        Model.getInstance().removeFromTurnOrder(this);
        Model.getInstance().giveExperience(1);
    }
}
