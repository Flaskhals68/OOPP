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
        return model.getDrawables(model.getPlayerFloor(), x, y);
    }

    public boolean isValidCoordinates(int x, int y){
        return model.isValidCoordinates(x, y);
    }

    public int getPlayerX(){
        return model.getPlayerX();
    }

    public int getPlayerY(){
        return model.getPlayerY();
    }

    public Set<Position> getLegalMoves(){
        return PathfindingHelper.getSurrounding(model.getTile(model.getPlayerFloor(), getPlayerX(),getPlayerY()), 5);
    }

    public Set<Position> getHighlightedPositions(){
        return this.highlightedPositions;
    }

    public void movePlayer(int x, int y){

        Position targePosition  = new Position(x, y);
        if(!getLegalMoves().contains(targePosition)){
            throw new IllegalArgumentException("Tile out of range");
        }
        
        //FIXME dont get straight from internal model classes
        List<Position> positions = PathfindingHelper.getShortestPath(model.getTile(model.getPlayerFloor(), getPlayerX(),getPlayerY()), model.getTile(model.getPlayerFloor(), x,y));

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
     * @param x
     * @param y
     */
    public void mouseHover(int x, int y){
        Position targetPosition  = new Position(x, y);

        List<Position> positions = PathfindingHelper.getShortestPath(model.getTile(model.getPlayerFloor(), getPlayerX(), getPlayerY()), model.getTile(model.getPlayerFloor(), x, y));

        if(!movementTimerFlag && !hoverFlag){
            if(getLegalMoves().contains(targetPosition)){
                hoverFlag = true;
                highlightedPositions = new HashSet<>(positions);
                model.updateObservers();
            }
            else{
                highlightedPositions = getLegalMoves();
                model.updateObservers();
                throw new IllegalArgumentException("Tile out of range");
            }
        }
        else if(movementTimerFlag){
            throw new IllegalStateException("Movement in progress, tiles not highlighted");
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
