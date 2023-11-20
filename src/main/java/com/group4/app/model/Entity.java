package com.group4.app.model;

import java.util.Objects;

public class Entity implements IDrawable {
    private String id;
    private String floor;
    private Position pos;

    public Entity(String id) {
        this.id = id;
    }

    public Entity(String id, String floorId, Position pos) {
        this.id = id;
        this.floor = floorId;
        this.pos = pos;
        Model.getInstance().addEntity(this, floorId, pos);
    }

    public void setPosition(String floorId, int xPos, int yPos) {
        if(this.floor != null) {
            Model.getInstance().removeEntity(this);
        }
        Model.getInstance().addEntity(this, floorId, pos);
        this.floor = floorId;
        this.pos = new Position(xPos, yPos);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floorId) {
        this.floor = floorId;
    }

    public int getXPos() {
        return pos.getX();
    }

    public void setXPos(int xPos) {
        this.pos = new Position(xPos, pos.getY());
    }

    public int getYPos() {
        return pos.getY();
    }

    public void setYPos(int yPos) {
        this.pos = new Position(pos.getX(), yPos);
    }

    public Position getPos() {
        return this.pos;
    }
}
