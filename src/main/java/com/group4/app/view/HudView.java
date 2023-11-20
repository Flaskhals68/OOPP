package com.group4.app.view;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import com.group4.app.controller.HudController;
import com.group4.app.model.Model;

public class HudView extends JPanel implements IGameView {
    private Model model;
    private HudController controller;

    private GridBagConstraints btnConstraints = new GridBagConstraints();

    public HudView(Model model, HudController controller) {
        this.model = model;
        this.controller = controller;
    }

    private void initComponents() {
        
    }

    @Override
    public void updateView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateView'");
    }

    @Override
    public JPanel getView() {
        return this;
    }


}
