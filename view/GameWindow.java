package view;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

/*
 * This is the frame where every other panel is drawn on. 
 * Should draw each view. 
 */
public class GameWindow extends JFrame{
    private static GameWindow instance = null;
    private HudView hudView;
    private InventoryView inventoryView;
    private WorldView worldView;

    private GameWindow() {
        initComponents();
    }

    //TODO should make so that each view gets added to the frame
    private void initComponents (){
        setTitle("GAME");
        setPreferredSize(new Dimension(1280,720));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
    }

    public static GameWindow getInstance() {
        if (instance == null) {
            return new GameWindow();
        }
        else {
            return GameWindow.instance;
        }
    }


}
