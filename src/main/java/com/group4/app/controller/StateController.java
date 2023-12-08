package com.group4.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.group4.app.model.Model;
import com.group4.app.view.ActionState;
import com.group4.app.view.IStateControllerObserver;

public class StateController {
    private static ActionState state;
    private static List<IStateControllerObserver> observers = new ArrayList<>();

    public StateController(ActionState initialState){
        state = initialState; 
    }

    public static ActionState getState(){
        return state;
    }

    public static void setState(ActionState newState){
        if(!Model.getInstance().isPlayerTurn()){
            state = ActionState.IDLE;
        }
        else{
            state = newState;
            for(IStateControllerObserver isco : observers){
                isco.updateState();
            }
        }
    }

    public static void addObserver(IStateControllerObserver obs){
        observers.add(obs);
    }

}
