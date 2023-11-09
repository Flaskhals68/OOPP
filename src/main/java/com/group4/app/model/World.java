package com.group4.app.model;

import java.io.Serializable;
import java.util.UUID;

public class World implements Serializable{
    private Tile[][] tileMatrix;
    private String uniqueID;

    public World(int size){
        this.uniqueID = UUID.randomUUID().toString();
        this.tileMatrix = new Tile[size][size];
    }

    public String getId(){
        return this.uniqueID;
    }

    public Tile getTile(int xPos, int yPos){
        return this.tileMatrix[xPos][yPos];
    }

    public void addTile(Tile tile){
        this.tileMatrix[tile.getXPos()][tile.getYPos()] = tile;
    }
}
