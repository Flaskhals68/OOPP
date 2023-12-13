package com.group4.app.model.actions;

import com.group4.app.model.ITurnTaker;
import com.group4.app.model.creatures.Creature;


public class EndTurnActionInput extends ActionInput<Creature>{

    public EndTurnActionInput(Creature creature) {
        super("endTurn", creature);
    }
    
    public String getActionId(){
        return super.getActionId();
    }

    public ITurnTaker getTurnTaker(){
        return super.getTarget();
    }
}
