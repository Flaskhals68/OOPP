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
    private final ActionState state = ActionState.IDLE;
    private boolean movementTimerFlag;
    private boolean hoverFlag;
    private Timer movementTimer;
    private final int movementTimerDelay = 100;
    private PlayerMovementController controller = new PlayerMovementController();

    public WorldViewPlayerMoveState(Position playerPosition){
        super(playerPosition);
        setHighLightedPositions(controller.getLegalMoves());
        System.out.println("null");
    }

    private void initMovementTimer(int delay){
        movementTimer = new Timer(delay, null);
        movementTimer.start();
        movementTimerFlag = true;
    }

    private void stopMovementTimer(){
        movementTimer.stop();
        movementTimerFlag = false;
    }

    @Override
    public void colorBorders(Map<Position, JLayeredPane> visibleToPlayer) {
        for(Position pos : getHighlightedPositions()){
            visibleToPlayer.get(pos).setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        }
    }
    
    /**
     *  Starts and animates the movement of the player
     */
    @Override
    public void drawMouseClickedOnTile(Position targetPosition) {
        List<Position> path = controller.getPathFromPlayerTo(targetPosition); 
        
        setHighLightedPositions(new HashSet<>(path));
        getHighlightedPositions().remove(path.get(0));
        controller.mouseClicked(path.get(0));
        
        initMovementTimer(movementTimerDelay); 

        movementTimer.addActionListener(new ActionListener() {
            private int currentPosIndex = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPosIndex < path.size()-1){
                    getHighlightedPositions().remove(path.get(currentPosIndex));
                    controller.mouseClicked(path.get(currentPosIndex));
                    currentPosIndex++;            
                }
                else{
                    controller.mouseClicked(path.get(currentPosIndex));
                    setHighLightedPositions(controller.getLegalMoves());
                    //FIXME weird way of updating obeservers?
                    controller.mouseClicked(controller.getPlayerPosition());
                    stopMovementTimer();
                }
            }
        });
        System.out.println(controller.getPlayerPosition().getX());

    }

    /**
     * Highlights a path between the position  of the player and the position the mouse has entered.
     */
    @Override
    public void drawMouseEnteredTile(Position targetPosition) {
        
        List<Position> path = controller.getPathFromPlayerTo(targetPosition); 

        if(!movementTimerFlag && !hoverFlag){
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
