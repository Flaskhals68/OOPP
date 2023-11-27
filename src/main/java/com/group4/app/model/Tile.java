package com.group4.app.model;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Tile implements IDrawable {
    private String id;
    private String floor;
    private Set<Tile> neighbors;
    private Position pos;
    private Set<Entity> entities;

    public Tile(String id, String floorId, Position pos){
        this.id = id;
        this.floor = floorId;
        this.pos = pos;
        this.entities = new HashSet<Entity>();
        this.calculateNeighbors();
    }

    public String getId(){
        return this.id;
    }

    public void setFloor(String floorId){
        this.floor = floorId;
    }

    public String getFloor(){
        return this.floor;
    }

    public int getXPos(){
        return pos.getX();
    }

    public int getYPos(){
        return pos.getY();
    }

    public void setPos(Position pos){
        this.pos = pos;
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
                    // Tile tile = Model.getInstance().getTile(this.floor, this.xPos+x, this.yPos+y);
                    Tile tile = Model.getInstance().getTile(floor, new Position(getXPos()+x, getYPos()+y, getFloor()));
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
        result = prime * result + getXPos();
        result = prime * result + getXPos();
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
        if (getXPos() != other.getXPos())
            return false;
        if (getYPos() != other.getYPos())
            return false;
        return true;
    }
}
