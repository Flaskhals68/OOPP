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
    private static int SCREEN_WIDTH = 1280;
    private static int SCREEN_HEIGHT = 720;
    private static String title = "GAME";
    private static Color backGroundColor = Color.darkGray;

    private GameWindow(List<JPanel> panelList) {
        this.panelList = panelList;
        initComponents();
    }
    
    public static GameWindow getInstance(List<JPanel> panelList) {
        if (instance == null) {
            instance = new GameWindow(panelList);
            return instance;
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

    private void addPanels(){
        for(JPanel panel : panelList){
            add(panel);
        }
    }

    /**
     * Initially draws the panels
     */
    private void drawPanels(){
        for(JPanel panel : panelList){
            panel.repaint();
        }
    }

    //TODO experiment with layout.
    private void initGameWindow(){
        setTitle(title);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(backGroundColor);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
        setResizable(false);
    }




}
