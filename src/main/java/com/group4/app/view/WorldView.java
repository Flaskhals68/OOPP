package com.group4.app.view;

import com.group4.app.controller.WorldController;
import com.group4.app.model.Model;

public class WorldView {
    private Model model;
    private WorldController controller;
    
    public WorldView(Model model, WorldController controller) {
        this.model = model;
        this.controller = controller;
    }
}
