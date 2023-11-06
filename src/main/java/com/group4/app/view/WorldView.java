package src.main.java.com.group4.app.view;

import src.main.java.com.group4.app.controller.WorldController;
import src.main.java.com.group4.app.model.Model;

public class WorldView {
    private Model model;
    private WorldController controller;
    
    public WorldView(Model model, WorldController controller) {
        this.model = model;
        this.controller = controller;
    }
}
