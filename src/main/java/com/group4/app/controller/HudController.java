package com.group4.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.group4.app.model.AttributeType;
import com.group4.app.model.Model;
import com.group4.app.view.ActionState;

public class HudController {
    // FIXME: Debugging variables
    private ActionState currentState = ActionState.IDLE;

    // public boolean attackAllowed() {
    //     // TODO: Implement logic to check if attack is allowed
    //     System.out.println("Attack allowed : " + attackAllowed);
    //     return attackAllowed;
    // }

    public void enterAttackState() {
        // TODO: Implement logic to enter attack state
        System.out.println("Enter attack state");
        currentState = ActionState.ATTACK;
    }

    public void exitAttackState() { 
        System.out.println("Exit attack state");
        currentState = ActionState.IDLE;
    }

    public ActionState getActionState() {
        return currentState;
    }

    public void endTurn() {
        System.out.println("End turn");
        Model.getInstance().endTurn();
    }

    public List<String> getLegalActions() {
        // TODO: Get legal actions from model
        List<String> legalActions = new ArrayList<>();
        legalActions.add("attack");
        legalActions.add("endTurn");
        return legalActions;
    }

    public Map<AttributeType, Integer> getAttributes() { 
        return Model.getInstance().getPlayerAttributes(); 
    }
}
