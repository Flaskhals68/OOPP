package com.group4.app.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.group4.app.view.GameWindow;
import com.group4.app.view.WorldView;

public class Model {
    private static Model instance = null;
    private Player player;
    private Map<String, World> floors;
    private World currentWorld;

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
        //Test World
        this.floors = new HashMap<String, World>();
        World floor0 = new World(100);
        addBasicMap(floor0, 100);
        setCurrentWorld(floor0);
        
        this.player = new Player(PLAYER_ID, 100, floor0.getTile(96,0));
        Enemy e = EnemyFactory.createZombie();
        floor0.getTile(5, 2).addEntity(e);

        Enemy e2 = EnemyFactory.createSkeleton();
        floor0.getTile(2, 2).addEntity(e2);

        floor0.getTile(player.getXPos(), player.getYPos()).addEntity(player);

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

    // temp fix to test view.
    private void setCurrentWorld(World world){
        this.currentWorld = world;
    }

    public Player getPlayer(){
        return this.player;
    }

    // Why did this need the world id as parameter, the view should not know about this? problem in the merge maybe, Removed the world parameter
    public Set<Entity> getEntities(int xPos, int yPos){
        return getTile(currentWorld.getId(), xPos, yPos).getEntities();
    }

    public Tile getPlayerTile(){
        return this.player.getTile();
    }

    // same here. Maybe remove?
    public Tile getTile(String floorId, int xPos, int yPos){
        return this.getWorld(floorId).getTile(xPos, yPos);
    }

    
}