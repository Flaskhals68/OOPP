package com.group4.app.model.dungeon;

public interface IPositionable extends IHasPosition {
    void setFloor(String floorId);
    void setPos(Position pos);
}
