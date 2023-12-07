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

    //TODO change to use actionInput
    public Set<Position> getAttacksInRange(){
        return Model.getInstance().getAttackablePositions();
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
    
}
