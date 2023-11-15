package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class EntityPanelGenerator{
    private Map<String, JPanel> entityPanelMap = new HashMap<>();
    private String[] idArray = {"sand","stone", "Zombie", "Skeleton", "player"};
    private Color[] colorArray = {Color.yellow, Color.gray, Color.green, Color.lightGray, Color.blue};
    private int tile_width;
    private int tile_height;

    public EntityPanelGenerator(int tile_height, int tile_width){
        this.tile_height = tile_height;
        this.tile_width = tile_width;
        this.entityPanelMap = entityPanelMap;
        ArrayList<JPanel> panels = createEntityPanels(colorArray);
        for(int i = 0; i < idArray.length; i++){
            entityPanelMap.put(idArray[i], panels.get(i));
        }
    }

    /**
     * Takes in an id as a key and returns a copy of the corresponding JPanel value the entity panel map
     */
    public JPanel getJPanel(String key){
        JPanel jp = createCopy(entityPanelMap.get(key));
        return jp;
    }

    /**
     * This copies a JPanel and returns it.
     * @return a copy of the parameter
     */
    private JPanel createCopy(JPanel jPanel){
        JPanel copiedPanel = new JPanel();
        copiedPanel.setPreferredSize(jPanel.getPreferredSize());
        copiedPanel.setBackground(jPanel.getBackground());
        return copiedPanel;
    }

    /**
     * Creates panels with the corresponding colors
     */
    private ArrayList<JPanel> createEntityPanels(Color[] colorArray2) {
        ArrayList<JPanel> panels = new ArrayList<>();
        for(int i = 0; i < colorArray2.length; i++){
            JPanel p = new JPanel();
            p.setPreferredSize(new Dimension(tile_width, tile_height));
            p.setBackground(colorArray2[i]);
            panels.add(p);            
        }
        return panels;
    }

    
}
