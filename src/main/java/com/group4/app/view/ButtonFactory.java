package com.group4.app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.group4.app.controller.HudController;



public class ButtonFactory {
    public static HudButton createAttackButton(Font font, HudController controller) {
        HudButton attackBtn = new ActionButton("attack", "Attack", font, Color.RED, controller);
        attackBtn.setFont(font);
        attackBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!attackBtn.isEnabled()) return;
                controller.enterAttackState();
                attackBtn.toggle();
            }
        });
        return attackBtn;
    }

    public static HudButton createEndTurnButton(Font font, HudController controller) {
        HudButton endTurnBtn = new ActionButton("endTurn", "End Turn", font, Color.WHITE, controller);
        endTurnBtn.setFont(font);
        endTurnBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!endTurnBtn.isEnabled()) return;
                controller.endTurn();
                endTurnBtn.toggle();
            }
        });
        return endTurnBtn;
    }

}
