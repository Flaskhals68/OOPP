package com.group4.app.controller;

import com.group4.app.model.Model;
import com.group4.app.model.creatures.Player;

public class InventoryController {
    public void useItem(String itemName) {
        Player p = Model.getInstance().getPlayer();
        p.useItemFromInventory(itemName);
    }
}
