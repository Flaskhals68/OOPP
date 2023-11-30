package com.group4.app.view;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group4.app.controller.HudController;
import com.group4.app.model.Model;

public class HudView extends JPanel implements IGameView {
    private static final int HEIGHT = 200;
    private static final int WIDTH = 775;
    private static final int BTN_WIDTH = 50;
    private static final int BTN_HEIGHT = 30;

    private Model model;
    private HudController controller;
    private GridBagConstraints constraints = new GridBagConstraints();

    private List<SubView> subViews = new ArrayList<>();

    public HudView(Model model, HudController controller) {
        this.model = model;
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        
        bindSubView(new ButtonPanel(775, BTN_HEIGHT, controller), 0);
        bindSubView(new AttributePanel(controller), 1);
    }

    public void bindSubView(SubView subView, int gridy) {
        constraints.gridy = gridy;
        subViews.add(subView);
        add(subView, constraints);
    }

    @Override
    public void updateView() {
        for (SubView subView : subViews) {
            subView.update();
        }
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
