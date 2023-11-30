package com.group4.app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.group4.app.controller.HudController;

public class ActionButton extends HudButton {
    private HudController controller;
    private String actionId;
    private boolean buttonEnabled;
    private Color defaultColor;
    private ActionState currentState = ActionState.IDLE;
    private Map<ActionState, MouseAdapter> stateHandlerMap = new HashMap<>();
    private List<ActionState> disabledStates = new ArrayList<>();

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
        updateState();

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

    public void bindStateHandler(ActionState state,  MouseAdapter handler) {
        stateHandlerMap.put(state, handler);
    }

    public void removeHandler(ActionState state) {
        stateHandlerMap.remove(state);
    }

    public void setState(ActionState state) {
        currentState = state;
    }

    public void registerDisabledState(ActionState state) {
        disabledStates.add(state);
    }

    @Override
    public void updateState() {
        MouseListener[] mouseListners = getMouseListeners();
        if (getMouseListeners().length > 0) {
            removeMouseListener(stateHandlerMap.get(currentState));
        }
        currentState = controller.getActionState();
        addMouseListener(stateHandlerMap.get(currentState));
        MouseAdapter handler = stateHandlerMap.get(currentState);
        mouseListners = getMouseListeners();
        updateButtonState();
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

    private void updateButtonState() {
        List<String> legalActions = controller.getLegalActions();
        if (!disabledStates.contains(currentState) && legalActions.contains(actionId)) {
            this.setEnabled();
        } else {
            this.setDisabled();
        }
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
