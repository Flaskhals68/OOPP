package com.group4.app.view.worldView;

import javax.swing.*;

public class TestClass {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("400x400 JFrame");
        
        // Set the size of the JFrame
        frame.setSize(1000, 700);
        
        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new DeathScreen());
        
        // Make the JFrame visible
        frame.setVisible(true);

    }
}

