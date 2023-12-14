package com.group4.app.model.creatures;

import com.group4.app.model.IModel;
import com.group4.app.model.ITurnTaker;
import com.group4.app.model.Position;
import com.group4.app.model.dungeon.ITileContainer;

public interface ICreatureManager extends IModel {
    void remove(IPositionable e);
    void setDeadTile(Position position);
    void removeFromTurnOrder(ITurnTaker taker);

    void add(IPositionable actionTaker);

    ITileContainer getTileContainer();
}
