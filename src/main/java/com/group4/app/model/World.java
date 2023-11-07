package com.group4.app.model;

import java.io.Serializable;

public class World implements Serializable{
    private Tile[][] tileMatrix;

    public World(int size){
        this.tileMatrix = new Tile[size][size];
    }

    public Tile[][] getTileMatrix(){
        return this.tileMatrix;
    }

    public Tile getTile(int xPos, int yPos){
        return this.tileMatrix[xPos][yPos];
    }

    public void addTile(Tile tile){
        this.tileMatrix[tile.getXPos()][tile.getYPos()] = tile;
    }
}
