package com.group4.app.view.worldView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A class that creates a map with sprite ID:s as keys and JPanels containing the correct sprite as values.
 */
public class EntityPanelGenerator{
    private Map<String, JPanel> entityPanelMap = new HashMap<>();
    private String[] idArray = {"stone", "Zombie", "Skeleton", "player","deadEnemy", "playerDead"};
    private String[] imagePathArray = {
        "src/resources/stone_tile2.png",
        "src/resources/ghoul.png",
        "src/resources/skeletal_warrior.png",
        "src/resources/player.png",
        "src/resources/dead_enemy_stone2.png",
        "src/resources/dead_player_stone2.png"
    };
    private int tile_width;
    private int tile_height;

    public EntityPanelGenerator(int tile_height, int tile_width){
        this.tile_height = tile_height;
        this.tile_width = tile_width;
        ArrayList<JPanel> panels = createEntityPanels(imagePathArray);
        for(int i = 0; i < idArray.length; i++){
            entityPanelMap.put(idArray[i], panels.get(i));
        }
    }

    /**
     * Takes in an id as a key and returns a copy of the corresponding JPanel value the entity panel map
     */
    public JPanel getJPanel(String key){
        if(!entityPanelMap.containsKey(key)){
            throw new IllegalArgumentException();
        }
        JPanel jp = createCopy((SpriteComponent)entityPanelMap.get(key));
        return jp;
    }

    /**
     * This copies a SpriteComponent and returns it.
     * @return a copy of the parameter
     */
    private SpriteComponent createCopy(SpriteComponent ogPanel){
        BufferedImage originalImage = ogPanel.getSpriteImage();
        if (originalImage != null) {
            // Create a new SpriteComponent and set its image
            SpriteComponent copiedComponent = new SpriteComponent(originalImage, ogPanel.getWidth(), ogPanel.getHeight());
            return copiedComponent;
        } else {
            // Handle the case where the original image is null
            System.err.println("Error: Original image is null");
            return null;
        }
    }

    /**
     * Creates panels with the corresponding colors
     */
    private ArrayList<JPanel> createEntityPanels(String[] imagePathArray) {
        ArrayList<JPanel> panels = new ArrayList<>();
        for(String imagePath: imagePathArray){
            try {
                BufferedImage image = ImageIO.read(new File(imagePath));
                JPanel p = new SpriteComponent(image, tile_width, tile_height);
                panels.add(p);
            } catch (IOException e) {
                e.printStackTrace();
            } 
            
        
            
        }
        return panels;
    }
}
