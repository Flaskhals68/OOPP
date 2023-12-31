package com.group4.app.controller.worldControllers;

import java.util.List;
import java.util.Set;

import com.group4.app.controller.ActionController;
import com.group4.app.model.Model;
import com.group4.app.model.dungeon.Position;
import com.group4.app.model.input.PositionActionInput;
import com.group4.app.view.ActionState;


public class PlayerMovementController extends AWorldController{

    public PlayerMovementController(){
        super(ActionState.IDLE);
    }

    public Set<Position> getLegalMoves(){
        return Model.getInstance().getLegalMoves();
    }

    public List<Position> getPathFromPlayerTo(Position position){
        return Model.getInstance().getPathFromTo(getPlayerPosition(), position);
    }

    @Override
    public void mouseClicked(Position position) {
        ActionController.getInstance().queueAction(new PositionActionInput("move", position));
    }

    @Override
    public void mouseEntered(Position position) {
        Model.getInstance().updateObservers();
    }

    @Override
    public void mouseExited() {
        return;
    }


}