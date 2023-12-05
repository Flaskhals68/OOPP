package com.group4.app.controller.worldControllers;

import java.util.List;
import java.util.Set;

import com.group4.app.model.ActionInput;
import com.group4.app.model.Model;
import com.group4.app.model.Position;

import com.group4.app.view.ActionState;


public class PlayerMovementController extends AWorldController{
    private Set<Position> highlightedPositions;
    private boolean movementTimerFlag;
    private boolean hoverFlag;

    public PlayerMovementController(){
        super(ActionState.IDLE);
    }

    public Set<Position> getLegalMoves(){
        return Model.getInstance().getSurrounding(new Position(getPlayerPosition().getX(), getPlayerPosition().getY(), Model.getInstance().getPlayerFloor()), 5);
    }

    public List<Position> getPathFromPlayerTo(Position position){
        return Model.getInstance().getPathFromTo(getPlayerPosition(), position);
    }

    //TODO fix
    @Override
    public void mouseClicked(Position position) {
        // Model.getInstance().performPlayerAction("move");
    }

    @Override
    public void mouseEntered(Position position) {
        Model.getInstance().updateObservers();
    }

    @Override
    public void mouseExited() {
        return;
    }

    @Override
    public ActionInput<?> getActionInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActionInput'");
    }
}