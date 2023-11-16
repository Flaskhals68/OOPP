package com.group4.app.model;

import java.util.Objects;

public class Entity implements IDrawable {
    private String id;
    private String floor;
    private int xPos;
    private int yPos;

    public Entity(String id) {
        this.id = id;
    }

    public Entity(String id, String floorId, int xPos, int yPos) {
        this.id = id;
        this.floor = floorId;
        this.xPos = xPos;
        this.yPos = yPos;
        Model.getInstance().addEntity(this, floorId, xPos, yPos);
    }

    public void setPosition(String floorId, int xPos, int yPos) {
        if(this.floor != null) {
            Model.getInstance().removeEntity(this);
        }
        Model.getInstance().addEntity(this, floorId, xPos, yPos);
        this.floor = floorId;
        this.xPos = xPos;
        this.yPos = yPos;
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
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
}
