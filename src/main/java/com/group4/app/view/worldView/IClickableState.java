package com.group4.app.view.worldView;

import com.group4.app.model.dungeon.Position;

public interface IClickableState {
    
    public abstract void drawMouseClickedOnTile(Position targetPosition);

    public abstract void drawMouseEnteredTile(Position targetPosition);
    
    public abstract void drawMouseExitedTile();
}
