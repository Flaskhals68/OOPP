package com.group4.app.model;

import com.group4.app.model.creatures.IPositionable;

public interface ITurnTaker extends IPositionable {
    void takeTurn();
    int getAp();
    void refillAp();
    void useAp(int amount);
}
