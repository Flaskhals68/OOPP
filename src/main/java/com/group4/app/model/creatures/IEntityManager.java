package com.group4.app.model.creatures;

import com.group4.app.model.dungeon.IPositionable;
import com.group4.app.model.dungeon.ITileContainer;

public interface IEntityManager {
    void remove(IPositionable e);
    void add(IPositionable actionTaker);

    ITileContainer getTileContainer();
}
