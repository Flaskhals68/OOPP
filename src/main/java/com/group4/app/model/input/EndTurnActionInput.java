package com.group4.app.model.input;

import com.group4.app.model.creatures.Creature;
import com.group4.app.model.turns.ITurnTaker;


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
