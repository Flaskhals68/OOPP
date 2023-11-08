package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.group4.app.view.GameWindow;

public class Model {
    private static Model instance = null;
    //private Player player;
    private Map<String, World> floors;
    
    public static Model getInstance(){
        if (instance == null) {
            return new Model();
        }
        else {
            return Model.instance;
        }
    }

    public Model(){
        this.floors = new HashMap<String, World>();
        //this.player = new Player();
    }

    private World getWorld(String id){
        return this.floors.get(id);
    }

    private World getWorld(World floor){
        return this.floors.get(floor.getId());
    }

    // public Tile getTile(int xPos, int yPos){
    //     return this.player.getWorld().getTile(xPos, yPos);
    // }

    // public Tile getPlayerTile(int xPos, int yPos){
    //     return this.player.getTile();
    // }

    public Tile getTile(World floor, int xPos, int yPos){
        return this.getWorld(floor).getTile(xPos, yPos);
    }

    public Tile getTile(String floorId, int xPos, int yPos){
        return this.getWorld(floorId).getTile(xPos, yPos);
    }
}