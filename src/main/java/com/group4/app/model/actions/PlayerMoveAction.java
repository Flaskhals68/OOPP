package com.group4.app.model.actions;

import java.util.Set;
import com.group4.app.model.IPositionable;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public class PlayerMoveAction extends Action<IPositionable, Position>{
    private int apCost;
    private String name;
    private IPositionable actionTaker;
    
    public PlayerMoveAction(int apCost, String name, IPositionable actionTaker) {
        super(apCost, name, actionTaker);
    }

    public void perform(Position target) {
        Set<Position> legalMoves = getTargetablePositions();
        if (!legalMoves.contains(target)) {
            throw new IllegalArgumentException("Illegal move");
        }

        Model.getInstance().remove(actionTaker);
        actionTaker.setXPos(target.getX());
        actionTaker.setYPos(target.getY());
        Model.getInstance().add(actionTaker, actionTaker.getFloor(), target.getX(), target.getY());
    }

    public Set<Position> getTargetable() {
        return PathfindingHelper.getSurrounding(Model.getInstance().getTile(actionTaker.getFloor(), actionTaker.getYPos(), actionTaker.getYPos()), 5);
    }

    public Set<Position> getTargetablePositions() {
        return getTargetable();
    }
}
