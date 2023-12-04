package com.group4.app.view;

import java.awt.event.MouseAdapter;

import javax.swing.JButton;

/**
 * Abstract class for all buttons in the HUD
 */
public abstract class HudButton extends JButton {
    
    public HudButton(String text) {
        super(text);
    }

    public abstract void bindStateHandler(ActionState state,  MouseAdapter handler);
    public abstract void removeHandler(ActionState state);
    public abstract void setState(ActionState state);
    public abstract void setDisabled();
    public abstract void setEnabled();
    public abstract void toggle();
    public abstract String getActionId();
    public abstract void updateState();
}
