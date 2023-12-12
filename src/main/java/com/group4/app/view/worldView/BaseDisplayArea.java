package com.group4.app.view.worldView;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.group4.app.controller.StateController;
import com.group4.app.view.ActionState;
import com.group4.app.view.IGameView;
import com.group4.app.view.SubView;

public class BaseDisplayArea extends JPanel implements IGameView{
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    SubView initial;
    SubView currentGameDisplay;


    public BaseDisplayArea(){
        //Initial drawing
        if(currentGameDisplay != null){remove(currentGameDisplay);}
        initial = new WorldView(ActionState.IDLE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(initial);
        currentGameDisplay = initial;
        updateView();
    }

    public static int getScreenHeight(){
        return HEIGHT;
    }

    public static int getScreenWidth(){
        return WIDTH;
    }


    @Override
    public void updateView() {
        if(StateController.getState() == ActionState.DEAD){
            remove(currentGameDisplay);
            currentGameDisplay = new DeathScreen();
            add(currentGameDisplay);
        }
        currentGameDisplay.update();
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
