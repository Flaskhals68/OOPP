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
    private static final int HEIGHT = 75;
    private static final int WIDTH = 775;
    private static final int BTN_WIDTH = 50;
    private static final int BTN_HEIGHT = 30;

    private Model model;
    private HudController controller;
    // private GameWindow gameWindow = GameWindow.getInstance();
    private List<HudButton> btnList = new ArrayList<>();
    private Map<String, HudButton> btnActionMap = new HashMap<>();

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

        createButtons(defaultFont);
        addButtons();
    }

    /**
     * Create buttons without adding them to the JPanel
     * @param font
     */
    private void createButtons(Font font) {
        bindButton(ButtonFactory.createAttackButton(font, controller));
        bindButton(ButtonFactory.createEndTurnButton(font, controller));
    }

    private void bindButton(HudButton btn) {
        btnList.add(btn);
        btnActionMap.put(btn.getActionId(), btn);
    }

    private void addButtons() {
        // TODO: Get legal actions from model
        List<String> legalActions = new ArrayList<>();
        legalActions.add("attack");
        legalActions.add("endTurn");

        for (HudButton btn : btnList) {
            this.add(btn);
            updateButtonState(legalActions, btn);
        }
    }

    private void updateButtonState(List<String> legalActions, HudButton btn) {
        if (legalActions.contains(btn.getActionId())) {
            btn.setEnabled();
        } else {
            btn.setDisabled();
        }
    }

    @Override
    public void updateView() {
        removeAll();
        addButtons();
        revalidate();
        repaint();
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
