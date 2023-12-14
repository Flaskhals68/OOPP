package com.group4.app.model.actions;

import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;
import com.group4.app.model.creatures.ICreatureManager;
import com.group4.app.model.creatures.IPositionable;

import java.util.List;

public class EnemyMoveAction extends MoveAction{


    public EnemyMoveAction(int apCost, String name, IPositionable actionTaker, int moveSpeed, ICreatureManager manager) {
        super(apCost, name, actionTaker, moveSpeed, manager);
    }

    @Override
    public void perform(Position target) {
        System.out.println(getName() + " should move now");
        List<Position> path;
        // gives the entire path to the player, irrespective of distance
        path = PathfindingHelper.pathToClosest(getActionTaker().getPos(), target, getManager().getTileContainer());

        // Limit move length to moveSpeed, so that the enemy doesn't move too far
        path = path.subList(0, Math.min(path.size(), getMoveSpeed()));

        if(!path.isEmpty()) {
            for(Position p : path) {
                System.out.println("Moving to " + p.getX() + ", " + p.getY());
                getManager().remove(getActionTaker());
                getActionTaker().setPos(p);
                getManager().add(getActionTaker());
                getManager().updateObservers();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println(getName() + " is blocked and can't move.");
        }
    }


}
