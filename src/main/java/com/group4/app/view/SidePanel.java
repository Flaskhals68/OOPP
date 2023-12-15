package com.group4.app.view;

import javax.swing.JPanel;

public abstract class SidePanel extends JPanel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 300;

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
