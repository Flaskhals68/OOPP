package com.group4.app.model;

public class AttackActionInput extends ActionInput<IAttackable> {
    public AttackActionInput(String actionId, IAttackable target) {
        super(actionId, target);
    }

    public IAttackable getTarget() {
        return super.getTarget();
    }

    public String getActionId() {
        return super.getActionId();
    }
}
