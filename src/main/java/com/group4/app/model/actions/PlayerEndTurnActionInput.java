package com.group4.app.model.actions;

import com.group4.app.model.ITurnTaker;
import com.group4.app.model.creatures.Creature;


public class PlayerEndTurnActionInput extends ActionInput<Creature>{

    public PlayerEndTurnActionInput(Creature player) {
        super("endTurn", player);
    }
    
    public String getActionId(){
        return super.getActionId();
    }

    public ITurnTaker getTurnTaker(){
        return super.getTarget();
    }
}
