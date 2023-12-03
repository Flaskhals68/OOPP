package com.group4.app.view.worldView;

import java.awt.Color;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import com.group4.app.model.Position;
import com.group4.app.view.ActionState;

public class WorldViewPlayerMoveState extends WorldViewState{
    private final ActionState state = ActionState.IDLE;

    public WorldViewPlayerMoveState(Position playerPosition){
        super(playerPosition);
    }

    @Override
    public void colorBorders(Set<Position> positionsToColor, Map<Position, JLayeredPane> visibleToPlayer) {
        for(Position pos : positionsToColor){
            visibleToPlayer.get(pos).setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        }
    }

    @Override
    public void drawMouseClickedOnTile(Position targetPosition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClickedOnTile'");
    }

    @Override
    public void drawMouseEnteredTile(Position targetPosition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEnteredTile'");
    }

    @Override
    public void drawMouseExitedTile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExitedTile'");
    }


    
}
