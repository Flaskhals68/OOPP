package com.group4.app.model;

import java.util.Objects;

public class Entity implements IDrawable {
    private String id;
    private Position pos;

    public Entity(String id) {
        this.id = id;
    }

    public Entity(String id, Position pos) {
        this.id = id;
        this.pos = pos;
        Model.getInstance().add(this, pos);
    }

    public void setPosition(Position pos) {
        if(getFloor() != null) {
            Model.getInstance().remove(this);
        }
        Model.getInstance().add(this, pos);
        this.pos = pos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFloor() {
        return pos.getFloor();
    }

    public void setFloor(String floorId) {
        this.pos = new Position(this.pos.getX(), this.pos.getY(), floorId);
    }

    public Position getPos() {
        return this.pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
