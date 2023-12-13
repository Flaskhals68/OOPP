package com.group4.app.model.creatures;

import com.group4.app.model.ITurnTaker;
import com.group4.app.model.Position;

public interface IEntityManager {
    void remove(Entity e);
    void setDeadTile(Position position);
    void removeFromTurnOrder(ITurnTaker taker);
    void giveExperience(int exp);
}
