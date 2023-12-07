package com.group4.app.controller;

import com.group4.app.view.ActionState;

public class StateController {
    private static ActionState state;

    public StateController(ActionState initialState){
        state = initialState; 
    }

    public static ActionState getState(){
        return state;
    }

    public static void setState(ActionState newState){
        state = newState;
    }
}
