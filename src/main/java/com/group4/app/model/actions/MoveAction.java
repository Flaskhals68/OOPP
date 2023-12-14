package com.group4.app.model.actions;

import java.util.List;
import java.util.Set;

import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;
import com.group4.app.model.creatures.ICreatureManager;
import com.group4.app.model.creatures.IPositionable;

public class MoveAction extends Action<IPositionable, Position>{

    private final int moveSpeed;
    private final ICreatureManager manager;
    public MoveAction(int apCost, String name, IPositionable actionTaker, int moveSpeed, ICreatureManager manager) {
        super(apCost, name, actionTaker);
        this.moveSpeed = moveSpeed;
        this.manager = manager;
    }

    public void perform(Position target) {
        Set<Position> legalMoves = getTargetablePositions();
        if (!legalMoves.contains(target)) {
            throw new IllegalArgumentException("Illegal move");
        }

        List<Position> path = PathfindingHelper.getShortestPath(getActionTaker().getPos(), target, manager.getTileContainer());
        for (Position pos : path) {
            manager.remove(getActionTaker());
            getActionTaker().setPos(pos);
            manager.add(getActionTaker());
            System.out.println("Moving to " + pos.getX() + ", " + pos.getY());
            manager.updateObservers();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<Position> getTargetable() {
        return PathfindingHelper.getSurrounding(getActionTaker().getPos(), moveSpeed, manager.getTileContainer());
    }

    public Set<Position> getTargetablePositions() {
        return getTargetable();
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public ICreatureManager getManager() {
        return manager;
    }
}
