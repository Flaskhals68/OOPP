package com.group4.app.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.group4.app.controller.HudController;

public abstract class ActionButton extends JButton {
    private HudController controller;

    public ActionButton(String text, Font font, HudController controller) {
        super(text);
        this.controller = controller;
        this.setFont(font);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    public HudController getController() {
        return controller;
    }

    public abstract void updateState();
}
