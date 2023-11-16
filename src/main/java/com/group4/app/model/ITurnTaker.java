package com.group4.app.model;

public interface ITurnTaker {
    void startTurn();
    int getAp();
    void refillAp();
    void useAp(int amount);
}
