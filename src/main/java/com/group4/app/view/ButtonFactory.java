package com.group4.app.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.group4.app.controller.HudController;

public class ButtonFactory {
    public static JComponent createAttackButton(int width, int height, Font font, HudController controller) {
        JComponent attackBtn = new JButton("Attack");
        attackBtn.setPreferredSize(new Dimension(width, height));
        attackBtn.setFont(font);
        attackBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.enterAttackState();
            }
        });
        return attackBtn;
    }
}
