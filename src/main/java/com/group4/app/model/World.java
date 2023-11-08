package com.group4.app.model;

import java.io.Serializable;
import java.io.IOException;

public class World implements Serializable{
    private static final long serialVersionUID = 1L;
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

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        for (Tile[] column : this.tileMatrix){
            for (Tile tile : column){
                if (tile != null){
                    tile.setWorld(this);
                    tile.calculateNeighbors();
                }
            }
        }
    }
}
