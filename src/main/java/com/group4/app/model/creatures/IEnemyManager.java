package com.group4.app.model.creatures;

import com.group4.app.model.Position;

public interface IEnemyManager extends ICreatureManager {
    void giveExperience(int exp);
    Position getPlayerPos();
    int getPlayerStealthBonus();
}
