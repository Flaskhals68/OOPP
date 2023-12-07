package com.group4.app.model.creatures;

import com.group4.app.model.IHasPosition;
import com.group4.app.model.Position;

public interface IPositionable extends IHasPosition {
    void setFloor(String floorId);
    void setPos(Position pos);
}
