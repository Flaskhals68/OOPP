package com.group4.app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Timer;

import com.group4.app.model.IDrawable;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public class PlayerMovementController extends WorldViewController implements IPlayerControllerState{

    private Model model;
    private Set<Position> highlightedPositions;
    private boolean movementTimerFlag;
    private boolean hoverFlag;

    public PlayerMovementController(Model model){
        super(model);
        this.highlightedPositions = getLegalMoves();
    }    

    @Override
    public void mouseClicked(Position position) {
        movePlayer(position);
    }

    @Override
    public void mouseEntered(Position position) {
        mouseHover(position);
    }

    @Override
    public void mouseExited() {
        mouseExitHover();
    }

    public Set<Position> getLegalMoves(){
        return model.getSurrounding(new Position(model.getPlayerPos().getX(), model.getPlayerPos().getY(), model.getPlayerFloor()), 5);
    }

    @Override
    public Set<Position> getHighlightedPositions() {
        return this.highlightedPositions;
    }

    /**
     * Moves the player to a target position. Animates the movement of the player.
     * @param pos the clicked position
     */
    public void movePlayer(Position pos){

        // Position targePosition  = new Position(x, y, model.getPlayerFloor());
        Position targePosition  = pos;
        int playerX = model.getPlayerPos().getX();
        int playerY = model.getPlayerPos().getY();

        if(!getLegalMoves().contains(targePosition)){
            throw new IllegalArgumentException("Tile out of range");
        }
        
        //FIXME dont get straight from internal model classes
        List<Position> positions = PathfindingHelper.getShortestPath(model.getTile(new Position(playerX,playerY,model.getPlayerFloor())), model.getTile(targePosition));

        highlightedPositions = new HashSet<Position>(positions);
        
        // moves the player the first step to remove delay
        model.movePlayer(positions.get(0));
        highlightedPositions.remove(positions.get(0));
        model.updateObservers();

        // creates and starts the timer.
        Timer movementTimer = new Timer(100, null);
        movementTimer.start();
        movementTimerFlag = true;
        movementTimer.addActionListener(new ActionListener() {
            private int currentPosIndex = 1;
            @Override
            public void actionPerformed(ActionEvent e){
                // if done with iterating through the positions, stop the timer. 
                if(currentPosIndex < positions.size()){
                    model.movePlayer(positions.get(currentPosIndex));
                    highlightedPositions.remove(positions.get(currentPosIndex));
                    model.updateObservers();
                    currentPosIndex++;
                }
                else{
                    highlightedPositions = getLegalMoves();
                    model.updateObservers();
                    movementTimerFlag = false;
                    movementTimer.stop();
                }
            }
        });
        
 
    }

    /**
     * Should run when hovering over a tile, updates the positions to be highlighted when hovering over a tile that is within legal movement range.
     * If hovering over a tile that is out of range it throws an IllegalArgumentException.
     * If a movement is happening at the same time when hovering, it throws an IllegalStateException
     * @param pos the position of the tile that the mouse is hovering over
     * 
     */
    public void mouseHover(Position pos){
        int playerX = model.getPlayerPos().getX();
        int playerY = model.getPlayerPos().getY();

        Position targetPosition  = pos;

        List<Position> positions = PathfindingHelper.getShortestPath(model.getTile(new Position(playerX,playerY,model.getPlayerFloor())), model.getTile(targetPosition));

        if(!movementTimerFlag && !hoverFlag){
            if(getLegalMoves().contains(targetPosition)){
                hoverFlag = true;
                highlightedPositions = new HashSet<>(positions);
                model.updateObservers();
            }
            else{
                highlightedPositions = getLegalMoves();
                model.updateObservers();
                return;
            }
        }
        else if(movementTimerFlag){
            return;
        }        
    }

    /**
     * When exiting a tile, a flag is set to false to indicate that the tile has been left.
     */
    public void mouseExitHover(){
        if(hoverFlag){
            hoverFlag = false;
        }
    }
    
}
