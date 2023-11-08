package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.group4.app.controller.WorldController;
import com.group4.app.model.Model;

//FIXME implement Observer pattern
public class WorldView extends JPanel{
    private Model model;
    private WorldController controller;
    private static final int HEIGHT = 720/2;
    private static final int WIDTH = 1280/2;

    
    public WorldView(Model model, WorldController controller) {
        this.model = model;
        this.controller = controller;
        initComponent();
    }

    private void initComponent(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
    }

    //TODO this should redraw each entity on the world map
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    
    /* 
    //FIXME
    @Override
    public void update(){
        System.out.println("Yo jag ritar ut en world");
        this.repaint();
    }
    */
}
