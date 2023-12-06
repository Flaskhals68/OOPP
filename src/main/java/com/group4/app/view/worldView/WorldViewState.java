package com.group4.app.view.worldView;

import java.util.Map;
import java.util.Set;

import javax.swing.JLayeredPane;

import com.group4.app.controller.worldControllers.AWorldController;
import com.group4.app.model.Position;
import com.group4.app.view.ActionState;

public abstract class WorldViewState {
    protected Position playerPosition;
    protected boolean hoverFlag;
    protected Set<Position> highlightedPositions;

    public WorldViewState(Position playerPosition){
        this.playerPosition = playerPosition;
    }

    public Set<Position> getHighlightedPositions(){
        Set<Position> litUpPos = this.highlightedPositions;
        return litUpPos;
    }

    public void setHighLightedPositions(Set<Position> pos){
        this.highlightedPositions = pos;
    }

    public abstract AWorldController getController();

    /**
     * Colors the border of a number of the tiles that are currently shown to the user based on view state.
     * @param visibleToPlayer
     */
    public abstract void colorBorders(Map<Position, JLayeredPane> visibleToPlayer);

    public abstract void drawMouseClickedOnTile(Position targetPosition);

    public abstract void drawMouseEnteredTile(Position targetPosition);
    
    public abstract void drawMouseExitedTile();
}
