package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

import com.group4.app.controller.PlayerStatController;

public class HealthBar extends SubView {
    private JProgressBar bar;
    private PlayerStatController psc;
    private int currentHealth;

    public HealthBar(Dimension dimension, int orient) {
        this.setPreferredSize(dimension);
        this.setBackground(null);
        initBar(dimension, orient);
    }

    private void initBar(Dimension dimension, int orient) {
        this.psc = new PlayerStatController();
        this.bar = new JProgressBar(orient, 0, psc.getPlayerMaxHealth());
        this.bar.setPreferredSize(dimension);
        this.bar.setBounds(0, 0, dimension.width, dimension.height);
        this.bar.setStringPainted(true);
        this.bar.setForeground(Color.RED);
        this.bar.setBorder(null);
        this.bar.setFont(new Font("Arial", Font.BOLD, 16));
        this.currentHealth = psc.getPlayerMaxHealth();
        updateValues();
        add(this.bar);
    }

    private void updateValues() {
        int playerHealth = psc.getPlayerHealth();
        if(this.currentHealth > playerHealth){SoundPlayer.playSound("src\\resources\\547042__eponn__hit-impact-sword-3.wav");}
        this.currentHealth = playerHealth;
        bar.setValue(playerHealth);
        bar.setString("HP: " + playerHealth + "/" + psc.getPlayerMaxHealth()); 
    }

    @Override
    public void update() {
        updateValues();
        revalidate();
        repaint();
    }
}
