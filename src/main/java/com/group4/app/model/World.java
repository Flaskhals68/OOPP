package com.group4.app.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class World {
    private String uniqueID;
    private Tile[][] tiles;

    public World(int size){
        this.uniqueID = UUID.randomUUID().toString();
        this.tiles = new Tile[size][size];
    }

    public String getId(){
        return this.uniqueID;
    }

    public Tile getTile(int xPos, int yPos){
        return this.tiles[xPos][yPos];
    }

    public Set<Entity> getEntities(int xPos, int yPos){
        return this.tiles[xPos][yPos].getEntities();
    }

    public void addTile(Tile tile){
        this.tiles[tile.getXPos()][tile.getYPos()] = tile;
    }

    public void addEntity(Entity entity, int xPos, int yPos){
        this.getTile(xPos, yPos).addEntity(entity);
    }

    public void removeEntity(Entity entity){
        this.getTile(entity.getXPos(), entity.getYPos()).removeEntity(entity);
    }
}
