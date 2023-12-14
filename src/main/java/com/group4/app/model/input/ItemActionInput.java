package com.group4.app.model.input;

import com.group4.app.model.items.IInventoriable;

public class ItemActionInput extends ActionInput<IInventoriable>{
    public ItemActionInput(String actionId, IInventoriable target) {
        super(actionId, target);
    }
}
