package com.group4.app.model.actions;

import java.util.Set;

import com.group4.app.model.Model;

public class RestartAction extends Action {

    public RestartAction(int apCost, String name, Object actionTaker) {
        super(apCost, name, actionTaker);
    }

    @Override
    public void perform(Object target) {
        Model.getInstance().reset();
    }

    @Override
    public Set getTargetable() {
        return null;
    }

    @Override
    public Set getTargetablePositions() {
        return null;
    }
    
}
