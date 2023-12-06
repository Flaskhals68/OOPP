package com.group4.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.group4.app.controller.HudController;
import com.group4.app.controller.InventoryController;
import com.group4.app.controller.worldControllers.AWorldController;
import com.group4.app.controller.worldControllers.PlayerMovementController;
import com.group4.app.controller.worldControllers.PlayerViewAttackController;
import com.group4.app.model.Model;
import com.group4.app.model.actions.PlayerAttackAction;
import com.group4.app.view.AttributePanel;
import com.group4.app.view.GameWindow;
import com.group4.app.view.HudView;
import com.group4.app.view.IGameView;
import com.group4.app.view.InventoryView;
import com.group4.app.view.worldView.WorldView;

public class App {
    public static void main(String[] args) {
        Model model = Model.getInstance();
        model.addBasicMap(100);

        AWorldController worldController = new PlayerMovementController();
        WorldView worldView = new WorldView(worldController.getState());

        InventoryController inventoryController = new InventoryController();
        InventoryView inventoryView = new InventoryView(model, inventoryController);
        
        HudController hudController = new HudController();
        IGameView HudView = new HudView(model, hudController);
        IGameView attrPanel = new AttributePanel(hudController);


        //TODO add the rest of the views.
        List<IGameView> pl = new ArrayList<IGameView>();
        pl.add(worldView);
        pl.add(attrPanel);
        pl.add(HudView);

        GameWindow gw = GameWindow.getInstance(pl);

        model.addObserver(gw);
    }
}
