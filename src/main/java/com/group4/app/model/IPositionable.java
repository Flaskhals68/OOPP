package com.group4.app.model;

public interface IPositionable extends IHasPosition {
    void setFloor(String floorId);
    void setXPos(int xPos);
    void setYPos(int yPos);
}
