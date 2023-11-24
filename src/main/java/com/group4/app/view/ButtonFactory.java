package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.group4.app.controller.HudController;
import com.group4.app.view.ActionButton;
import com.group4.app.view.ActionButton;



public class ButtonFactory {
    public static HudButton createAttackButton(Font font, HudController controller) {
        HudButton attackBtn = new ActionButton("attack", "Attack", font, controller);
        attackBtn.setBackground(Color.RED);
        // attackBtn.setPreferredSize(new Dimension(width, height));
        attackBtn.setFont(font);
        attackBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.enterAttackState();
                attackBtn.toggle();
            }
        });
        return attackBtn;
    }

    public static HudButton createEndTurnButton(Font font, HudController controller) {
        HudButton endTurnBtn = new ActionButton("endTurn", "End Turn", font, controller);
        endTurnBtn.setBackground(Color.LIGHT_GRAY);
        // endTurnBtn.setPreferredSize(new Dimension(width, height));
        endTurnBtn.setFont(font);
        endTurnBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.endTurn();
                endTurnBtn.toggle();
            }
        });
        return endTurnBtn;
    }

}
