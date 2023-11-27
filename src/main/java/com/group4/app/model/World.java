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

    public Tile getTile(int xPos, int yPos){
        return this.tiles[xPos][yPos];
    }

    public Set<IPositionable> getEntities(int xPos, int yPos){
        return this.tiles[xPos][yPos].getEntities();
    }

    public void addTile(Tile tile){
        this.tiles[tile.getXPos()][tile.getYPos()] = tile;
    }

    public void add(IPositionable entity, int xPos, int yPos){
        this.getTile(xPos, yPos).add(entity);
    }

    public void remove(IPositionable entity){
        this.getTile(entity.getXPos(), entity.getYPos()).remove(entity);
    }
}
