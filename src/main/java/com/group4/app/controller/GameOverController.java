package com.group4.app.controller;

import com.group4.app.model.Model;

public class GameOverController {

    public void close() {
        System.exit(0);
    }

    public void restart() {
        // TODO: implement
    }

    public boolean isGameOver() {
        return Model.getInstance().getPlayer().getHitPoints() <= 0;
    }
}
