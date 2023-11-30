package com.group4.app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;

import com.group4.app.controller.HudController;



public class ButtonFactory {
    public static HudButton createAttackButton(Font font, HudController controller) {
        ActionButton attackBtn = new ActionButton("attack", "Attack", font, Color.RED, controller);
        attackBtn.registerDisabledState(ActionState.DISABLED);
        attackBtn.bindStateHandler(ActionState.IDLE, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!attackBtn.isEnabled()) return;
                controller.enterAttackState();
            }
        });

        attackBtn.bindStateHandler(ActionState.ATTACK, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!attackBtn.isEnabled()) return;
                controller.exitAttackState();
            }
        });
       
        return attackBtn;
    }

    public static HudButton createEndTurnButton(Font font, HudController controller) {
        ActionButton endTurnBtn = new ActionButton("endTurn", "End Turn", font, Color.WHITE, controller);
        endTurnBtn.registerDisabledState(ActionState.DISABLED);
        endTurnBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!endTurnBtn.isEnabled()) return;
                controller.endTurn();
            }
        });
        return endTurnBtn;
    }

}
