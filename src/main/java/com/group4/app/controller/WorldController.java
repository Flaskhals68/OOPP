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
import com.group4.app.model.actions.PositionActionInput;

public class WorldController {
    
    private Model model;
    private Set<Position> highlightedPositions;
    private boolean movementTimerFlag;
    private boolean hoverFlag;

    public WorldController(Model model){
        this.model = model;
        this.highlightedPositions = getLegalMoves();
    }

    public List<IDrawable> getDrawables(int x, int y){
        return model.getDrawables(model.getPlayerFloor(), new Position(x,y,model.getPlayerFloor()));
    }

    public boolean isValidCoordinates(int x, int y){
        return model.isValidCoordinates(new Position(x,y, model.getPlayerFloor()));
    }

    public Position getPlayerPosition(){
        return model.getPlayerPos();
    }

    public Set<Position> getLegalMoves(){
        return PathfindingHelper.getSurrounding(model.getTile(new Position(getPlayerPosition().getX(), getPlayerPosition().getY(), model.getPlayerFloor())), 5);
    }

    public Set<Position> getHighlightedPositions(){
        return this.highlightedPositions;
    }

    public String getPlayerFloor(){
        return model.getPlayerFloor();
    }

    /**
     * Moves the player to a target position. Animates the movement of the player.
     * @param pos the clicked position
     */
    public void movePlayer(Position pos){

        // Position targePosition  = new Position(x, y, model.getPlayerFloor());
        Position targePosition  = pos;
        int playerX = getPlayerPosition().getX();
        int playerY = getPlayerPosition().getY();

        if(!getLegalMoves().contains(targePosition)){
            throw new IllegalArgumentException("Tile out of range");
        }
        
        //FIXME dont get straight from internal model classes
        List<Position> positions = PathfindingHelper.getShortestPath(model.getTile(new Position(playerX,playerY,model.getPlayerFloor())), model.getTile(targePosition));

        highlightedPositions = new HashSet<Position>(positions);
        
        // moves the player the first step to remove delay
        ActionController.getInstance().queueAction(new PositionActionInput("move", positions.get(0)));
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
                    ActionController.getInstance().queueAction(new PositionActionInput("move", positions.get(currentPosIndex)));
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
        int playerX = getPlayerPosition().getX();
        int playerY = getPlayerPosition().getY();

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
    public void mouseExited(){
        if(hoverFlag){
            hoverFlag = false;
        }
    }
}
