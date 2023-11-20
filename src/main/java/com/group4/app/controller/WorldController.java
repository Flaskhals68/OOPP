package com.group4.app.controller;

import java.util.List;
import java.util.Set;

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
        model.movePlayer(new Position(x, y));
        model.updateObservers();
    }
}
