package com.group4.app.model;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Tile implements IDrawable {
    private String id;
    private Set<Tile> neighbors;
    private Position pos;
    private Set<IPositionable> entities;

    public Tile(String id, Position pos){
        this.id = id;
        this.pos = pos;
        this.entities = new HashSet<IPositionable>();
        this.calculateNeighbors();
    }

    public String getId(){
        return this.id;
    }

    public void setFloor(String floorId){
        this.pos = new Position(this.pos.getX(), this.pos.getY(), floorId);
    }

    public String getFloor(){
        return this.pos.getFloor();
    }

    public Position getPos() {
        return new Position(pos.getX(), pos.getY(), pos.getFloor());
    }

    public void setPos(Position pos){
        this.pos = pos;
    }

    public void add(IPositionable positionable){
       this.entities.add(positionable);
    }

    public Set<IPositionable> getEntities(){
        return this.entities;
    }

    public void remove(IPositionable positionable){
       this.entities.remove(positionable);
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
                    Tile tile = Model.getInstance().getTile(new Position(getPos().getX()+x, getPos().getY()+y, getFloor()));
                    if (tile != null){
                        this.addNeighbors(tile);
                        tile.addNeighbors(this);
                    }
                } catch (IndexOutOfBoundsException e){

                }
                
            }
        }
    }

    public boolean isEmpty() {
        return this.entities.isEmpty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFloor() == null) ? 0 : getFloor().hashCode());
        result = prime * result + getPos().getX();
        result = prime * result + getPos().getY();
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
        if (getFloor() == null) {
            if (other.getFloor() != null)
                return false;
        } else if (!getFloor().equals(other.getFloor()))
            return false;
        if (getPos().getX() != other.getPos().getX())
            return false;
        if (getPos().getY() != other.getPos().getY())
            return false;
        return true;
    }
}
