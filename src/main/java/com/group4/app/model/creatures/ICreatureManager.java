package com.group4.app.model.creatures;

import com.group4.app.model.IModel;
import com.group4.app.model.dungeon.IPositionable;
import com.group4.app.model.dungeon.ITileContainer;
import com.group4.app.model.dungeon.Position;
import com.group4.app.model.turns.ITurnTaker;


public interface ICreatureManager extends IModel {
    void remove(IPositionable e);
    void setDeadTile(Position position);
    void removeFromTurnOrder(ITurnTaker taker);

    void add(IPositionable actionTaker);

    ITileContainer getTileContainer();
}
