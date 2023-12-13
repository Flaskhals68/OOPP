package com.group4.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.group4.app.controller.CreatureStatController;
import com.group4.app.model.dungeon.Position;

/**
 * A health bar for a creature at the given position.
 */
public class EnemyHealthBar extends JPanel {
    private CreatureStatController csc = new CreatureStatController();
    private JProgressBar bar;

    public EnemyHealthBar(Position creaturePosition, Dimension size) {
        this.setBounds(0, 0, size.width, size.height);
        this.setPreferredSize(size);
        this.setLayout(new BorderLayout());
        JProgressBar bar = initBar(creaturePosition, size);
        this.add(bar, BorderLayout.NORTH);
    }

    private JProgressBar initBar(Position creaturePosition, Dimension size) {
        int maxHealth = csc.getCreatureMaxHealth(creaturePosition);
        int currentHealth = csc.getCreatureHealth(creaturePosition);
        this.bar = new JProgressBar(0, maxHealth);
        
        bar.setBounds(0, 0, size.width, size.height);
        bar.setPreferredSize(size);
        bar.setForeground(Color.RED);
        bar.setValue(currentHealth);
        return bar;
    }
}
