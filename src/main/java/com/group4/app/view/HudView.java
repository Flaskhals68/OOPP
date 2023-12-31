package com.group4.app.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import com.group4.app.controller.HudController;
import com.group4.app.controller.PlayerStatController;
import com.group4.app.model.Model;

public class HudView extends JPanel implements IGameView{
    private static final int HEIGHT = 150;
    private static final int WIDTH = 775;
    private static final int BTN_HEIGHT = 30;

    private HudController controller;
    private GridBagConstraints constraints = new GridBagConstraints();

    private List<SubView> subViews = new ArrayList<>();

    public HudView(HudController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        HealthBar healthBar = new HealthBar(new Dimension(300, 30), 0);
        ApBar apBar = new ApBar(new Dimension(100,30));
        bindSubView(healthBar, 0,0);
        bindSubView(new ButtonPanel(775, BTN_HEIGHT, controller), 1,0);
        bindSubView(apBar,2,0);
    }

    public void bindSubView(SubView subView, int gridy, int gridx) {
        constraints.gridy = gridy;
        constraints.gridx = gridx;
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
