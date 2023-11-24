package com.group4.app.view;

import java.awt.Color;
import java.awt.Font;

import com.group4.app.controller.HudController;

public class ActionButton extends HudButton {
    private HudController controller;
    private String actionId;
    private boolean isEnabled;

    public ActionButton(String actionId, String text, Font font, HudController controller) {
        super(text);
        this.actionId = actionId;
        this.controller = controller;
        this.setFont(font);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.isEnabled = true;
    }

    public String getActionId() {
        return actionId;
    }

    public HudController getController() {
        return controller;
    }

    public void setDisabled() {
        this.setEnabled(false);
        this.setBackground(Color.GRAY);
    }

    public void setEnabled() {
        this.setEnabled(true);
    }

    public void toggle() {
        if (this.isEnabled) {
            this.setDisabled();
        } else {
            this.setEnabled();
        }
    }
}
