package com.group4.app.model;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Tile implements IDrawable {
    private String floor;
    private Set<Tile> neighbors;
    private int xPos;
    private int yPos;
    private Set<Entity> entities;

    public Tile(String floorId, int xPos, int yPos){
        this.floor = floorId;
        this.xPos = xPos;
        this.yPos = yPos;
        this.entities = new HashSet<Entity>();
        this.calculateNeighbors();
    }

    public void setFloor(String floorId){
        this.floor = floorId;
    }

    public String getFloor(){
        return this.floor;
    }

    public void setXPos(int next){
        this.xPos = next;
    }

    public int getXPos(){
        return xPos;
    }

    public void setYPos(int next){
        this.yPos = next;
    }

    public int getYPos(){
        return yPos;
    }

    public void addEntity(Entity entity){
       this.entities.add(entity);
    }

    public Set<Entity> getEntities(){
        return this.entities;
    }

    public void removeEntity(Entity entity){
       this.entities.remove(entity);
    }

    public void addNeighbors(Tile neighbor){
        this.neighbors.add(neighbor);
    }

    public void addNeighbors(Tile[] neighbors){
        for (Tile neighbor : neighbors){
            this.neighbors.add(neighbor);
        }
    }

    public Set<Tile> getNeighbors(){
        return neighbors;
    }

    public void calculateNeighbors(){
        this.neighbors = new HashSet<Tile>();
        for (int x=-1; x<2; x++){
            for (int y=-1; y<2; y++){
                try {
                    Tile tile = Model.getInstance().getTile(this.floor, this.xPos+x, this.yPos+y);
                    if (tile != null){
                        this.addNeighbors(tile);
                        tile.addNeighbors(this);
                    }
                } catch (IndexOutOfBoundsException e){

                }
                
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((floor == null) ? 0 : floor.hashCode());
        result = prime * result + xPos;
        result = prime * result + yPos;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (floor == null) {
            if (other.floor != null)
                return false;
        } else if (!floor.equals(other.floor))
            return false;
        if (xPos != other.xPos)
            return false;
        if (yPos != other.yPos)
            return false;
        return true;
    }
}
