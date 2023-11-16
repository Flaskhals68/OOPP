package com.group4.app.view;

import javax.swing.JPanel;

import com.group4.app.controller.InventoryController;
import com.group4.app.model.Model;

public class InventoryView extends JPanel{
    private Model model;
    private InventoryController controller;

    public InventoryView(Model model, InventoryController controller) {
        this.model = model;
        this.controller = controller;
    }
}