package com.group4.app.model.actions;

import com.group4.app.model.creatures.Creature;

public class RestartActionInput extends ActionInput<Creature> {

    public RestartActionInput(Creature target) {
        super("restart", target);
    }
    
    public String getActionId() {
        return super.getActionId();
    }
}
