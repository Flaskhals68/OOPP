package com.group4.app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.group4.app.controller.GameOverController;

public class GameOverMenu extends SubView {

    private GameOverController goc;
    private static int BUTTON_WIDTH = 300;

    public GameOverMenu(){
        this.goc = new GameOverController();
        initScreen();
        JLabel deathLabel = initGameOverLabel();
        addComponent(deathLabel, MainWindow.getScreenHeight()-350);
        JButton closeGameBtn = createCloseBtn();
        JButton restartButton = createRestartButton();
        addComponent(restartButton, 10);
        addComponent(closeGameBtn, 10);
    }

    public void initScreen(){
        setPreferredSize(new Dimension(MainWindow.getScreenWidth(), MainWindow.getScreenHeight()));
        setBackground(Color.DARK_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addComponent(Component component, int margin){
        add(Box.createRigidArea(new Dimension(0,margin)));
        add(component);
    }

    public JLabel initGameOverLabel(){
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setAlignmentX(CENTER_ALIGNMENT);
        gameOverLabel.setAlignmentY(CENTER_ALIGNMENT);
        gameOverLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
        gameOverLabel.setForeground(Color.RED);
        return gameOverLabel;
    }

    public JButton createCloseBtn(){
        JButton closeBtn = new JButton("Close");
        closeBtn.setAlignmentX(CENTER_ALIGNMENT);
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goc.close();
            }
        });
        return closeBtn;
    }

    public JButton createRestartButton(){
        JButton restartButton = new JButton("Restart");
        restartButton.setAlignmentX(CENTER_ALIGNMENT);
        restartButton.setBounds(0, 0, BUTTON_WIDTH, 50);
        restartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goc.restart();
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