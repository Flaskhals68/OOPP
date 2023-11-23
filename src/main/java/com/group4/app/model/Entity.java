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

    public void setPosition(String floorId, Position pos) {
        if(this.floor != null) {
            Model.getInstance().removeEntity(this);
        }
        Model.getInstance().addEntity(this, floorId, pos);
        this.floor = floorId;
        this.pos = pos;
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

    public int getYPos() {
        return pos.getY();
    }

    public Position getPos() {
        return this.pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
