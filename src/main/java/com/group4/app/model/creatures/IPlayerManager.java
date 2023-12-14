package com.group4.app.model.creatures;

import com.group4.app.model.actions.ActionInput;

public interface IPlayerManager extends ICreatureManager {
    void setPlayerDied();
    ActionInput<?> getActionInput();
    void endPlayerTurn();
    void startPlayerTurn();
}
