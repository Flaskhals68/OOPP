package com.group4.app.view.worldView;

import java.awt.Color;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import com.group4.app.controller.worldControllers.AWorldController;
import com.group4.app.controller.worldControllers.PlayerViewAttackController;
import com.group4.app.model.Model;
import com.group4.app.model.Position;

public class WorldViewPlayerAttackState extends WorldViewState{
    private PlayerViewAttackController controller = new PlayerViewAttackController();
    public WorldViewPlayerAttackState(Position playerPosition){
        super(playerPosition);
        //TODO implement
        setHighLightedPositions(controller.getAttacksInRange());
    }

    @Override
    public AWorldController getController() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getController'");
    }

    @Override
    public void colorBorders(Map<Position, JLayeredPane> visibleToPlayer) {
        for(Position pos : getHighlightedPositions()){
            visibleToPlayer.get(pos).setBorder(BorderFactory.createLineBorder(Color.red, 1));
        }
    }

    @Override
    public void drawMouseClickedOnTile(Position targetPosition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawMouseClickedOnTile'");
    }

    @Override
    public void drawMouseEnteredTile(Position targetPosition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawMouseEnteredTile'");
    }

    @Override
    public void drawMouseExitedTile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawMouseExitedTile'");
    }
    
}
