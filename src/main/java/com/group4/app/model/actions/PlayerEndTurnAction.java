package com.group4.app.model.actions;

import java.util.Set;

import com.group4.app.model.ITurnTaker;
import com.group4.app.model.creatures.Creature;
import com.group4.app.model.creatures.Player;

public class PlayerEndTurnAction extends Action<ITurnTaker, ITurnTaker>{

    public PlayerEndTurnAction(int maxPlayerAp, String id, Creature player){
        super(maxPlayerAp, id, player);
    }

    @Override
    public Set getTargetable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetable'");
    }

    @Override
    public Set getTargetablePositions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetablePositions'");
    }


    @Override
    public void perform(ITurnTaker player) {
        player.useAp(player.getAp());
    }
    
}
