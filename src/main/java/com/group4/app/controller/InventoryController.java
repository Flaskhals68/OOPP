package com.group4.app.controller;

import com.group4.app.model.Model;
import com.group4.app.model.actions.Action;
import com.group4.app.model.actions.ItemActionInput;
import com.group4.app.model.creatures.Player;

public class InventoryController {
    public void useItem(String itemName) {
        Player p = Model.getInstance().getPlayer();
        ActionController.getInstance().queueAction(new ItemActionInput("useItem", p.fetchItemFromInventory(itemName)));
    }
}
