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

    public int getWorldHeight(){
        return tiles[0].length;
    }

    public int getWorldWidth(){
        return tiles.length;
    }

    public Tile getTile(Position pos){
        return this.tiles[pos.getX()][pos.getY()];
    }

    public Set<Entity> getEntities(int xPos, int yPos){
        return this.tiles[xPos][yPos].getEntities();
    }

    public void addTile(Tile tile){
        this.tiles[tile.getXPos()][tile.getYPos()] = tile;
    }

    public void addEntity(Entity entity, Position pos){
        this.getTile(pos).addEntity(entity);
    }

    public void removeEntity(Entity entity){
        this.getTile(entity.getPos()).removeEntity(entity);
    }
}
