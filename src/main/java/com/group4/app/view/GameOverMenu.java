package com.group4.app.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GameOverMenu extends JPanel implements IGameView {

    private boolean isShowing = false;

    public GameOverMenu(int width, int heigth) {
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(width, heigth));
    }

    @Override
    public void updateView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateView'");
    }

    @Override
    public void repaint() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'repaint'");
    }

    @Override
    public JPanel getView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }
    
}
