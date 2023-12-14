package com.group4.app.model.creatures;


import com.group4.app.model.input.ActionInput;

public interface IPlayerManager extends ICreatureManager {
    void setPlayerDied();
    ActionInput<?> getActionInput();
    void endPlayerTurn();
    void startPlayerTurn();
}
