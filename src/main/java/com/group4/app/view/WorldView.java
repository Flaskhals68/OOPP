package com.group4.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.group4.app.controller.WorldController;
import com.group4.app.model.Model;
import com.group4.app.model.Tile;

//FIXME implement Observer pattern
public class WorldView extends JPanel{
    private Model model;
    private WorldController controller;
    private static final int NUMBER_OF_TILES = 10;
    private static final int HEIGHT = 400;
    private static final int WIDTH = 400;
    private static final int TILE_WIDHT = WIDTH/NUMBER_OF_TILES;
    private static final int TILE_HEIGHT = HEIGHT/NUMBER_OF_TILES;

    //Contains the id:s of all possible entities.

    
    public WorldView(Model model, WorldController controller) {
        this.model = model;
        this.controller = controller;
        initComponents();
    }

    /**
     * Initiates the worldView by drawing all the components.
     */
    private void initComponents(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        drawTile();
        
    }

    private void drawTile(){
        // Should return where the player is.
        Tile playerTile = model.getPlayerTile();

        int playerX = playerTile.getXPos();
        int playerY = playerTile.getYPos();

        int xStart = Math.max(playerX - 4,0);
        int xEnd = Math.min(playerX + 4, WIDTH);

        int yStart = Math.max(playerY - 4, 0);
        int yEnd = Math.min(playerY + 4, HEIGHT);

        GridBagConstraints tileConstraints = new GridBagConstraints();
        
        //draws the tiles to the correct position relative to the player. 
        for(int i = yStart; i <= yEnd; i++ ){
            for(int j = xStart; j <= xEnd; j++){
                if(i == playerY && j == playerX){
                    tileConstraints.gridx = playerX;
                    tileConstraints.gridy = playerY;
                    add(createTile(Color.blue, j, i), tileConstraints);
                    continue;
                }
                tileConstraints.gridx = j;
                tileConstraints.gridy = i;
                add(createTile(Color.gray, j, i), tileConstraints);
            }
        }
    }

    /**
     * Create the look of the tile and create the actual tile object.
     */
    private JPanel createTile(Color color, int x, int y){
        //TODO add some way to know which entity is on the tile
        JPanel tileView = new JPanel();
        tileView.setPreferredSize(new Dimension(TILE_WIDHT,TILE_HEIGHT));
        tileView.setBackground(color);
        tileView.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        return tileView;
    }

    //TODO this should redraw each entity and tile on the world map
    @Override
    protected void paintComponent(Graphics g){
        drawTile();
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
