package com.group4.app.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.group4.app.controller.HudController;

public class ActionButton extends HudButton {
    private HudController controller;
    private String actionId;
    private boolean isEnabled;
    private Color defaultColor;

    public ActionButton(String actionId, String text, Font font, Color color, HudController controller) {
        super(text);
        this.actionId = actionId;
        this.controller = controller;
        this.setFont(font);
        this.defaultColor = color;
        this.setBackground(defaultColor);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.isEnabled = true;

        this.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                if (isEnabled) {
                    setBackground(defaultColor);
                } else {
                    setBackground(Color.GRAY);
                }
            }
        });
    }

    @Override
    public String getActionId() {
        return actionId;
    }

    public HudController getController() {
        return controller;
    }

    @Override
    public void setDisabled() {
        this.setEnabled(false);
        this.setBackground(Color.GRAY);
    }

    @Override
    public void setEnabled() {
        this.setEnabled(true);
    }

    @Override
    public void toggle() {
        if (this.isEnabled) {
            this.setDisabled();
        } else {
            this.setEnabled();
        }
    }
}
