package com.group4.app.model;

public interface ITurnTaker {
    void startTurn();
    void endTurn();
    int getAp();
    void refillAp();
    void useAp(int amount);
}
