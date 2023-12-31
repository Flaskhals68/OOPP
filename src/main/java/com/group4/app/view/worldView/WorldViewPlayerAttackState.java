package com.group4.app.view.worldView;

import java.awt.Color;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import com.group4.app.controller.worldControllers.AWorldController;
import com.group4.app.controller.worldControllers.PlayerViewAttackController;
import com.group4.app.model.dungeon.Position;
import com.group4.app.view.SoundPlayer;

public class WorldViewPlayerAttackState extends WorldViewState{
    private PlayerViewAttackController controller = new PlayerViewAttackController();
    private Color borderColor = Color.red;
    public WorldViewPlayerAttackState(Position playerPosition){
        super(playerPosition);
        setHighLightedPositions(controller.getAttacksInRange());
    }

    @Override
    public AWorldController getController() {
        return this.controller;
    }

    @Override
    public void colorBorders(Map<Position, JLayeredPane> visibleToPlayer) {
        for(Position pos : getHighlightedPositions()){
            visibleToPlayer.get(pos).setBorder(BorderFactory.createLineBorder(borderColor));
        }
    }

    @Override
    public void drawMouseClickedOnTile(Position targetPosition) {
        SoundPlayer.playSound("src/resources/547042__eponn__hit-impact-sword-3.wav", false);
        controller.mouseClicked(targetPosition);
    }


    @Override
    public void drawMouseEnteredTile(Position targetPosition) {
        // TODO Left open for future implementation
    }

    @Override
    public void drawMouseExitedTile() {
        // TODO Left open for future implementation
    }
    
}
