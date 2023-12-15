package com.group4.app.model.creatures;

import com.group4.app.model.dungeon.Position;
import com.group4.app.model.turns.ITurnTaker;


public interface ICreatureManager extends IEntityManager {

    void setDeadTile(Position position);
    void removeFromTurnOrder(ITurnTaker taker);
}
