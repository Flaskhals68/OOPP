package com.group4.app.controller;

import com.group4.app.view.ActionState;

public class DeathScreenController {
    public DeathScreenController(){
        StateController.setState(ActionState.DISABLED);
    }

    public void closeGame(){
        System.exit(0);
    }

    public void restartGame(){
    }
}
