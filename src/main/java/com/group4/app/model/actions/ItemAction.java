package com.group4.app.model.actions;

import com.group4.app.model.Model;
import com.group4.app.model.dungeon.Position;
import com.group4.app.model.items.IInventoriable;
import com.group4.app.model.items.IUser;

import java.util.Set;

public class ItemAction extends Action<IUser, IInventoriable>{


    public ItemAction(int apCost, String name, IUser actionTaker) {
        super(apCost, name, actionTaker);
    }

    @Override
    public void perform(IInventoriable target) {
        target.use(getActionTaker());
        Model.getInstance().updateObservers();
    }

    @Override
    public Set<IInventoriable> getTargetable() {
        return null;
    }

    @Override
    public Set<Position> getTargetablePositions() {
        return null;
    }


}
