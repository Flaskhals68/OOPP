package com.group4.app.view.worldView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;


import com.group4.app.controller.DeathScreenController;
import com.group4.app.view.SubView;

public class DeathScreen extends SubView {

    private DeathScreenController dsc;
    
    public DeathScreen(){
        this.dsc = new DeathScreenController();
        setPreferredSize(new Dimension(BaseDisplayArea.getScreenWidth(), BaseDisplayArea.getScreenHeight()));
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel deathLabel = createDeathJLabel();
        add(Box.createRigidArea(new Dimension(0,(400/2))));
        add(deathLabel);
        JButton closeGameBtn = createCloseBtn();
        JButton restartButton = createRestartButton();
        add(Box.createRigidArea(new Dimension(0,10)));
        add(restartButton);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(closeGameBtn);
    }

    public JLabel createDeathJLabel(){
        JLabel deathLabel = new JLabel("YOU DIED");
        deathLabel.setAlignmentX(CENTER_ALIGNMENT);
        deathLabel.setAlignmentY(CENTER_ALIGNMENT);
        deathLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        deathLabel.setForeground(Color.red);
        return deathLabel;
    }

    public JButton createCloseBtn(){
        JButton closeBtn = new JButton("Close Game");
        closeBtn.setAlignmentX(CENTER_ALIGNMENT);
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO make it so that it closes the APP
                dsc.closeGame();
            }
        });
        return closeBtn;
    }

    public JButton createRestartButton(){
        JButton restartButton = new JButton("Restart");
        restartButton.setAlignmentX(CENTER_ALIGNMENT);
        restartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO RESET GAME
                dsc.restartGame();
            }
        });
        return restartButton;
    }

    @Override
    public void update() {
        revalidate();
        repaint();
    }
}
