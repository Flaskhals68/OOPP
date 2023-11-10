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
    private String[] idArray = {"Zombie", "Skeleton", "player"};
    private Color[] colorArray = {Color.green, Color.lightGray, Color.blue};
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

    public Map<String, JPanel> getMap(){
        Map<String, JPanel> entityPanelMapCopy = entityPanelMap;
        return entityPanelMapCopy;
    }

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
