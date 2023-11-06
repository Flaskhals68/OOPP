package src.main.java.com.group4.app.view;

import src.main.java.com.group4.app.controller.InventoryController;
import src.main.java.com.group4.app.model.Model;

public class InventoryView {
    private Model model;
    private InventoryController controller;

    public InventoryView(Model model, InventoryController controller) {
        this.model = model;
        this.controller = controller;
    }
}