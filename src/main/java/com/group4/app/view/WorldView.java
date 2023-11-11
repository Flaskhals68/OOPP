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
import com.group4.app.model.Entity;
import com.group4.app.model.Model;
import com.group4.app.model.Tile;

//FIXME implement Observer pattern
public class WorldView extends JPanel{
    private Model model;
    private WorldController controller;
    private static final int MAX_NUMBER_OF_TILES_PER_ROW = 10;
    private static final int TILES_DRAWN_FROM_PLAYER = MAX_NUMBER_OF_TILES_PER_ROW/2 - 1;
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;
    private static final int TILE_WIDHT = WIDTH/MAX_NUMBER_OF_TILES_PER_ROW;
    private static final int TILE_HEIGHT = HEIGHT/MAX_NUMBER_OF_TILES_PER_ROW; 
    private static final EntityPanelGenerator entityPanelMap = new EntityPanelGenerator(TILE_HEIGHT, TILE_WIDHT);

    //map to include each id and it's corresponding sprite.
    private Map<String, JPanel> spriteMap;

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
        drawTile(entityPanelMap);
        
    }

    private void drawTile(EntityPanelGenerator entityPanelGenerator){
        // Should return where the player is.
        Tile playerTile = model.getPlayerTile();

        int playerX = playerTile.getXPos();
        int playerY = playerTile.getYPos();

        int xStart = Math.max(playerX - TILES_DRAWN_FROM_PLAYER,0);
        int xEnd = Math.min(playerX + TILES_DRAWN_FROM_PLAYER, WIDTH);

        int yStart = Math.max(playerY - TILES_DRAWN_FROM_PLAYER, 0);
        int yEnd = Math.min(playerY + TILES_DRAWN_FROM_PLAYER, HEIGHT);

        GridBagConstraints tileConstraints = new GridBagConstraints();
        
        //draws the tiles to the correct position relative to the player. 
        for(int i = yStart; i <= yEnd; i++ ){
            for(int j = xStart; j <= xEnd; j++){
                if(i == playerY && j == playerX){
                    tileConstraints.gridx = playerX;
                    tileConstraints.gridy = playerY;
                    JPanel playerPanel = createTile(model, playerX, playerY);
                    add(playerPanel, tileConstraints);
                    continue;
                }
                else{
                    JPanel entityPanel = createTile(model, j, i);
                    tileConstraints.gridx = j;
                    tileConstraints.gridy = i;
                    add(entityPanel, tileConstraints);
                    continue;

                }
                }
            }
        }

    /**
     * Create the actual tile object and it's enteties.
     */
    private JPanel createTile(Model model, int x, int y){
        JPanel tileView = new JPanel();
        tileView.setPreferredSize(new Dimension(TILE_WIDHT,TILE_HEIGHT));
        tileView.setBackground(Color.gray);
        tileView.setBorder(BorderFactory.createLineBorder(Color.darkGray));

        if (model.getEntities(x, y).isEmpty() == false) {
            for(Entity e : model.getEntities(x, y)){
                JPanel p = entityPanelMap.getJPanel(e.getId());
                tileView.add(p);
                }
            }

        return tileView;
    }

    //TODO this should redraw each entity and tile on the world map
    @Override
    protected void paintComponent(Graphics g){
        //drawTile();
        super.paintComponent(g);
    }
    
    
    //FIXME 
    /* 
    @Override
    public void update(){
        System.out.println("Yo jag ritar ut en world");
        this.repaint();
    }
    */
}
