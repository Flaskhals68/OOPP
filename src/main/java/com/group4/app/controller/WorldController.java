package com.group4.app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.Timer;

import com.group4.app.model.IDrawable;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;
import com.group4.app.model.Tile;

public class WorldController {
    
    // Model model;

    // public WorldController(Model model){
    //     this.model = model;
    // }

    private Model model;
    private List<Position> possess;
    private int currentPositionIndex;
    private Timer movementTimer;

    public WorldController(Model model) {
        this.model = model;
        possess = new ArrayList<>();
        currentPositionIndex = 0;
        movementTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPositionIndex < possess.size()) {
                    model.movePlayer(possess.get(currentPositionIndex));
                    model.updateObservers();
                    currentPositionIndex++;
                } else {
                    movementTimer.stop(); // Stop the timer when movement is complete
                }
            }
        });
    }

    public void movePlayer(int x, int y) {
        possess = PathfindingHelper.getShortestPath(model.getTile(model.getPlayerFloor(), model.getPlayerX(), model.getPlayerY()),
                model.getTile(model.getPlayerFloor(), x, y));
        currentPositionIndex = 0; // Reset the index for a new movement
        if (!movementTimer.isRunning()) {
            movementTimer.start(); // Start the timer if it's not already running
        }
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

    // public void movePlayer(int x, int y){
    //     List<Position> possess = PathfindingHelper.getShortestPath(model.getTile(model.getPlayerFloor(), getPlayerX(),getPlayerY()), model.getTile(model.getPlayerFloor(), x,y));
    //     for (Position pos : possess) {
    //         model.movePlayer(pos);
    //         model.updateObservers();

    //     }
    // }
}