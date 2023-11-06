package view;

import controller.InventoryController;
import model.Model;

public class InventoryView {
    private Model model;
    private InventoryController controller;

    public InventoryView(Model model, InventoryController controller) {
        this.model = model;
        this.controller = controller;
    }
}