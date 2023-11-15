package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Model {
    private static Model instance = null;
    private Player player;
    private TurnHandler turnHandler;
    private Boolean isPlayerTurn;
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

    private Model(){
        this.floors = new HashMap<String, World>();
        this.isPlayerTurn = false;
        this.turnHandler = new TurnHandler();
    }

    public void addBasicMap(int size){
        World world = new World(100);
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                world.addTile(new Tile("stone", world.getId(), x, y));
            }
        }
        this.player = new Player(PLAYER_ID, 100, 3, null, world.getId(), 0, 0);

        this.floors.put(world.getId(), world);
    }

    public void addWorld(World world){
        this.floors.put(world.getId(), world);
    }

    private World getWorld(String floorId){
        return this.floors.get(floorId);
    }

    public Player getPlayer(){
        return this.player;
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

    public void startPlayerTurn(){
        this.isPlayerTurn = true;
    }

    public void endPlayerTurn(){
        this.isPlayerTurn = false;
        this.turnHandler.endTurn();
    }

    public boolean isPlayerTurn(){
        return this.isPlayerTurn;
    }

    public void addToTurnOrder(ITurnTaker turnTaker){
        this.turnHandler.add(turnTaker);
    }

    public void removeFromTurnOrder(ITurnTaker turnTaker){
        this.turnHandler.remove(turnTaker);
    }

    public void startTurn(){
        this.turnHandler.startTurn();
    }

    public void endTurn(){
        this.turnHandler.endTurn();
    }
}