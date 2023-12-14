package com.group4.app.view;

import javax.swing.*;

import com.group4.app.controller.InventoryController;
import com.group4.app.model.Model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class InventoryView extends SidePanel implements IGameView{
    private Model model;
    private InventoryController controller;

    public InventoryView(Model model, InventoryController controller) {
        this.model = model;
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(new Dimension(getWidth(), getHeight()));
        initInventory();
    }

    private void initInventory() {
        removeAll();
        Map<String, Integer> inventory = model.getPlayer().getInventoryItems();
        JPanel invTitle = new JPanel();
        invTitle.setPreferredSize(new Dimension(100, 25));
        invTitle.add(new JLabel("Inventory"));
        add(invTitle);

        for(Map.Entry<String, Integer> entry : inventory.entrySet()) {
            JPanel jPan = new JPanel();
            jPan.setPreferredSize(new Dimension(100, 25));
            jPan.add(new JLabel(entry.getKey() + ": " + entry.getValue()));
            jPan.setBackground(Color.GRAY);
            jPan.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            jPan.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SoundPlayer.playSound("src\\resources\\166188__drminky__potion-drink-regen.wav", false);
                    controller.useItem(entry.getKey());
                    initInventory();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            add(jPan);
        }

        revalidate();
        repaint();
    }

    @Override
    public void updateView() {
        initInventory();
    }

    @Override
    public JPanel getView() {
        return this;
    }
}