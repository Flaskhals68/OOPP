package com.group4.app.model.actions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;
import com.group4.app.model.creatures.IPositionable;

public class MoveAction extends Action<IPositionable, Position>{

    private final int moveSpeed;
    public MoveAction(int apCost, String name, IPositionable actionTaker, int moveSpeed) {
        super(apCost, name, actionTaker);
        this.moveSpeed = moveSpeed;
    }

    public void perform(Position target) {
        Set<Position> legalMoves = getTargetablePositions();
        if (!legalMoves.contains(target)) {
            throw new IllegalArgumentException("Illegal move");
        }

        List<Position> path = Model.getInstance().getPathFromTo(getActionTaker().getPos(), target);
        for (Position pos : path) {
            Model.getInstance().remove(getActionTaker());
            getActionTaker().setPos(pos);
            Model.getInstance().add(getActionTaker());
            System.out.println("Moving to " + pos.getX() + ", " + pos.getY());
            Model.getInstance().updateObservers();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public Set<Position> getTargetable() {
        return PathfindingHelper.getSurrounding(getActionTaker().getPos(), moveSpeed, Model.getInstance());
    }

    public Set<Position> getTargetablePositions() {
        return getTargetable();
    }
}
