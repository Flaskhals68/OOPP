package com.group4.app.view.worldView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpriteComponent extends JPanel{
    private BufferedImage spriteImage;

    public SpriteComponent(BufferedImage spritImage, int tileWidth, int tileHeight ){
        this.spriteImage = spritImage;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(tileWidth, tileHeight));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(spriteImage != null){
            g.drawImage(spriteImage, 0, 0, getWidth(), getHeight(), this);

        }
    }

    public BufferedImage getSpriteImage(){
        return this.spriteImage;
    }
}
