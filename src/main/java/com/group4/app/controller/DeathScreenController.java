package com.group4.app.controller;

import com.group4.app.model.Model;
import com.group4.app.model.actions.RestartActionInput;

public class DeathScreenController {

    public void closeGame(){
        System.exit(0);
    }

    public void restartGame(){
        ActionController.getInstance().queueAction(new RestartActionInput(Model.getInstance().getPlayer()));
    }
}
