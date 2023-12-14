package com.group4.app.model.creatures;

import com.group4.app.model.Position;

public interface IEnemyManager extends ICreatureManager {
    void giveExperienceToPlayer(int exp);
    Position getPlayerPos();
    int getPlayerStealthBonus();
    Player getPlayer();
    boolean nextToPlayer(Position pos);
}
