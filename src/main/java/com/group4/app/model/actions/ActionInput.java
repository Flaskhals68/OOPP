package com.group4.app.model.actions;

public class ActionInput<T> {
    private String actionId;
    private T target;

    public ActionInput(String actionId, T target) {
        this.actionId = actionId;
        this.target = target;
    }

    public String getActionId() {
        return actionId;
    }

    public T getTarget() {
        return target;
    }
}
