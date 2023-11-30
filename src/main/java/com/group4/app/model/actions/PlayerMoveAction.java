package com.group4.app.model.actions;

import java.util.Set;
import com.group4.app.model.IPositionable;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public class PlayerMoveAction extends Action<IPositionable, Position>{
    
    public PlayerMoveAction(int apCost, String name, IPositionable actionTaker) {
        super(apCost, name, actionTaker);
    }

    public void perform(Position target) {
        Set<Position> legalMoves = getTargetablePositions();
        if (!legalMoves.contains(target)) {
            throw new IllegalArgumentException("Illegal move");
        }

        Model.getInstance().remove(getActionTaker());
        getActionTaker().setPos(target);
        Model.getInstance().add(getActionTaker(), target);
    }

    public Set<Position> getTargetable() {
        return PathfindingHelper.getSurrounding(Model.getInstance().getTile(getActionTaker().getPos()), 5);
    }

    public Set<Position> getTargetablePositions() {
        return getTargetable();
    }
}
