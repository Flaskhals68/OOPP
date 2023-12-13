package com.group4.app.view.worldView;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.group4.app.controller.StateController;
import com.group4.app.view.ActionState;
import com.group4.app.view.IGameView;
import com.group4.app.view.SubView;

public class BaseGameDisplayArea extends JPanel implements IGameView{
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    private SubView initialGameDisplay;
    private SubView currentGameDisplay;

    private Map<ActionState, SubView> stateToViewMap = new HashMap<>();

    public BaseGameDisplayArea(){
        //Used when restarting and there already exists a component here
        //TODO might need to implement a restart method instead. 
        if(this.currentGameDisplay != null){remove(this.currentGameDisplay);}
        this.initialGameDisplay = new WorldView(ActionState.IDLE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(this.initialGameDisplay);
        this.currentGameDisplay = this.initialGameDisplay;
        updateView();
    }

    public static int getScreenHeight(){
        return HEIGHT;
    }

    public static int getScreenWidth(){
        return WIDTH;
    }

    public void register(ActionState state, SubView view){
        this.stateToViewMap.put(state, view);
    }

    @Override
    public void updateView() {
        removeAll();
        if(stateToViewMap.get(StateController.getState()) != null){
            this.currentGameDisplay = stateToViewMap.get(StateController.getState());
            add(currentGameDisplay);
            this.currentGameDisplay.update();
        }
        else{
            add(initialGameDisplay);
            initialGameDisplay.update();
        }
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
