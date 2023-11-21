package com.group4.app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.Timer;

import com.group4.app.model.IDrawable;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public class WorldController {
    
    Model model;

    public WorldController(Model model){
        this.model = model;
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

    public void movePlayer(int x, int y){
        // gets the path
        List<Position> positions = PathfindingHelper.getShortestPath(model.getTile(model.getPlayerFloor(), getPlayerX(),getPlayerY()), model.getTile(model.getPlayerFloor(), x,y));
        
        // moves the player the first step to remove delay
        model.movePlayer(positions.get(0));
        model.updateObservers();

        // creates and starts the timer.
        Timer movementTimer = new Timer(500, null);
        movementTimer.start();
        movementTimer.addActionListener(new ActionListener() {
            private int currentPosIndex = 1;
            @Override
            public void actionPerformed(ActionEvent e){
                // if done with iterating through the positions, stop the timer. 
                if(currentPosIndex < positions.size()){
                    model.movePlayer(positions.get(currentPosIndex));
                    model.updateObservers();
                    currentPosIndex++;
                }
                else{
                    movementTimer.stop();
                }
            }
        });
        
        
    }
}
