package com.group4.app.controller;

import com.group4.app.model.Model;

public class DeathScreenController {

    public void closeGame(){
        System.exit(0);
    }

    public void restartGame(){
        Model.getInstance().queueRestart();
    }
}
