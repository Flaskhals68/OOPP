package com.group4.app.view;

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
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import com.group4.app.controller.HudController;
import com.group4.app.model.Model;
import com.group4.app.model.Position;

public class HudView extends JPanel implements IGameView {
    private static final int HEIGHT = 75;
    private static final int WIDTH = 775;
    private static final int BTN_WIDTH = 50;
    private static final int BTN_HEIGHT = 30;

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
        // Init self
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        btnConstraints.insets = new Insets(5, 5, 5, 5);
        setLayout(new GridBagLayout());
        

        Font defaultFont = new Font("Arial", Font.BOLD, 16);

        createBtns(defaultFont);
        addComponents();
    }

    /**
     * Create buttons without adding them to the JPanel
     * @param font
     */
    private void createBtns(Font font) {
        List<JComponent> btnList = new ArrayList<>();

        // JComponent attackBtn = new JPanel();
        // JLabel attackLabel = new JLabel("Attack");
        // attackLabel.setFont(font);
        // attackBtn.add(attackLabel);
        // btnList.add(attackBtn);
        // attackBtn.setBackground(Color.RED);
        // components.add(attackBtn);

        JComponent attackBtn = new AttackButton("Attack", font, controller);
        btnList.add(attackBtn);
        components.add(attackBtn);

        JComponent endTurnBtn = new JPanel();
        JLabel endTurnLabel = new JLabel("End Turn");
        endTurnLabel.setFont(font);
        endTurnBtn.add(endTurnLabel);
        btnList.add(endTurnBtn);
        endTurnBtn.setBackground(Color.WHITE);
        components.add(endTurnBtn);
    }

    private void addComponents() {
        for (JComponent component : components) {
            this.add(component);
        }
    }

    private void enableComponents() {
        for (JComponent component : components) {
            component.enable();
        }
    }

    @Override
    public void updateView() {
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
