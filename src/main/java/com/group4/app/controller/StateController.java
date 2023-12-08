package com.group4.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.group4.app.model.IModelObserver;
import com.group4.app.model.Model;
import com.group4.app.view.ActionState;
import com.group4.app.view.IStateControllerObserver;

public class StateController implements IModelObserver{
    private static ActionState state;
    private final boolean isPlayerTurn = Model.getInstance().isPlayerTurn();
    private static List<IStateControllerObserver> observers = new ArrayList<>();

    public StateController(ActionState initialState){
        state = initialState; 
    }

    public static ActionState getState(){
        return state;
    }

    public static void setState(ActionState newState){
        if(!Model.getInstance().isPlayerTurn()){
            state = ActionState.DISABLED;
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

    @Override
    public void update() {
        boolean currentPlayerTurn = Model.getInstance().isPlayerTurn();
        if(currentPlayerTurn != isPlayerTurn){
            if(getState() == ActionState.ATTACK){}
            else{
                setState(ActionState.IDLE);
            }
        }
        else{
            setState(ActionState.DISABLED);
        }
    }

}
