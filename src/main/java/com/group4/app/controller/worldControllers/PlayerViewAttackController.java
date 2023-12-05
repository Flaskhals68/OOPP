package com.group4.app.controller.worldControllers;

import java.util.Set;

import com.group4.app.model.ActionInput;
import com.group4.app.model.Model;
import com.group4.app.model.Position;

import com.group4.app.view.ActionState;


public class PlayerViewAttackController extends AWorldController{

    public PlayerViewAttackController(){
        super(ActionState.ATTACK);
    }

    public Set<Position> getAttacksInRange(){
        return Model.getInstance().getSurrounding(getPlayerPosition(), 1);
    }

    @Override
    public void mouseClicked(Position position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mouseEntered(Position position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public ActionInput<?> getActionInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActionInput'");
    }
    
}
