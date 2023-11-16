package com.group4.app.view;

import javax.swing.JPanel;

import com.group4.app.controller.HudController;
import com.group4.app.model.Model;

public class HudView extends JPanel{
    private Model model;
    private HudController controller;

    public HudView(Model model, HudController controller) {
        this.model = model;
        this.controller = controller;
    }
}
