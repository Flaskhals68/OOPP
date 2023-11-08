package com.group4.app.view;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//TODO Add obeserver
/*
 * This is the frame where every other panel is added to
 */
public class GameWindow extends JFrame{
    private List<JPanel> panelList = new ArrayList<JPanel>();
    private static GameWindow instance = null;

    private GameWindow() {
        initComponents();
    }
    
    public static GameWindow getInstance() {
        if (instance == null) {
            return new GameWindow();
        }
        else {
            return GameWindow.instance;
        }
    }

    private void initComponents(){
        initGameWindow();
        addPanels();
        drawPanels();
        setVisible(true);
        
    }

    //Adds the panels
    private void addPanels(){
        for(JPanel panel : panelList){
            add(panel);
        }
    }

    //Initially draws the panels
    private void drawPanels(){
        for(JPanel panel : panelList){
            panel.repaint();
        }
    }

    //TODO experiment with layout.
    private void initGameWindow(){
        setTitle("GAME");
        setSize(1280, 720);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
        setResizable(false);
    }




}
