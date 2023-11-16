package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            instance = new Model();
            return instance;
        }
        else {
            return Model.instance;
        }
    }

    private Model(){
        this.floors = new HashMap<String, World>();
    }

    public void addBasicMap(int size){
        World world = new World(100);
        this.addWorld(world);
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                world.addTile(new Tile("stone", world.getId(), x, y));
            }
        }
        this.player = new Player(PLAYER_ID, 100, null, world.getId(), 99, 0);
        addEntity(player, world.getId(), player.getXPos(), player.getYPos());
    }

    public void addWorld(World world){
        this.floors.put(world.getId(), world);
    }

    private World getWorld(String floorId){
        return this.floors.get(floorId);
    }

    public String getPlayerFloor(){
        return player.getFloor();
    }

    public Player getPlayer(){
        return this.player;
    }

    public int getPlayerX(){
        return this.player.getXPos();
    }

    public int getPlayerY(){
        return this.player.getYPos();
    }

    public Tile getTile(String floorId, int xPos, int yPos){
        return this.getWorld(floorId).getTile(xPos, yPos);
    }

    public Set<Entity> getEntities(String floorId, int xPos, int yPos){
        return getTile(floorId, xPos, yPos).getEntities();
    }

    public List<IDrawable> getDrawables(String floorId, int xPos, int yPos){
        IDrawable[] entities = getEntities(floorId, xPos, yPos).toArray(new IDrawable[0]);
        IDrawable tile = getTile(floorId, xPos, yPos);
        ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
        drawables.add(tile);
        for (IDrawable entity : entities){
            drawables.add(entity);
        }
        return drawables;
    }

    public void addEntity(Entity entity, String floorId, int xPos, int yPos){
        this.getWorld(floorId).addEntity(entity, xPos, yPos);
    }

    public void removeEntity(Entity entity){
        this.getWorld(entity.getFloor()).removeEntity(entity);
    }

    
}