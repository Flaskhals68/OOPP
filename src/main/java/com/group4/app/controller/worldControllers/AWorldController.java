package com.group4.app.controller.worldControllers;

import java.util.List;
import java.util.Set;

import com.group4.app.model.IController;
import com.group4.app.model.IDrawable;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public abstract class AWorldController implements IController{

    public AWorldController(){
    }

    public abstract void mouseClicked(Position position);
    public abstract void mouseEntered(Position position);
    public abstract void mouseExited();
    
    public List<IDrawable> getDrawables(int x, int y){
        return Model.getInstance().getDrawables(Model.getInstance().getPlayerFloor(), new Position(x,y,Model.getInstance().getPlayerFloor()));
    }

    public boolean isValidCoordinates(int x, int y){
        return Model.getInstance().isValidCoordinates(new Position(x,y, Model.getInstance().getPlayerFloor()));
    }

    public Position getPlayerPosition(){
        return Model.getInstance().getPlayerPos();
    }

    public String getPlayerFloor(){
        return Model.getInstance().getPlayerFloor();
    }

} 