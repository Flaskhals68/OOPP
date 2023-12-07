package com.group4.app.model.actions;

import com.group4.app.model.Position;

public class PositionActionInput extends ActionInput<Position>{
    public PositionActionInput(String actionId, Position target) {
        super(actionId, target);
    }

    public Position getTarget() {
        return super.getTarget();
    }

    public String getActionId() {
        return super.getActionId();
    }
}
