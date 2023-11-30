package com.group4.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.group4.app.controller.HudController;
import com.group4.app.controller.InventoryController;
import com.group4.app.controller.PlayerAttackController;
import com.group4.app.controller.PlayerMovementController;
import com.group4.app.controller.WorldViewPlayerController;
import com.group4.app.model.Model;
import com.group4.app.view.GameWindow;
import com.group4.app.view.HudView;
import com.group4.app.view.IGameView;
import com.group4.app.view.InventoryView;
import com.group4.app.view.WorldView;

public class App {
    public static void main(String[] args) {
        Model model = Model.getInstance();
        model.addBasicMap(100);

        // WorldViewPlayerController worldController = new PlayerMovementController(model);
        WorldViewPlayerController worldController = new PlayerAttackController(model);
        WorldView worldView = new WorldView(worldController);

        InventoryController inventoryController = new InventoryController();
        InventoryView inventoryView = new InventoryView(model, inventoryController);
        
        HudController hudController = new HudController();
        HudView HudView = new HudView(model, hudController);

        //TODO add the rest of the views.
        List<IGameView> pl = new ArrayList<IGameView>();
        pl.add(worldView);

        GameWindow gw = GameWindow.getInstance(pl);

        model.addObserver(gw);
    }
}
