package com.group4.app.controller;
import java.util.Set;

import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public class PlayerAttackController extends WorldViewPlayerController{
    
    public PlayerAttackController(Model model) {
        super(model);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String getControllerState(){
        return "attack";
    }

    @Override
    public void mouseExited() {}

    @Override
    public Set<Position> getHighlightedPositions() {
        // TODO
        // get the range somehow? Meaning the players weapon range prehaps?
        // works for now
        return model.getSurrounding(getPlayerPosition(), 1);
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
    
}
