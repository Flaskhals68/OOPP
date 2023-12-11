package com.group4.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.group4.app.model.Model;
import com.group4.app.model.actions.PlayerEndTurnActionInput;
import com.group4.app.model.creatures.AttributeType;
import com.group4.app.view.ActionState;

public class HudController {
    // FIXME: Debugging variables
    private ActionState currentState = ActionState.IDLE;

    public void enterAttackState() {
        System.out.println("Enter attack state");
        currentState = ActionState.ATTACK;
        StateController.setState(currentState);
    }

    public void exitAttackState() { 
        System.out.println("Exit attack state");
        currentState = ActionState.IDLE;
        StateController.setState(currentState);
    }

    public void enterMoveState() {
        System.out.println("Enter move state");
        currentState = ActionState.IDLE;
        StateController.setState(currentState);
    }

    public ActionState getActionState() {
        return StateController.getState();
    }

    public void endTurn() {
        System.out.println("End turn");
        StateController.setState(ActionState.DISABLED);
        ActionController.getInstance().queueAction(new PlayerEndTurnActionInput(Model.getInstance().getPlayer()));
    }

    public List<String> getLegalActions() {
        // TODO: Get legal actions from model
        return Model.getInstance().getAvailableActions();
    }

    public Map<AttributeType, Integer> getAttributes() { 
        return Model.getInstance().getPlayerAttributes(); 
    }
}
