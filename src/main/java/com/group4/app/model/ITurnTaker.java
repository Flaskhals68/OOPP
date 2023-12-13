package com.group4.app.model;

public interface ITurnTaker{
    void takeTurn();
    int getAp();
    void refillAp();
    void useAp(int amount);
}
