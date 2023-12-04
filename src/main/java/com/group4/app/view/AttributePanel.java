package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.group4.app.controller.HudController;
import com.group4.app.model.AttributeType;

public class AttributePanel extends JPanel implements IGameView {
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
        setPreferredSize(new Dimension(100, 250));
        
        setLayout(new GridLayout(7, 1));
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
        JLabel header = new JLabel("Attributes");
        int largeFontSize = defaultFont.getSize() + 4;
        String fontFamily = defaultFont.getFamily();
        Font largeFont = new Font(fontFamily, Font.BOLD, largeFontSize);
        header.setFont(largeFont);
        header.setHorizontalAlignment(SwingConstants.RIGHT);
        add(header, constraints);

        Map<AttributeType, Integer> attributes = controller.getAttributes();
        for (AttributeType attribute : attributes.keySet()) {
            JLabel label = labelMap.get(attribute);
            label.setText(attribute.toString() + ": " + attributes.get(attribute));
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            add(label, constraints);
        }
     }

    @Override
    public void updateView() { 
        removeAll();
        addLabels();
        revalidate();
        repaint();
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
