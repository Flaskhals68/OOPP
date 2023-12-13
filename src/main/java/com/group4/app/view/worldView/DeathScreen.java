package com.group4.app.view.worldView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;


import com.group4.app.controller.DeathScreenController;
import com.group4.app.view.SubView;

public class DeathScreen extends SubView {

    private DeathScreenController dsc;
    private static boolean hasPlayedDeathSound;
    private String deathSoundFilePath = "src\\resources\\646974__huw2k8__yourtimehascome.wav";
    
    public DeathScreen(){
        hasPlayedDeathSound = false;
        this.dsc = new DeathScreenController();
        initScreen();
        JLabel deathLabel = createDeathJLabel();
        // divide height by three to get the label to end up in the middle of the screen.
        addComponent(deathLabel, BaseGameDisplayArea.getScreenHeight()/3);
        JButton closeGameBtn = createCloseBtn();
        JButton restartButton = createRestartButton();
        addComponent(closeGameBtn, 10);
        addComponent(restartButton, 10);
    }

    public void initScreen(){
        setPreferredSize(new Dimension(BaseGameDisplayArea.getScreenWidth(), BaseGameDisplayArea.getScreenHeight()));
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Adds a component to a container, along with horizontal space above it.
     * @param c a component to be added
     * @param space horizontal space above the component
     */
    public void addComponent(Component c, int space){
        add(Box.createRigidArea(new Dimension(0,space)));
        add(c);
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
                dsc.restartGame();
            }
        });
        return restartButton;
    }

    @Override
    public void update() {
        if(!hasPlayedDeathSound){
            //TODO Play sound upon death
            hasPlayedDeathSound = true;
        }
        revalidate();
        repaint();
    }
}
