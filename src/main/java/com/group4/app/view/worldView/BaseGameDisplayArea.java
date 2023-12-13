package com.group4.app.view.worldView;

import java.awt.Dimension;

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


    @Override
    public void updateView() {
        if(StateController.getState() == ActionState.DEAD){
            remove(this.currentGameDisplay);
            this.currentGameDisplay = new DeathScreen();
            add(this.currentGameDisplay);
        }
        this.currentGameDisplay.update();
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
