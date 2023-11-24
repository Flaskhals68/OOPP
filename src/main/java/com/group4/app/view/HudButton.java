package com.group4.app.view;

import javax.swing.JButton;

/**
 * Abstract class for all buttons in the HUD
 */
public abstract class HudButton extends JButton {
    public HudButton(String text) {
        super(text);
    }

    public abstract void setDisabled();
    public abstract void setEnabled();
    public abstract void toggle();
    public abstract String getActionId();
}
