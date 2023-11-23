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
    private boolean hoverTimerFlag;
    private Timer hoverTimer =  new Timer(0, null);

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
        hoverTimerFlag = false;
        
        Position targePosition  = new Position(x, y);
        if(!getLegalMoves().contains(targePosition)){
            throw new IllegalArgumentException("Tile out of range");
        }
        
        List<Position> positions = PathfindingHelper.getShortestPath(model.getTile(model.getPlayerFloor(), getPlayerX(),getPlayerY()), model.getTile(model.getPlayerFloor(), x,y));

        highlightedPositions = new HashSet<Position>();
        for(Position p : positions){
            highlightedPositions.add(p);
        }
        
        // moves the player the first step to remove delay
        model.movePlayer(positions.get(0));
        highlightedPositions.remove(positions.get(0));
        model.updateObservers();

        // creates and starts the timer.
        Timer movementTimer = new Timer(500, null);
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

    public void mouseHover(int x, int y){
        
        Position targetPosition  = new Position(x, y);
        List<Position> updatedPositions = PathfindingHelper.getShortestPath(model.getTile(model.getPlayerFloor(), getPlayerX(), getPlayerY()), model.getTile(model.getPlayerFloor(), x, y));

        if(!movementTimerFlag && !hoverTimerFlag){
            if(getLegalMoves().contains(targetPosition)){
                highlightedPositions = new HashSet<>(updatedPositions);
                model.updateObservers();
                hoverTimerFlag = true;
                hoverTimer.start();
                hoverTimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                        highlightedPositions = new HashSet<>(updatedPositions);
                        model.updateObservers();
                    }
                });
                hoverTimer.stop();
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

    public void mouseExited(){
        if(hoverTimerFlag){
            hoverTimerFlag = false;
            hoverTimer.stop();
        }
    }
}
