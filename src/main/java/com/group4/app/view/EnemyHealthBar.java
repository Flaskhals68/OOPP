package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.group4.app.controller.CreatureStatController;
import com.group4.app.model.Position;

public class EnemyHealthBar extends JPanel {
    private CreatureStatController csc = new CreatureStatController();
    private JProgressBar bar;

    public EnemyHealthBar(Position creaturePosition, Dimension size) {
        this.setPreferredSize(size);
        this.setBackground(Color.RED);
        initBar(creaturePosition, size);
    }

    private void initBar(Position creaturePosition, Dimension size) {
        int maxHealth = csc.getCreatureMaxHealth(creaturePosition);
        int currentHealth = csc.getCreatureHealth(creaturePosition);
        this.bar = new JProgressBar(0, maxHealth);
        bar.setPreferredSize(size);
        bar.setBackground(Color.RED);
        bar.setValue(currentHealth);
        bar.setString("{currentHealth}/{maxHealth}");
        bar.setStringPainted(true);
        this.add(bar);
    }
}
