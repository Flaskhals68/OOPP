package com.group4.app.controller;
import java.util.Set;

import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public class PlayerAttackController extends WorldViewController implements IPlayerControllerState{
    
    public PlayerAttackController(Model model) {
        super(model);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void mouseExited() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public Set<Position> getHighlightedPositions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHighlightedPositions'");
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
