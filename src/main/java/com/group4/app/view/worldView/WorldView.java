package com.group4.app.view.worldView;

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
import java.lang.reflect.InvocationTargetException;
import java.security.Identity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;

import com.group4.app.controller.StateController;
import com.group4.app.controller.worldControllers.AWorldController;
import com.group4.app.controller.worldControllers.PlayerMovementController;
import com.group4.app.controller.worldControllers.PlayerViewAttackController;
import com.group4.app.controller.ActionController;
import com.group4.app.controller.WorldController;
import com.group4.app.model.IDrawable;
import com.group4.app.model.IModelObserver;
import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;
import com.group4.app.model.actions.Action;
import com.group4.app.view.ActionState;
import com.group4.app.view.IGameView;
import com.group4.app.view.IStateControllerObserver;
import com.group4.app.model.actions.PositionActionInput;
import com.group4.app.model.creatures.Entity;
import com.group4.app.model.dungeon.Tile;

public class WorldView extends JPanel implements IGameView{
    private AWorldController controller;
    private WorldViewState drawingState;
    private ActionState state;

    //The tiles that are seen by the player at the moment.
    private Map<Position, JLayeredPane> visibleTiles = new HashMap<>();

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

    public WorldView(ActionState initialState) {
        this.state = initialState;
        initComponents();

    }

    /**
     * Initiates the worldView by setting up it's look and adding the tiles that should be added initially.
     */
    private void initComponents(){
        initialState();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        addTiles(entityPanelGenerator);
        colorBorders();
    }

    private void initialState(){
        if(StateController.getState() == ActionState.IDLE){
            controller = new PlayerMovementController();
            drawingState = new WorldViewPlayerMoveState(controller.getPlayerPosition());
        }
        else if(StateController.getState() == ActionState.ATTACK){
            controller = new PlayerViewAttackController();
            drawingState = new WorldViewPlayerAttackState(controller.getPlayerPosition());
        }
        
    }

    /**
     * Calculates which tiles should be added by getting the player's position and adding
     * the corresponding tiles around that position. Takes a EntetyPanelGenerator as a parameter
     * to be able to draw the tiles and, if there are any, the enteties located at that tile.
     * @param entityPanelGenerator
     */
    private void addTiles(EntityPanelGenerator entityPanelGenerator){
        Position playerPosition = controller.getPlayerPosition();

        //Offsets in both directions from the player
        int centerX = MAX_NUMBER_OF_TILES_PER_ROW/2;
        int centerY = MAX_NUMBER_OF_TILES_PER_ROW/2;

        //Used to get the actual positions of each tile in the loop below.
        int actualX = playerPosition.getX() - centerX;
        int actualY = playerPosition.getY() - centerY;

        // reset which tiles are seen by the player 
        visibleTiles = new HashMap<>();

        for(int i = 0; i < MAX_NUMBER_OF_TILES_PER_ROW; i++ ){
            int y = actualY + i;
            tileConstraints.gridy = i;
            for(int j = 0; j < MAX_NUMBER_OF_TILES_PER_ROW; j++){
                int x = actualX + j;
                tileConstraints.gridx = j;
                Position pos = new Position(x, y, controller.getPlayerFloor());
                if(controller.isValidCoordinates(x,y)) {
                    JLayeredPane entityPanel = createTile(pos);
                    visibleTiles.put(pos, entityPanel);
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
    private JLayeredPane createTile(Position pos){
        int borderWidth = 1;

        // Makes sure that the components get added inside the border of the JLayerPane
        int innerWidth = TILE_WIDHT - 2 * borderWidth;
        int innerHeight = TILE_HEIGHT - 2 * borderWidth;

        JLayeredPane tileView = getTileView(borderWidth);

        addMouseListenerClickedEvent(pos, tileView);

        addMouseListenerHover(pos, tileView);


        List<IDrawable> drawables = controller.getDrawables(pos.getX(), pos.getY());
        int layerIndex = 0;
        if (drawables.isEmpty() == false) {
            for(int i = drawables.size()-1; i >= 0; i-- ){
                IDrawable e = drawables.get(i);
                JPanel p = entityPanelGenerator.getJPanel(e.getId());
                tileView.add(p, layerIndex++);
                
                p.setBounds(borderWidth, borderWidth, innerWidth, innerHeight);
            }
            }
        return tileView;
    }

    /**
     * Add a mouselistener to a JLayeredPane with a mouseEntered event and a mouseExited event. 
     * @param pos the position where the mouse entered
     * @param tileView
     */
    private void addMouseListenerHover(Position pos, JLayeredPane tileView) {
        tileView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                drawingState.drawMouseEnteredTile(pos);
            }
        });

        tileView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e){
                drawingState.drawMouseExitedTile();
            }
        });
    }

    /**
     * Add a mouseListener to a JLayeredPane with a mouseClicked event.
     * @param pos the position where the user has clicked. 
     * @param tileView
     */
    private void addMouseListenerClickedEvent(Position pos, JLayeredPane tileView) {
        tileView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                drawingState.drawMouseClickedOnTile(pos);
            }
        });
    }

    /**
     * Creates and sets the size, background color and border color, and returns a JLayeredPane
     * @param borderWidth
     * @return
     */
    private JLayeredPane getTileView(int borderWidth) {
        JLayeredPane tileView = new JLayeredPane();
        tileView.setPreferredSize(new Dimension(TILE_WIDHT,TILE_HEIGHT));
        tileView.setBackground(Color.white);
        tileView.setBorder(BorderFactory.createLineBorder(Color.darkGray, borderWidth));
        return tileView;
    }

    /**
     * Colors the JLayeredPanes' borders at the specific positions in a map of positions and JLayeredPanes, based on the view's state. 
     * @param positions set of positions
     */ 
    private void colorBorders(){
        drawingState.colorBorders(visibleTiles);
    }

    public void setState(ActionState newState){
        if(this.state == newState){
            return;
        }
        if(newState == ActionState.IDLE){
            this.drawingState = new WorldViewPlayerMoveState(controller.getPlayerPosition());
        }
        else if(newState == ActionState.ATTACK){
            this.drawingState = new WorldViewPlayerAttackState(controller.getPlayerPosition());
        }
        this.controller = drawingState.getController();
        this.state = StateController.getState();
    }

    @Override
    public void updateView() {
        removeAll();
        addTiles(entityPanelGenerator);
        setState(StateController.getState());
        if(StateController.getState() != ActionState.DISABLED){
            colorBorders();
        }
        revalidate();
        repaint();
    }

    @Override
    public JPanel getView(){
        return this;
    }
}
