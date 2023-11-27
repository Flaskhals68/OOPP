package com.group4.app.view;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.group4.app.controller.HudController;

public class ActionButton extends HudButton {
    private HudController controller;
    private String actionId;
    private boolean buttonEnabled;
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
        this.buttonEnabled = true;

        this.addPropertyChangeListener("buttonEnabled", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if (buttonEnabled) {
                    setBackground(defaultColor);
                } else {
                    setBackground(Color.GRAY);
                }
            }
        });
    }

    public void setButtonEnabled(boolean enabled) {
        boolean oldEnabled = this.buttonEnabled;
        this.buttonEnabled = enabled;
        this.firePropertyChange("buttonEnabled", oldEnabled, enabled);
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
        this.setButtonEnabled(false);
    }

    @Override
    public void setEnabled() {
        this.setEnabled(true);
        this.setBackground(defaultColor);
        this.setButtonEnabled(true);
    }

    @Override
    public void toggle() {
        if (this.buttonEnabled) {
            this.setDisabled();
        } else {
            this.setEnabled();
        }
    }
}
