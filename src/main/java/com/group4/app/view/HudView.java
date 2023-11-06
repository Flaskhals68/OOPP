package src.main.java.com.group4.app.view;

import src.main.java.com.group4.app.controller.HudController;
import src.main.java.com.group4.app.model.Model;

public class HudView {
    private Model model;
    private HudController controller;

    public HudView(Model model, HudController controller) {
        this.model = model;
        this.controller = controller;
    }
}
