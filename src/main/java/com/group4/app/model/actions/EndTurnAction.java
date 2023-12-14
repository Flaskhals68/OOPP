package com.group4.app.model.actions;

import java.util.HashSet;
import java.util.Set;

import com.group4.app.model.creatures.Creature;
import com.group4.app.model.turns.ITurnTaker;

public class EndTurnAction extends Action<ITurnTaker, ITurnTaker>{

    public EndTurnAction(int maxPlayerAp, String id, ITurnTaker creature){
        super(maxPlayerAp, id, creature);
    }

    @Override
    public Set getTargetable() {
        Set<ITurnTaker> tts = new HashSet<>();
        tts.add(getActionTaker());
        return tts;
    }

    @Override
    public Set getTargetablePositions() {
        return getTargetable();
    }


    @Override
    public void perform(ITurnTaker creature) {
        creature.useAp(creature.getAp());
    }
    
}
