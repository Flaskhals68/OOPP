package com.group4.app.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Model {
    private static Model instance = null;
    private Player player;
    private Map<String, World> floors;

    private static final String PLAYER_ID = "player";
    
    public static Model getInstance(){
        if (instance == null) {
            instance = new Model();
            return instance;
        }
        else {
            return Model.instance;
        }
    }

    public Model(){
        this.floors = new HashMap<String, World>();
    }

    public void addBasicMap(int size){
        World world = new World(100);
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                world.addTile(new Tile(world.getId(), x, y));
            }
        }
        this.player = new Player(PLAYER_ID, 100, null, world.getId(), 0, 0);

        this.floors.put(world.getId(), world);
    }

    private World getWorld(String floorId){
        return this.floors.get(floorId);
    }

    public void addWorld(World world){
        this.floors.put(world.getId(), world);
    }

    public Player getPlayer(){
        return this.player;
    }

    public Set<Entity> getEntities(String floorId, int xPos, int yPos){
        return getTile(floorId, xPos, yPos).getEntities();
    }

    public Tile getTile(String floorId, int xPos, int yPos){
        return this.getWorld(floorId).getTile(xPos, yPos);
    }
}