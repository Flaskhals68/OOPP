package com.group4.app.view;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.group4.app.controller.StateController;
import com.group4.app.view.ActionState;
import com.group4.app.view.IGameView;
import com.group4.app.view.SubView;
import com.group4.app.view.worldView.WorldView;

public class MainWindow extends JPanel implements IGameView{
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    private SubView defaultView;

    private Map<ActionState, SubView> stateToViewMap = new HashMap<>();

    public MainWindow(SubView defaultView) {
        this.defaultView = defaultView;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(this.defaultView);
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
        SubView currentView = stateToViewMap.get(StateController.getState());
        if (currentView != null) {
            remove(defaultView);
            add(currentView);
        }
        else {
            removeAll();
            add(defaultView);
            currentView = defaultView;
        }
        currentView.update();
    }

    @Override
    public JPanel getView() {
        return this;
    }

    public void registerState(ActionState state, SubView view) {
        stateToViewMap.put(state, view);
    }
}