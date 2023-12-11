package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.group4.app.controller.HudController;

public class ButtonPanel extends SubView {
    private static final int BTN_WIDTH = 50;
    private static final int BTN_HEIGHT = 30;

    private GridBagConstraints constraints = new GridBagConstraints();
    private HudController controller;
    private List<HudButton> btnList = new ArrayList<>();
    private Map<String, HudButton> btnActionMap = new HashMap<>();
    private Font defaultFont = new Font("Arial", Font.BOLD, 16);
    private int width;
    private int height;

    public ButtonPanel(int width, int height, HudController controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;

        setPreferredSize(new Dimension(this.width, this.height));
        setBackground(Color.lightGray);

        initComponents();
    }

    private void initComponents() {
        bindButtons();
        addButtons();
    }

    /**
     * Create buttons without adding them to the JPanel
     * @param font
     */
    private void bindButtons() {
        bindButton(ButtonFactory.createMoveButton(defaultFont, controller));
        bindButton(ButtonFactory.createAttackButton(defaultFont, controller));
        bindButton(ButtonFactory.createEndTurnButton(defaultFont, controller));
    }

    private void bindButton(HudButton btn) {
        btnList.add(btn);
        btnActionMap.put(btn.getActionId(), btn);
    }

    private void addButtons() {
        for (HudButton btn : btnList) {
            add(btn, constraints);
            // updateButtonState(controller.getLegalActions(), btn);
            btn.updateState();
        }
    }

    private void updateButtons() {
        for (HudButton btn : btnList) {
            btn.updateState();
        }
    }

    public void update() {
        updateButtons();
        revalidate();
        repaint();
    }
}
