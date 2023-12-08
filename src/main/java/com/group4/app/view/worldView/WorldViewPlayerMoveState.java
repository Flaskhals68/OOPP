package com.group4.app.view.worldView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import com.group4.app.controller.worldControllers.AWorldController;
import com.group4.app.controller.worldControllers.PlayerMovementController;
import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.view.ActionState;

public class WorldViewPlayerMoveState extends WorldViewState{
    private boolean movementTimerFlag;
    private boolean hoverFlag;
    private PlayerMovementController controller = new PlayerMovementController();
    private Position mouseClickedPosition;

    public WorldViewPlayerMoveState(Position playerPosition){
        super(playerPosition);
        setHighLightedPositions(controller.getLegalMoves());
    }

    @Override
    public void colorBorders(Map<Position, JLayeredPane> visibleToPlayer) {
        drawMovementPathIfMoving();
        for(Position pos : getHighlightedPositions()){
            visibleToPlayer.get(pos).setBorder(BorderFactory.createLineBorder(Color.cyan));
        }
    }

    private void drawMovementPathIfMoving(){
        if(movementTimerFlag && !controller.getPlayerPosition().equals(mouseClickedPosition)){
            List<Position> path = controller.getPathFromPlayerTo(mouseClickedPosition);
            setHighLightedPositions(new HashSet<>(path));
        }
        else if(movementTimerFlag && controller.getPlayerPosition().equals(mouseClickedPosition)){
            setHighLightedPositions(controller.getLegalMoves());
            movementTimerFlag = false;
        }
    }
    
    /**
     *  Starts and animates the movement of the player
     */
    @Override
    public void drawMouseClickedOnTile(Position targetPosition) {
        mouseClickedPosition = targetPosition;
        movementTimerFlag = true;
        controller.mouseClicked(targetPosition);
    }

    /**
     * Highlights a path between the position  of the player and the position the mouse has entered.
     */
    @Override
    public void drawMouseEnteredTile(Position targetPosition) {
        List<Position> path = controller.getPathFromPlayerTo(targetPosition);
        
        if(!hoverFlag && !movementTimerFlag){
            if(controller.getLegalMoves().contains(targetPosition)){
                hoverFlag = true;
                setHighLightedPositions(new HashSet<>(path));
                controller.mouseEntered(targetPosition);

            }
            else{
                setHighLightedPositions(controller.getLegalMoves());
                controller.mouseEntered(targetPosition);
                return;
            }
        }
        else if(movementTimerFlag){
            return;
        } 
    }

    @Override
    public void drawMouseExitedTile() {
        if(hoverFlag){
            hoverFlag = false;
        }
    }

    @Override
    public AWorldController getController() {
        return this.controller;
    }


    
}
