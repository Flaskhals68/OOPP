package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.group4.app.controller.HudController;
import com.group4.app.model.AttributeType;

public class AttributePanel extends SubView {
    private HudController controller;
    private Map<AttributeType, JLabel> labelMap = new HashMap<>();
    private List<JLabel> labels = new ArrayList<>();
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font defaultFont = new Font("Arial", Font.BOLD, 16);

    public AttributePanel(HudController controller) { 
        this.controller = controller;
        initComponents();
    }
    
    private void initComponents() {
        setPreferredSize(new Dimension(300, 100));
        setLayout(new GridLayout(2, 3));
        setBackground(Color.WHITE);
        bindStats();
        addLabels();
    }

    private void bindStats() {
        Map<AttributeType, Integer> attributes = controller.getAttributes();
        for (AttributeType attribute : attributes.keySet()) {
            JLabel label = new JLabel();
            label.setFont(defaultFont);
            labelMap.put(attribute, label);
            labels.add(label);
        }
    }

    private void addLabels() {
        Map<AttributeType, Integer> attributes = controller.getAttributes();
        for (AttributeType attribute : attributes.keySet()) {
            JLabel label = labelMap.get(attribute);
            label.setText(attribute.toString() + ": " + attributes.get(attribute));
            add(label);
        }
     }

    @Override
    public void update() { 
        removeAll();
        addLabels();
        revalidate();
        repaint();
    }
}
