package com.group4.app.controller;

import com.group4.app.model.Model;

public class PlayerStatController {
    public int getPlayerHealth() {
        return Model.getInstance().getPlayerHealth();
    }

    public int getPlayerMaxHealth() {
        return Model.getInstance().getPlayerMaxHealth();
    }
}
