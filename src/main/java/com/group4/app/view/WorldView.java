package com.group4.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import com.group4.app.controller.WorldController;
import com.group4.app.model.Entity;
import com.group4.app.model.IDrawable;
import com.group4.app.model.IModelObserver;
import com.group4.app.model.Model;
import com.group4.app.model.Position;
import com.group4.app.model.Tile;

//FIXME implement Observer pattern
public class WorldView extends JPanel implements IGameView{
    private Model model;
    private WorldController controller;

    //TODO implement zoom?
    private static float zoom = 2;

    //Specifies how many tiles at maximum are allowed to be displayed per row.
    private static int MAX_NUMBER_OF_TILES_PER_ROW = (int) (11 * zoom);

    // Dimensions of the actual worldView Panel
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    // Max dimensions of each tile
    private static final int TILE_WIDHT = WIDTH/MAX_NUMBER_OF_TILES_PER_ROW;
    private static final int TILE_HEIGHT = HEIGHT/MAX_NUMBER_OF_TILES_PER_ROW;

    private GridBagConstraints tileConstraints = new GridBagConstraints();

    // Helper class to generate the sprites
    private static final EntityPanelGenerator entityPanelGenerator = new EntityPanelGenerator(TILE_HEIGHT, TILE_WIDHT);

    public WorldView(Model model, WorldController controller) {
        this.model = model;
        this.controller = controller;
        initComponents();

    }

    /**
     * Initiates the worldView by setting up it's look and adding the tiles that should be added initially.
     */
    private void initComponents(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        drawTile(entityPanelGenerator);

    }

    /**
     * Calculates which tiles should be drawn by getting the player's position and drawing
     * the corresponding tiles around that position. Takes a EntetyPanelGenerator as a parameter
     * to be able to draw the tiles and, if there are any, the enteties located at that tile.
     * @param entityPanelGenerator
     */
    private void drawTile(EntityPanelGenerator entityPanelGenerator){
        int playerX = model.getPlayerX();
        int playerY = model.getPlayerY();

        //Offsets in both directions from the player
        int centerX = MAX_NUMBER_OF_TILES_PER_ROW/2;
        int centerY = MAX_NUMBER_OF_TILES_PER_ROW/2;

        //Used to get the actual positions of each tile in the loop below.
        int actualX = playerX - centerX;
        int actualY = playerY - centerY;

        for(int i = 0; i < MAX_NUMBER_OF_TILES_PER_ROW; i++ ){
            int y = actualY + i;
            tileConstraints.gridy = i;
            for(int j = 0; j < MAX_NUMBER_OF_TILES_PER_ROW; j++){
                int x = actualX + j;
                tileConstraints.gridx = j;
                Position pos = new Position(x, y);
                if(model.isValidCoordinates(pos)) {
                    JLayeredPane entityPanel = createTile(model, pos);
                    add(entityPanel, tileConstraints);
                }
                else{
                     add(createEmptyTile(), tileConstraints);
                }
                   
            }

        }
    }

    
    /**
     * Creates the tiles that represent the void in the world
     * @return a black tile
     */
    private JPanel createEmptyTile(){
        JPanel tileView = new JPanel();
        tileView.setPreferredSize(new Dimension(TILE_WIDHT,TILE_HEIGHT));
        tileView.setBackground(Color.BLACK);
        return tileView;
    }

    /**
     * Create the actual tile panel and add it's enteties to it.
     */
    private JLayeredPane createTile(Model model, Position pos){
        int borderWidth = 1;

        // Makes sure that the components get added inside the border of the JLayerPane
        int innerWidth = TILE_WIDHT - 2 * borderWidth;
        int innerHeight = TILE_HEIGHT - 2 * borderWidth;

        JLayeredPane tileView = new JLayeredPane();
        tileView.setPreferredSize(new Dimension(TILE_WIDHT,TILE_HEIGHT));
        tileView.setBackground(Color.white);
        tileView.setBorder(BorderFactory.createLineBorder(Color.darkGray, borderWidth));

        //FIXME should be in controller
        tileView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                model.movePlayer(pos);
                model.updateObservers();
            }
        });

        List<IDrawable> drawables = model.getDrawables(model.getPlayerFloor(), pos);
        int layerIndex = 0;
        if (drawables.isEmpty() == false) {
            for(int i = drawables.size()-1; i >= 0; i-- ){
                IDrawable e = drawables.get(i);
                JPanel p = entityPanelGenerator.getJPanel(e.getId());
                tileView.add(p, layerIndex++);
                p.setBounds(borderWidth,borderWidth, innerWidth, innerHeight);
            }
            }
        return tileView;
    }

    @Override
    public void updateView() {
        removeAll();
        drawTile(entityPanelGenerator);
        revalidate();
        repaint();
    }

    public JPanel getView(){
        return this;
    }

}
