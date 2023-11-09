package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.group4.app.view.GameWindow;

public class Model {
    private static Model instance = null;
    private Player player;
    private Map<String, World> floors;

    private static final String PLAYER_ID = "player";
    
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
        World floor0 = new World(100);
        addBasicMap(floor0, 10);
        
        this.player = new Player(PLAYER_ID, 100, floor0.getTile(0,0););

        this.floors.put(floor0.getId(), floor0);

    }

    private void addBasicMap(World world, int size){
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                world.addTile(new Tile(world, x, y));
            }
        }
    }

    private World getWorld(String id){
        return this.floors.get(id);
    }

    private World getWorld(World floor){
        return this.floors.get(floor.getId());
    }

    public Tile getTile(int xPos, int yPos){
        return this.player.getWorld().getTile(xPos, yPos);
    }

    public Tile getPlayerTile(int xPos, int yPos){
        return this.player.getTile();
    }

    public Tile getTile(World floor, int xPos, int yPos){
        return this.getWorld(floor).getTile(xPos, yPos);
    }

    public Tile getTile(String floorId, int xPos, int yPos){
        return this.getWorld(floorId).getTile(xPos, yPos);
    }
}