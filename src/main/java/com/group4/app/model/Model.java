package com.group4.app.model;

import java.util.*;

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

    private Model(){
        this.floors = new HashMap<String, World>();
    }

    public void addBasicMap(int size){
        World world = new World(100);
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                world.addTile(new Tile("stone", world.getId(), x, y));
            }
        }
        this.player = new Player(PLAYER_ID, 100, null, world.getId(), 0, 0);

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

    /**
     * Only implemented for melee weapons currently,
     * but should be relatively simple to adapt for ranged as well in the future
     * @param attacker the entity doing the attacking
     * @param victim the entity getting hit
     */
    public void performAttackAction(ICanAttack attacker, IAttackable victim) {
        int xDiff = Math.abs(attacker.getXPos() - victim.getXPos());
        int yDiff = Math.abs(attacker.getYPos() - victim.getYPos());

        if(!attacker.getFloor().equals(victim.getFloor())) {
            throw new IllegalArgumentException("Attacker and victim are on different floors/worlds");
        } else if(xDiff <= 1 && yDiff <= 1) {
            victim.takeHit(attacker.getDamage());
        } else {
            throw new IllegalArgumentException("Attacker is out of range");
        }

    }
}