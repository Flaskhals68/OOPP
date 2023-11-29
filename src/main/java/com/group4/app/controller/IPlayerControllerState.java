package com.group4.app.controller;

import java.util.Set;

import com.group4.app.model.Position;

public interface IPlayerControllerState {
    public void mouseClicked(Position position);
    public void mouseEntered(Position position);
    public void mouseExited();
    public Set<Position> getHighlightedPositions();
}
