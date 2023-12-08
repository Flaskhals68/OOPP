package com.group4.app.controller.worldControllers;

import java.util.Set;

import com.group4.app.controller.ActionController;
import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.actions.AttackActionInput;
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
        ActionController.getInstance().queueAction(new AttackActionInput("attack", Model.getInstance().getAttackedAtPosition(position)));
    }

    @Override
    public void mouseEntered(Position position) {
        // TODO Left open for future implementation      
    }

    @Override
    public void mouseExited() {
        // TODO Left open for future implementation        
    }
    
}
