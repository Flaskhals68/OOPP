package com.group4.app.view.worldView;

import java.util.Map;
import java.util.Set;

import javax.swing.JLayeredPane;

import com.group4.app.model.Position;
import com.group4.app.view.ActionState;

public abstract class WorldViewState {
    protected Position playerPosition;

    public WorldViewState(Position playerPosition){
        this.playerPosition = playerPosition;
    }

    public abstract void colorBorders(Set<Position> positionsToColor, Map<Position, JLayeredPane> visibleToPlayer);
    public abstract void drawMouseClickedOnTile(Position targetPosition);
    public abstract void drawMouseEnteredTile(Position targetPosition);
    public abstract void drawMouseExitedTile();
}
