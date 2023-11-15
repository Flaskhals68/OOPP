package com.group4.app.model;

import java.util.HashMap;
import java.util.Stack;

public class Inventory {

    private HashMap<String, Stack<IInventoriable>> items;
    public Inventory() {
        items = new HashMap<>();
    }

    /**
     * Should add item to inventory, if same item already exists add to stack. If it does not exist
     * @param item the item to add to inventory
     */
    public void addItem(IInventoriable item) {
        if (items.containsKey(item.getName())) {
            Stack<IInventoriable> stack = items.get(item.getName());
            stack.push(item);
        } else {
            items.put(item.getName(), new Stack<IInventoriable>());
            Stack<IInventoriable> stack = items.get(item.getName());
            stack.add(item);
        }
    }

    /**
     * If multiple in inventory pop one. If none left remove key from hashmap
     * @param itemName the name of the item we want to get
     * @return the item object associated with the key
     */
    public IInventoriable getItem(String itemName) {
        if (items.containsKey(itemName)) {
            Stack<IInventoriable> stack = items.get(itemName);
            IInventoriable itemToReturn = stack.pop();
            if (stack.empty()) {
                items.remove(itemName);
            }
            return itemToReturn;
        } else {
            throw new IllegalArgumentException("Item does not exist in inventory");
        }
    }
    /**
     * Use this to draw inventory later
     * @return Hashmap with items
     */
    public HashMap<String, Stack<IInventoriable>> getItems() {
        return items;
    }

}
