package com.group4.app.model.actions;

import java.util.List;
import java.util.Set;

import com.group4.app.model.Model;
import com.group4.app.model.dungeon.IPositionable;
import com.group4.app.model.dungeon.PathfindingHelper;
import com.group4.app.model.dungeon.Position;

public class MoveAction extends Action<IPositionable, Position>{

    private final int moveSpeed;
    public MoveAction(int apCost, String name, IPositionable actionTaker, int moveSpeed) {
        super(apCost, name, actionTaker);
        this.moveSpeed = moveSpeed;

    }

    public void perform(Position target) {
        Model m = Model.getInstance();
        Set<Position> legalMoves = getTargetablePositions();
        if (!legalMoves.contains(target)) {
            throw new IllegalArgumentException("Illegal move");
        }

        List<Position> path = PathfindingHelper.getShortestPath(getActionTaker().getPos(), target, m.getTileContainer());
        for (Position pos : path) {
            m.remove(getActionTaker());
            getActionTaker().setPos(pos);
            m.add(getActionTaker());
            System.out.println("Moving to " + pos.getX() + ", " + pos.getY());
            m.updateObservers();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<Position> getTargetable() {
        return PathfindingHelper.getSurrounding(getActionTaker().getPos(), moveSpeed, Model.getInstance().getTileContainer());
    }

    public Set<Position> getTargetablePositions() {
        return getTargetable();
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

}
