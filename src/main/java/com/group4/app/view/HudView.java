package com.group4.app.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import com.group4.app.controller.HudController;
import com.group4.app.model.Model;
import com.group4.app.model.Position;

public class HudView extends JPanel implements IGameView {
    private static final int HEIGHT = 75;
    private static final int WIDTH = 800;
    private static final int BTN_WIDTH = 50;
    private static final int BTN_HEIGHT = 75;

    private Model model;
    private HudController controller;
    // private GameWindow gameWindow = GameWindow.getInstance();
    private List<JComponent> components = new ArrayList<>();

    private GridBagConstraints btnConstraints = new GridBagConstraints();

    public HudView(Model model, HudController controller) {
        this.model = model;
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        setBackground(Color.GRAY);
        setLayout(new GridBagLayout());
        // this.btnConstraints.fill = GridBagConstraints.HORIZONTAL;
        
        JComponent attackBtn = new JPanel();
        components.add(attackBtn);
        attackBtn.setVisible(true);
        this.add(attackBtn);
    }

    private void enableComponents() {
        for (JComponent component : components) {
            component.enable();
        }
    }

    @Override
    public void updateView() {
        // TODO Auto-generated method stub
        removeAll();
        enableComponents();
        revalidate();
        repaint();
        
    }

    @Override
    public JPanel getView() {
        return this;
    }


}
