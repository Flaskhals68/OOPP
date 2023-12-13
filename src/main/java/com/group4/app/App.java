package com.group4.app;

import java.util.ArrayList;
import java.util.List;

import com.group4.app.controller.ActionController;
import com.group4.app.controller.HudController;
import com.group4.app.controller.InventoryController;
import com.group4.app.controller.StateController;
import com.group4.app.model.Model;
import com.group4.app.view.ActionState;
import com.group4.app.view.AttributePanel;
import com.group4.app.view.GameWindow;
import com.group4.app.view.HudView;
import com.group4.app.view.IGameView;
import com.group4.app.view.InventoryView;
import com.group4.app.view.worldView.BaseGameDisplayArea;

public class App {
    public static void main(String[] args) {
        Model model = Model.getInstance();
        model.addRandomMap(10);

        StateController initalStateController = new StateController(ActionState.IDLE);
        BaseGameDisplayArea displayArea = new BaseGameDisplayArea();


        InventoryController inventoryController = new InventoryController();
        InventoryView inventoryView = new InventoryView(model, inventoryController);
        
        HudController hudController = new HudController();
        IGameView HudView = new HudView(hudController);
        IGameView attrPanel = new AttributePanel(hudController);


        //TODO add the rest of the views.
        List<IGameView> pl = new ArrayList<IGameView>();
        pl.add(displayArea);
        pl.add(attrPanel);
        pl.add(HudView);

        GameWindow gw = GameWindow.getInstance(pl);
        StateController.addObserver(gw);

        model.addObserver(initalStateController);
        model.addObserver(gw);

        model.setController(ActionController.getInstance());

        model.enterGameLoop();
    }
}
