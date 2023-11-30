package com.group4.app.controller;

import java.util.List;
import java.util.Set;

import com.group4.app.model.IDrawable;
import com.group4.app.model.Model;
import com.group4.app.model.Position;

public abstract class WorldViewPlayerController{
    protected Model model;

    public abstract void mouseClicked(Position position);
    public abstract void mouseEntered(Position position);
    public abstract void mouseExited();
    public abstract String getControllerState();

    public WorldViewPlayerController(Model model){
        this.model = model;
    }

    public boolean isValidCoordinates(int x, int y){
        return model.isValidCoordinates(new Position(x,y, model.getPlayerFloor()));
    }

    public String getPlayerFloor(){
        return model.getPlayerFloor();
    }

    public Position getPlayerPosition(){
        return model.getPlayerPos();
    }

    public List<IDrawable> getDrawables(int x, int y){
        return model.getDrawables(model.getPlayerFloor(), new Position(x,y,model.getPlayerFloor()));
    }

    public abstract Set<Position> getHighlightedPositions();
}
