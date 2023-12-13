package com.group4.app.controller;

import com.group4.app.model.Model;
import com.group4.app.model.actions.Action;
import com.group4.app.model.actions.RestartAction;
import com.group4.app.model.actions.RestartActionInput;
import com.group4.app.view.ActionState;

public class DeathScreenController {

    public void closeGame(){
        System.exit(0);
    }

    public void restartGame(){
        ActionController.getInstance().queueAction(new RestartActionInput(Model.getInstance().getPlayer()));
    }
}
