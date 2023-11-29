package com.group4.app.controller;

import java.util.List;

import com.group4.app.model.IDrawable;
import com.group4.app.model.Model;
import com.group4.app.model.Position;

public class WorldViewController{
    Model model;

    public WorldViewController(Model model){
        this.model = model;
    }

    public boolean isValidCoordinates(int x, int y){
        return model.isValidCoordinates(new Position(x,y, model.getPlayerFloor()));
    }

    public String getPlayerFloor(){
        return model.getPlayerFloor();
    }

    public List<IDrawable> getDrawables(int x, int y){
        return model.getDrawables(model.getPlayerFloor(), new Position(x,y,model.getPlayerFloor()));
    }
}
