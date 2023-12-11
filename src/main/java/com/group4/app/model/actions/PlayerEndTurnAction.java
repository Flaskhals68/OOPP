package com.group4.app.model.actions;

import java.util.HashSet;
import java.util.Set;

import com.group4.app.model.ITurnTaker;
import com.group4.app.model.TurnHandler;
import com.group4.app.model.creatures.Creature;
import com.group4.app.model.creatures.Player;

public class PlayerEndTurnAction extends Action<ITurnTaker, ITurnTaker>{

    public PlayerEndTurnAction(int maxPlayerAp, String id, Creature player){
        super(maxPlayerAp, id, player);
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
    public void perform(ITurnTaker player) {
        player.useAp(player.getAp());
    }
    
}
