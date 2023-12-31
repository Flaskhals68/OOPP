package com.group4.app.view.worldView;

import java.util.Map;
import java.util.Set;

import javax.swing.JLayeredPane;

import com.group4.app.controller.worldControllers.AWorldController;
import com.group4.app.model.dungeon.Position;

public abstract class WorldViewState implements IClickableState{
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

}
