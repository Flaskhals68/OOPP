package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.group4.app.controller.PlayerStatController;
import com.group4.app.model.Model;

public class ApBar extends SubView{
    private PlayerStatController psc;
    private JLabel apLabel;
    private int maxAp;

    public ApBar(Dimension dimension){
        this.setPreferredSize(dimension);
        this.setBackground(null);
        this.setLayout(new FlowLayout());
        intiJLabel(dimension);

    }

    private void intiJLabel(Dimension d){
        this.psc = new PlayerStatController();
        this.maxAp = psc.getPlayerMaxAP();
        this.apLabel = new JLabel(String.valueOf(maxAp));
        this.apLabel.setPreferredSize(d);
        this.apLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.apLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.apLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.apLabel);
        updateValues();
    }

    private void updateValues(){
        int remainingAp = psc.getPlayerAp();
        this.apLabel.setText("AP:" + remainingAp + "/" + maxAp);
    }

    @Override
    public void update() {
        updateValues();
        revalidate();
        repaint();
    }

}